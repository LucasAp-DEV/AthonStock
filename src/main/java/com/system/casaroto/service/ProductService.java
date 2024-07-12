package com.system.casaroto.service;

import com.system.casaroto.domain.excessoes.CredentialsException;
import com.system.casaroto.domain.excessoes.FindByIdException;
import com.system.casaroto.domain.product.PriceProduct;
import com.system.casaroto.domain.product.Product;
import com.system.casaroto.domain.product.RegisterProductDTO;
import com.system.casaroto.domain.store.Store;
import com.system.casaroto.repository.PriceProductRepository;
import com.system.casaroto.repository.ProductRepository;
import com.system.casaroto.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final StoreRepository storeRepository;

    private final ProductRepository productRepository;

    private final PriceProductRepository priceProductRepository;

    public void saveProduct(RegisterProductDTO data) {
        var store = returnStore(data.storeId());
        validateBody(data);
        Product product = new Product(
                data.name(),
                store,
                data.quantity(),
                data.marca()
        );
        productRepository.save(product);
        PriceProduct priceProduct = new PriceProduct(data.price(), product);
        priceProductRepository.save(priceProduct);
    }

    public Store returnStore(Long id){
        if (Objects.isNull(id)) {throw new CredentialsException("Necessario informar o ID da store");}
        return storeRepository.findById(id).orElseThrow(() -> new FindByIdException("Store não encontrada"));
    }

    private void validateBody(RegisterProductDTO data) {
        validateField(data.name(), "o nome do produto");
        validateField(data.marca(), "a marca do produto");
        validateNumber(data.price());
    }
    private void validateField(String value, String description) {
        if (Objects.isNull(value) || value.isBlank()) {throw new CredentialsException("Necessario informar "+ description);}
    }
    private void validateNumber(Float price) {
        if (Objects.isNull(price)) {throw new CredentialsException("Necessario informar o valor do produto");}
    }
}
