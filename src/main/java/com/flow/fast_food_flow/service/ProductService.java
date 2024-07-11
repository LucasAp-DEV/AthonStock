package com.flow.fast_food_flow.service;

import com.flow.fast_food_flow.domain.excessoes.CredentialsException;
import com.flow.fast_food_flow.domain.excessoes.FindByIdException;
import com.flow.fast_food_flow.domain.product.PriceProduct;
import com.flow.fast_food_flow.domain.product.Product;
import com.flow.fast_food_flow.domain.product.ProductType;
import com.flow.fast_food_flow.domain.product.RegisterProductDTO;
import com.flow.fast_food_flow.domain.store.Store;
import com.flow.fast_food_flow.repository.PriceProductRepository;
import com.flow.fast_food_flow.repository.ProductRepository;
import com.flow.fast_food_flow.repository.StoreRepository;
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
                data.productType(),
                data.productType() == ProductType.KG ? data.weight() : null,
                data.productType() == ProductType.UN ? data.quantity() : null
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
        validateField(data.name());
        validateType(data);
        validateNumber(data.price());
        validateType2(data);
    }
    private void validateField(String value) {
        if (Objects.isNull(value) || value.isBlank()) {throw new CredentialsException("Necessario informar o nome do produto");}
    }
    private void validateType(RegisterProductDTO value) {
        if (Objects.isNull(value.productType()) || value.productType().getValue().isBlank()) {throw new CredentialsException("Necessario informar o tipo do produto");}
    }
    private void validateType2(RegisterProductDTO value) {
        if (Objects.equals(value.productType().getValue(), "KG") && Objects.isNull(value.weight())) {throw new CredentialsException("Necessario informar o peso do produto");}
    }
    private void validateNumber(Float price) {
        if (Objects.isNull(price)) {throw new CredentialsException("Necessario informar o valor do produto");}
    }
}
