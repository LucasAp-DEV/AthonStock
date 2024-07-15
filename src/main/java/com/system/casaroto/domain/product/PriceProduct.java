package com.system.casaroto.domain.product;

import com.system.casaroto.domain.excessoes.CredentialsException;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "price_product")
public class PriceProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float price;
    private Float lucro;
    private Float priceSale;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public PriceProduct(Float price, Float lucro, Product product) {
        this.price = price;
        this.date = LocalDate.now();
        this.product = product;
        this.lucro = lucro;
        this.priceSale = price * (1 + (lucro / 100));
    }
}