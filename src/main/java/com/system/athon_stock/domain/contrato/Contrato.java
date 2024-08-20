package com.system.athon_stock.domain.contrato;

import com.system.athon_stock.domain.person.Person;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@Entity(name = "contrato")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDate date;
    private Float labor;
    private Float valueProducts;
    private String nameClient;
    private Float totalValueContrato;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContratoItens> contratoItens = new ArrayList<>();


    public Contrato(String description, Float labor, Person person, String nameClient) {
        this.description = description;
        this.labor = labor;
        this.person = person;
        this.date = LocalDate.now();
        this.valueProducts = 0.0F;
        this.nameClient = nameClient;
    }

    public Float calculateTotalValueProduct() {
        this.valueProducts = contratoItens.stream()
                .map(item -> item.getValueProduct() * item.getQuantity())
                .reduce(0f, Float::sum);
        return this.valueProducts;
    }

    public void calculateTotalValueContrato() {
        Float totalProducts = calculateTotalValueProduct();
        this.totalValueContrato = totalProducts + this.labor;
    }
}
