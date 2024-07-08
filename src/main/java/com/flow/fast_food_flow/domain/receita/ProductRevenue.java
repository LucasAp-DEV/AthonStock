package com.flow.fast_food_flow.domain.receita;

import com.flow.fast_food_flow.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "product_receita")
public class ProductRevenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Product produto;

    @ManyToOne
    @JoinColumn(name = "receita_id", referencedColumnName = "id")
    private Revenue receita;
}
