package com.system.athon_stock.service;

import com.system.athon_stock.domain.excessoes.CredentialsException;
import com.system.athon_stock.domain.excessoes.FindByIdException;
import com.system.athon_stock.domain.person.Person;
import com.system.athon_stock.domain.product.*;
import com.system.athon_stock.repository.PersonRepository;
import com.system.athon_stock.repository.PriceProductRepository;
import com.system.athon_stock.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final PriceProductRepository priceProductRepository;
    private final PersonRepository personRepository;

    public void saveProduct(RegisterProductDTO data) {
        var person = returnPerson(data.personId());
        validateRegisterProduct(data);
        returnProductCode(data.code());
        Product product = new Product(data.name(), person,data.quantity(), data.marca(), data.code());
        productRepository.save(product);
        PriceProduct priceProduct = new PriceProduct(data.price(), data.lucro(), product);
        priceProductRepository.save(priceProduct);
    }

    public void updatePriceProduct(Long id, UpdatePriceProduct data){
        var product = returnProductId(id);
        validatePriceProduct(data);
        PriceProduct priceProduct = new PriceProduct(data.price(), data.lucro(), product);
        priceProductRepository.save(priceProduct);
    }

    public void updateProduct(Long id, UpdateProduct data){
        returnProductCode(data.code());
        var product = returnProductId(id);
            product.updateProduct(data);
            productRepository.save(product);
    }

    public List<ReturnProduct> returnProductAll(Long id){
        returnPerson(id);
        List<Product> productList = productRepository.findAll();
        List<ReturnProduct> returnProducts = new ArrayList<>();
        for(Product product : productList){
            returnProducts.add(converte(product));
        }
        return returnProducts;
    }

    public Product returnProductId(Long id){
        if (Objects.isNull(id)) {throw new CredentialsException("Necessario informar o ID do produto");}
        return productRepository.findById(id).orElseThrow(() -> new FindByIdException("Produto não encontrada"));
    }

    private void returnProductCode(String code){
        var productCode = productRepository.findByCode(code);
        if (productCode.isPresent())
            throw new CredentialsException("Código do produto ja esta em uso");
    }

    private Person returnPerson(Long id){
        if (Objects.isNull(id)) {throw new CredentialsException("Necessario informar o ID da pessoa");}
        return personRepository.findById(id).orElseThrow(() -> new FindByIdException("Pessoa não encontrada"));
    }

    private void validateRegisterProduct(RegisterProductDTO data) {
        validateField(data.name(), "o nome do produto");
        validateField(data.marca(), "a marca do produto");
        validateNumber(data.price(), "o valor do produto");
        validateNumber(data.lucro(), "o valor da venda do produto");
    }

    private void validatePriceProduct(UpdatePriceProduct data) {
        validateNumber(data.price(), "o valor do produto");
        validateNumber(data.lucro(), "o valor da venda do produto");
    }

    private void validateField(String value, String description) {
        if (Objects.isNull(value) || value.isBlank()) {throw new CredentialsException("Necessario informar "+ description);}
    }

    private void validateNumber(Float price, String description) {
        if (Objects.isNull(price)) {throw new CredentialsException("Necessario informar " + description);}
    }

    private ReturnProduct converte(Product product) {
        return ReturnProduct.builder()
                .name(product.getName())
                .marca(product.getMarca())
                .quantity(product.getQuantity())
                .status(product.getStatus())
                .code(product.getCode())
                .build();
    }
}
