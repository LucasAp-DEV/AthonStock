package com.system.casaroto.domain.product;

import com.system.casaroto.domain.store.Store;
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
    private String marca;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    public Product(String name, Store store,Integer quantity, String marca) {
        this.name = name;
        this.store = store;
        this.marca = marca;
        this.quantity = quantity;
    }
}