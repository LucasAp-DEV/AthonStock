package com.system.casaroto.domain.product;

import com.system.casaroto.domain.excessoes.CredentialsException;
import com.system.casaroto.domain.store.Store;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String marca;
    private Integer quantity;
    private Boolean status;
    private String code;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    public Product(String name, Store store,Integer quantity, String marca, String code) {
        this.name = name;
        this.store = store;
        this.marca = marca;
        this.code = code;
        this.quantity = quantity;
        this.status = true;
    }

    private void validateField(String value, String description) {
        if (Objects.isNull(value) || value.isBlank()) {throw new CredentialsException("Necessario informar "+ description);}
    }

    private void validadestatus(Boolean status, String description) {
        if (Objects.isNull(status)) {throw new CredentialsException("Necessario informar " + description);}
    }

    public void updateProduct(UpdateProduct data){
        validateField(data.name(), "o nome do produto");
        validateField(data.marca(), "a marca do produto");
        validateField(data.code(), "o código do produto");
        validadestatus(data.status(), "o status do produto");
        this.name = data.name();
        this.marca = data.marca();
        this.status = data.status();
        this.code = data.code();
    }

}