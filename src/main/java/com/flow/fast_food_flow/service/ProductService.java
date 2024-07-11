package com.flow.fast_food_flow.service;

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

@RequiredArgsConstructor
@Service
public class ProductService {

    private final StoreRepository storeRepository;

    private final ProductRepository productRepository;

    private final PriceProductRepository priceProductRepository;

    public void saveProduct(RegisterProductDTO data) {
        var store = returnStore(data.storeId());
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
       return storeRepository.findById(id).orElseThrow(() -> new FindByIdException("Store não encontrada"));
    }
}
