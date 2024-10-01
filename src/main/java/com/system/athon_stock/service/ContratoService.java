package com.system.athon_stock.service;

import com.system.athon_stock.domain.contrato.*;
import com.system.athon_stock.domain.excessoes.CredentialsException;
import com.system.athon_stock.domain.excessoes.FindByIdException;
import com.system.athon_stock.domain.excessoes.ReturnNullException;
import com.system.athon_stock.domain.person.Person;
import com.system.athon_stock.domain.product.PriceProduct;
import com.system.athon_stock.domain.product.Product;
import com.system.athon_stock.domain.contrato.request.ContratoServiceRequest;
import com.system.athon_stock.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;
    private final PersonRepository personRepository;
    private final ProductRepository productRepository;
    private final ContratoItensRepository contratoItensRepository;
    private final PriceProductRepository priceProductRepository;
    private final DefaultAuthenticationEventPublisher authenticationEventPublisher;

    public List<ContratoResponseDTO> findAllContratos(Long id) {
        returnPerson(id);
        List<Contrato> contratos = contratoRepository.findByPerson_id(id);
        List<ContratoResponseDTO> contratoResponseDTOs = new ArrayList<>();

        for (Contrato contrato : contratos) {
            contratoResponseDTOs.add(converte(contrato));
        }
        if (contratoResponseDTOs.isEmpty()) {
            throw new ReturnNullException("Voce nao possui contratos no momento");
        }
        return contratoResponseDTOs;
    }

    public List<ContratoResponseDTO> findAllContratosByData(ContratoServiceRequest request) {
        returnPerson(request.id());
        if (request.dataInicio() == null || request.dataFim() == null) {throw new ReturnNullException("As datas de início e fim não podem ser nulas");}

        if (request.dataInicio().isAfter(request.dataFim())) {throw new ReturnNullException("A data de início deve ser anterior à data final");}

        List<Contrato> contratos = contratoRepository.findByPerson_IdAndDateBetween(request.id(), request.dataInicio(), request.dataFim());
        List<ContratoResponseDTO> contratoResponseDTOs = new ArrayList<>();

        for (Contrato contrato : contratos) {contratoResponseDTOs.add(converte(contrato));}
        if (contratoResponseDTOs.isEmpty()) {throw new ReturnNullException("Voce nao possui contratos no momento");}
        return contratoResponseDTOs;
    }

    public List<ContratoResponseDTO> findAllContratosByNameClient(Long id, String nameClient) {
        returnPerson(id);
        returnClientName(nameClient);
        List<Contrato> contratos = contratoRepository.findByPerson_IdAndNameClientIgnoreCase(id, nameClient);
        List<ContratoResponseDTO> contratoResponseDTOs = new ArrayList<>();

        for (Contrato contrato : contratos) {contratoResponseDTOs.add(converte(contrato));}
        if (contratoResponseDTOs.isEmpty()) {throw new ReturnNullException("Voce nao possui contratos no momento");}
        return contratoResponseDTOs;
    }

    public void registerContrato(RegisterContratoDTO contratoDTO) {
        var person = returnPerson(contratoDTO.personId());
        validateRegisterDTO(contratoDTO);

        Contrato contrato = new Contrato(contratoDTO.description(), contratoDTO.labor(), person, contratoDTO.nameClient());
        contratoRepository.save(contrato);

        for (ProductListContrato productListContrato : contratoDTO.products()) {
            var product1 = returnProductId(productListContrato.id());
            PriceProduct priceProduct = returnPriceProduct(product1.getId());

            var venda = -1 * productListContrato.quantity();

            if((product1.getQuantity() + venda) < 0) {
                throw new ReturnNullException("Voce nao possue quantidade suficiente do produto: " + product1.getName()+": "+product1.getQuantity()+ " Unidades");
            }

            product1.setQuantity(product1.getQuantity() + venda);

            ContratoItens contratoItens = new ContratoItens(product1, contrato, priceProduct, productListContrato.quantity());
            contratoItensRepository.save(contratoItens);
            contrato.getContratoItens().add(contratoItens);
        }
        contrato.calculateTotalValueContrato();
        contratoRepository.save(contrato);
    }

    public void updateContratoId(Long id, UpdateContratoDTO updateContratoDTO) {
        returnPerson(updateContratoDTO.personId());
        validateUpdateDTO(updateContratoDTO);

        var contrato = returnContratoId(id);
        contrato.setDescription(updateContratoDTO.description());
        contrato.setLabor(updateContratoDTO.labor());
        contrato.setNameClient(updateContratoDTO.nameClient());

        for (ProductListContrato productListDTO : updateContratoDTO.products()) { //Percorre cada produto no contrato
            Product product = returnProductId(productListDTO.id());

            var existingItem = contrato.getContratoItens().stream()
                    .filter(contratoItem -> contratoItem.getProduct().getId().equals(product.getId()))
                    .findFirst();

            if (existingItem.isPresent()) { //Atualiza o produto no contrato e faz a modificação no estoque
                ContratoItens contratoItem = existingItem.get();

                int newQuantity = productListDTO.quantity();

                int quantityDifference = newQuantity - contratoItem.getQuantity();
                if ((product.getQuantity() - quantityDifference) < 0) {
                    throw new ReturnNullException("Quantidade insuficiente do produto: " + product.getName() + ": " + product.getQuantity() + " Unidades");
                }

                product.setQuantity(product.getQuantity() - quantityDifference);
                contratoItem.setQuantity(newQuantity);

            } else { //Salva o produto novo no contrato
                PriceProduct priceProduct = returnPriceProduct(product.getId());
                int venda = -1 * productListDTO.quantity();

                if ((product.getQuantity() + venda) < 0) {
                    throw new ReturnNullException("Você não possui quantidade suficiente do produto: " + product.getName() + ": " + product.getQuantity() + " Unidades");
                }

                product.setQuantity(product.getQuantity() + venda);

                ContratoItens contratoItens = new ContratoItens(product, contrato, priceProduct, productListDTO.quantity());
                contratoItensRepository.save(contratoItens);
                contrato.getContratoItens().add(contratoItens);
            }
        }
        contrato.calculateTotalValueContrato();
        contratoRepository.save(contrato);
    }

    private void returnClientName(String nameClient) {
        if (nameClient.isEmpty() || nameClient.isBlank()) {throw new CredentialsException("Necessario inserir nome do cliente");}
        contratoRepository.findByNameClientIgnoreCase(nameClient).orElseThrow(() -> new ReturnNullException("Nome do cliente nao encontrado"));
    }

    private Contrato returnContratoId(Long id){
        if (Objects.isNull(id)) {throw new CredentialsException("Necessario informar o ID do contrato");}
        return contratoRepository.findById(id).orElseThrow(() -> new ReturnNullException("Contrato na encontrado"));
    }

    private Product returnProductId(Long id) {
        if (Objects.isNull(id)) {throw new CredentialsException("Necessario informar o ID do produto");}
        return productRepository.findById(id).orElseThrow(() -> new FindByIdException("Produto não encontrada"));
    }

    private Person returnPerson(Long id) {
        if (Objects.isNull(id)) {throw new CredentialsException("Necessario informar o ID do usuario");}
        return personRepository.findById(id).orElseThrow(() -> new FindByIdException("Usuario não encontrada"));
    }

