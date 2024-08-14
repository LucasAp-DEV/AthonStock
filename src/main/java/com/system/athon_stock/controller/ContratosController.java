package com.system.athon_stock.controller;

import com.system.athon_stock.domain.contrato.Contrato;
import com.system.athon_stock.domain.contrato.RegisterContratoDTO;
import com.system.athon_stock.service.ContratoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("contrato")
public class ContratosController {

    private final ContratoService contratosService;

    @PostMapping("/register")
    public void registerContrato(@RequestBody RegisterContratoDTO contratoDTO) {
        contratosService.registerContrato(contratoDTO);
    }

    @GetMapping("/user/{id}")
    public List<Contrato> getAllContratoById(@PathVariable Long id) {
        return contratosService.findAllContratos(id);
    }
}
