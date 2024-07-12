package com.system.casaroto.domain.contrato;

import com.system.casaroto.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@Entity(name = "contrato_itens")
public class ContratoItens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contrato_id", referencedColumnName = "id")
    private Contrato contrato;

    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Product produto;

}
