package com.flow.fast_food_flow.domain.product;

import com.flow.fast_food_flow.domain.merchandise.Merchandise;
import com.flow.fast_food_flow.domain.store.Store;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float price;
    private int quantity;
    private float peso;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "merchandis_id", referencedColumnName = "id")
    private Merchandise merchandise;

}
