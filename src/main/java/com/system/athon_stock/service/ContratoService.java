package com.system.athon_stock.service;

import com.system.athon_stock.domain.contrato.Contrato;
import com.system.athon_stock.repository.ContratoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ContratoService {

    public final ContratoRepository contratoRepository;

    public List<Contrato> findAllContratos(Long id) {
        return contratoRepository.findByPerson_id(id);
    }
}