//    private List<Product> returnListProducts(List<Long> productIds) {
//        var products = productRepository.findAllByIdIn(productIds);
//        if (products.size() != productIds.size()) {
//            throw new FindByIdException("Um ou mais produtos não foram encontrados");
//        }
//        return products;
//    }

    private PriceProduct returnPriceProduct(Long productId) {
        return priceProductRepository.findFirstByProduct_IdOrderByDateDesc(productId).orElseThrow(() ->
                new FindByIdException("Preço do produto não encontrado para o ID: " + productId));
    }

    private ContratoResponseDTO converte(Contrato contrato) {

        List<ContratoItensDTO> contratoItens = contrato.getContratoItens().stream()
                .map(this::converteContratoItens)
                .collect(Collectors.toList());

        return ContratoResponseDTO.builder()
                .id(contrato.getId())
                .date(contrato.getDate())
                .description(contrato.getDescription())
                .nameClient(contrato.getNameClient())
                .totalValueContrato(contrato.getTotalValueContrato())
                .labor(contrato.getLabor())
                .totalValueContrato(contrato.getTotalValueContrato())
                .contratoItens(contratoItens)
                .build();
    }

    public ContratoItensDTO converteContratoItens(ContratoItens contratoItens) {
        var productId = contratoItens.getProduct().getId();

        Optional<PriceProduct> priceProductcusto = priceProductRepository.findFirstByProduct_IdOrderByDateDesc(productId);
        var price = priceProductcusto.get().getPrice();

        return ContratoItensDTO.builder()
                .id(contratoItens.getProduct().getId())
                .name(contratoItens.getProduct().getName())
                .quantity(contratoItens.getQuantity())
                .priceSale(contratoItens.getValueProduct())
                .price(price)
                .build();
    }

    private void validateField(String value, String description) {
        if (Objects.isNull(value) || value.isBlank()) {throw new CredentialsException("Necessario informar "+ description);}
    }

    private void validateNumber(Float price, String description) {
        if (Objects.isNull(price) || price < 0 ) {throw new CredentialsException("Necessario informar " + description);}
    }

    private void validateRegisterDTO(RegisterContratoDTO registerContratoDTO) {
        validateField(registerContratoDTO.description(), "uma descrição para o contrato");
        validateField(registerContratoDTO.nameClient(), "o nome do Cliente");
        validateNumber(registerContratoDTO.labor(), "um valor para Mão de Obra");
    }

    private void validateUpdateDTO(UpdateContratoDTO updateContratoDTO) {
        validateField(updateContratoDTO.description(), "uma descrição para o contrato");
        validateField(updateContratoDTO.nameClient(), "o nome do Cliente");
        validateNumber(updateContratoDTO.labor(), "um valor para Mão de Obra");
    }

}