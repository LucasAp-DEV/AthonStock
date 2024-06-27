package com.flow.fast_food_flow.domain.store;

import com.flow.fast_food_flow.domain.merchandise.Merchandise;
import com.flow.fast_food_flow.domain.pedido.Pedido;
import com.flow.fast_food_flow.domain.person.Person;
import com.flow.fast_food_flow.domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String cnpj;

    @ManyToOne
    @JoinColumn(name = "proprietario_id", referencedColumnName = "id")
    private Person proprietario;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Merchandise> merchandise = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Pedido> order = new ArrayList<>();

}
