package com.system.athon_stock.service;

import com.system.athon_stock.domain.contrato.*;
import com.system.athon_stock.domain.excessoes.CredentialsException;
import com.system.athon_stock.domain.excessoes.FindByIdException;
import com.system.athon_stock.domain.excessoes.ReturnNullException;
import com.system.athon_stock.domain.person.Person;
import com.system.athon_stock.domain.product.PriceProduct;
import com.system.athon_stock.domain.product.Product;
import com.system.athon_stock.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;
    private final PersonRepository personRepository;
    private final ProductRepository productRepository;
    private final ContratoItensRepository contratoItensRepository;
    private final PriceProductRepository priceProductRepository;

    public List<ContratoResponseDTO> findAllContratos(Long id) {
        returnPerson(id);
        List<Contrato> contratos = contratoRepository.findByPerson_id(id);
        List<ContratoResponseDTO> contratoResponseDTOs = new ArrayList<>();

        for (Contrato contrato : contratos) {
            contratoResponseDTOs.add(converte(contrato));
        }
        if(contratoResponseDTOs.isEmpty()){
            throw new ReturnNullException("Voce nao possui contratos no momento");
        }
        return contratoResponseDTOs;
    }

    public void registerContrato(RegisterContratoDTO contratoDTO) {
        var person = returnPerson(contratoDTO.personId());

        Contrato contrato = new Contrato(contratoDTO.description(), contratoDTO.labor(), person, contratoDTO.nameClient());
        contratoRepository.save(contrato);

        for (ProductListContrato productListContrato : contratoDTO.products()) {
            var product1 = returnProductId(productListContrato.id());
            PriceProduct priceProduct = returnPriceProduct(product1.getId());

            for (int i = 0; i < productListContrato.quantity(); i++) {
                ContratoItens contratoItens = new ContratoItens(product1, contrato, priceProduct);
                contratoItensRepository.save(contratoItens);
                contrato.getContratoItens().add(contratoItens);
            }
        }

        contrato.calculateTotalValueContrato();
        contratoRepository.save(contrato);
    }

    public Product returnProductId(Long id){
        if (Objects.isNull(id)) {throw new CredentialsException("Necessario informar o ID do produto");}
        return productRepository.findById(id).orElseThrow(() -> new FindByIdException("Produto não encontrada"));
    }

    private Person returnPerson(Long id){
        if (Objects.isNull(id)) {throw new CredentialsException("Necessario informar o ID do usuario");}
        return personRepository.findById(id).orElseThrow(() -> new FindByIdException("Usuario não encontrada"));
    }

    private List<Product> returnListProducts(List<Long> productIds){
        var products = productRepository.findAllByIdIn(productIds);
        if (products.size() != productIds.size()) {
            throw new FindByIdException("Um ou mais produtos não foram encontrados");
        }
        return products;
    }

    private PriceProduct returnPriceProduct(Long productId) {
        return priceProductRepository.findByProductId(productId).orElseThrow(() -> new FindByIdException("Preço do produto não encontrado para o ID: " + productId));
    }

    private ContratoResponseDTO converte(Contrato contrato) {
        return ContratoResponseDTO.builder()
                .id(contrato.getId())
                .date(contrato.getDate())
                .description(contrato.getDescription())
                .nameClient(contrato.getNameClient())
                .build();
    }

}