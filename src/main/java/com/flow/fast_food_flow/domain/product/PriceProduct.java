package com.flow.fast_food_flow.domain.product;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "price_product")
public class PriceProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private float price;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public PriceProduct(float price, Product product) {
        this.price = price;
        this.date = LocalDate.now();
        this.product = product;
    }

}