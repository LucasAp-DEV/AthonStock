package com.flow.fast_food_flow.domain.receita;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@RequiredArgsConstructor
@Entity(name = "price_receita")
public class PriceRevenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float price;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "receita_id", referencedColumnName = "id")
    private Revenue receita;
}
