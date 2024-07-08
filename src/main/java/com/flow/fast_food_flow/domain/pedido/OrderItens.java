package com.flow.fast_food_flow.domain.pedido;

import com.flow.fast_food_flow.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@RequiredArgsConstructor
@Entity(name = "pedido_itens")
public class OrderItens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    private Order pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Product produto;

}
