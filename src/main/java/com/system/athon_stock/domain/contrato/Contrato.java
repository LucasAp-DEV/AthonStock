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
    private Double valueProducts;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContratoItens> contratoItens = new ArrayList<>();


    public Contrato(String description, Float labor, Person person) {
        this.description = description;
        this.labor = labor;
        this.person = person;
        this.date = LocalDate.now();
        this.valueProducts = 0.0;
    }

    public void calculateTotalValue() {
        this.valueProducts = contratoItens.stream()
                .mapToDouble(ContratoItens::getTotalValue)
                .sum();
    }
}
