package com.flow.fast_food_flow.domain.receita;

import com.flow.fast_food_flow.domain.store.Store;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@Entity(name = "receita")
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @OneToMany(mappedBy = "receita",cascade = CascadeType.ALL)
    private List<ProductReceita> productReceitas = new ArrayList<>();
}
