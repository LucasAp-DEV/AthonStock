package com.flow.fast_food_flow.domain.product;

import com.flow.fast_food_flow.domain.store.Store;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private Double weight; //peso
    private Integer quantity; //unidade

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    public Product(String name, Store store, ProductType productType, Double weight, Integer quantity) {
        this.name = name;
        this.store = store;
        this.productType = productType;
        if (productType == ProductType.KG) {
            this.weight = weight;
            this.quantity = null;
        } else if (productType == ProductType.UN) {
            this.quantity = quantity;
            this.weight = null;
        }
    }
}