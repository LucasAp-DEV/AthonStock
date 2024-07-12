package com.system.casaroto.domain.contrato;

import com.system.casaroto.domain.store.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@Entity(name = "contrato")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDate date;
    private float price;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL)
    private List<ContratoItens> contratoItens = new ArrayList<>();

}
