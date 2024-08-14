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
    private float price;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    public Contrato(String description, Float price, Person person) {
        this.description = description;
        this.price = price;
        this.person = person;
        this.date = LocalDate.now();
    }
}
