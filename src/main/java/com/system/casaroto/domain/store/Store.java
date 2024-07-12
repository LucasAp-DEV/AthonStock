package com.system.casaroto.domain.store;

import com.system.casaroto.domain.pedido.Contrato;
import com.system.casaroto.domain.person.Person;
import com.system.casaroto.domain.product.Product;
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

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Contrato> order = new ArrayList<>();

}
