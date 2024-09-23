package com.system.athon_stock.controller;

import com.system.athon_stock.domain.contrato.ContratoResponseDTO;
import com.system.athon_stock.domain.contrato.RegisterContratoDTO;
import com.system.athon_stock.domain.contrato.UpdateContratoDTO;
import com.system.athon_stock.service.ContratoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public List<ContratoResponseDTO> getAllContratoById(@PathVariable Long id) {
        return contratosService.findAllContratos(id);
    }

    @PutMapping("/update/{id}")
    public void updateContrato(@PathVariable (value = "id") Long id, @RequestBody UpdateContratoDTO updateContratoDTO) {
        contratosService.updateContratoId(id, updateContratoDTO);
    }

    @GetMapping("/user/findByData/{id}")
    public List<ContratoResponseDTO> getAllContratoFindByData(@PathVariable Long id,
                                                              @RequestParam("dataInicio") LocalDate dataInicio,
                                                              @RequestParam("dataFim") LocalDate dataFim) {
        return contratosService.findAllContratosByData(id, dataInicio, dataFim);
    }

    @GetMapping("/user/name/client/{id}")
    public List<ContratoResponseDTO> getAllContratoFindByNameClient(@PathVariable Long id,
                                                              @RequestParam("nameClient") String nameClient) {
        return contratosService.findAllContratosByNameClient(id, nameClient);
    }
}
