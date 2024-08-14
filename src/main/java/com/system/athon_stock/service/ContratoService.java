package com.system.athon_stock.service;

import com.system.athon_stock.domain.contrato.Contrato;
import com.system.athon_stock.domain.contrato.ContratoItens;
import com.system.athon_stock.domain.contrato.RegisterContratoDTO;
import com.system.athon_stock.domain.excessoes.CredentialsException;
import com.system.athon_stock.domain.excessoes.FindByIdException;
import com.system.athon_stock.domain.excessoes.ReturnNullException;
import com.system.athon_stock.domain.person.Person;
import com.system.athon_stock.domain.product.Product;
import com.system.athon_stock.repository.ContratoItensRepository;
import com.system.athon_stock.repository.ContratoRepository;
import com.system.athon_stock.repository.PersonRepository;
import com.system.athon_stock.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;
    private final PersonRepository personRepository;
    private final ProductRepository productRepository;
    private final ContratoItensRepository contratoItensRepository;

    public List<Contrato> findAllContratos(Long id) {
        var contrato = contratoRepository.findByPerson_id(id);
        if(contrato.isEmpty()){
            throw new ReturnNullException("Você não possui contratos no momento");
        }
        return contrato;
    }

    public void registerContrato(RegisterContratoDTO contratoDTO) {
        var person = returnPerson(contratoDTO.personId());
        var products = returnListProducts(contratoDTO.productId());
        Contrato contrato = new Contrato(contratoDTO.description(), contratoDTO.price(), person);
        contratoRepository.save(contrato);
        ContratoItens contratoItens = new ContratoItens(products, contrato);
        contratoItensRepository.save(contratoItens);
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
}