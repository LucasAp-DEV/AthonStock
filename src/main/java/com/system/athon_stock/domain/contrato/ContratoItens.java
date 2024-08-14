package com.system.athon_stock.domain.contrato;

import com.system.athon_stock.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @ManyToMany
    @JoinTable(name = "contrato_itens_products", joinColumns = @JoinColumn(name = "contrato_itens_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public ContratoItens(List<Product> products, Contrato contrato) {
        this.products = products;
        this.contrato = contrato;
    }
}
