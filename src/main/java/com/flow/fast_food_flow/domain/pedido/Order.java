package com.flow.fast_food_flow.domain.pedido;

import com.flow.fast_food_flow.domain.store.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@Entity(name = "pedido")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDate date;
    private float price;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<OrderItens> pedidoItens = new ArrayList<>();

}
