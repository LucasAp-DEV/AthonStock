package com.system.athon_stock.domain.contrato;

import com.system.athon_stock.domain.product.PriceProduct;
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
    private Float valueProduct;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "contrato_id", referencedColumnName = "id")
    private Contrato contrato;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public ContratoItens(Product product, Contrato contrato, PriceProduct priceProduct, Integer quantity) {
        this.product = product;
        this.contrato = contrato;
        this.valueProduct = priceProduct.getPriceSale();
        this.quantity = quantity;
    }
}
