package com.flow.fast_food_flow.domain.pedido;

import com.flow.fast_food_flow.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@RequiredArgsConstructor
@Entity(name = "pedido_itens")
public class PedidoItens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Product produto;

}
