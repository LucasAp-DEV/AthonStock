package com.flow.fast_food_flow.domain.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class ProductKG {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private float kg;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

}
