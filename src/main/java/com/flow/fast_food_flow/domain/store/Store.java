package com.flow.fast_food_flow.domain.store;

import com.flow.fast_food_flow.domain.receita.Revenue;
import com.flow.fast_food_flow.domain.pedido.Order;
import com.flow.fast_food_flow.domain.person.Person;
import com.flow.fast_food_flow.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@Entity(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String cnpj;

    public Store(String name, String address, String cnpj, Person userId) {
        this.name = name;
        this.address = address;
        this.cnpj = cnpj;
        this.person = userId;
    }

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @OneToMany(mappedBy = "store",cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "store",cascade = CascadeType.ALL)
    private List<Revenue> revenues = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Order> order = new ArrayList<>();
}
