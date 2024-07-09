package com.flow.fast_food_flow.domain.store;

import com.flow.fast_food_flow.domain.excessoes.CredentialsException;
import com.flow.fast_food_flow.domain.person.UpdatePersonDTO;
import com.flow.fast_food_flow.domain.receita.Revenue;
import com.flow.fast_food_flow.domain.pedido.Order;
import com.flow.fast_food_flow.domain.person.Person;
import com.flow.fast_food_flow.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private Boolean status;

    public Store(String name, String address, String cnpj,Boolean status, Person person) {
        this.name = name;
        this.address = address;
        this.cnpj = cnpj;
        this.status = status;
        this.person = person;
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
