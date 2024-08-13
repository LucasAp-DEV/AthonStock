package com.system.athon_stock.controller;

import com.system.athon_stock.domain.contrato.Contrato;
import com.system.athon_stock.service.ContratoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("contrato")
public class ContratosController {

    private final ContratoService contratosService;

    @GetMapping("/user/{id}")
    public List<Contrato> getAllContratoById(@PathVariable Long id) {
        return contratosService.findAllContratos(id);
    }
}
