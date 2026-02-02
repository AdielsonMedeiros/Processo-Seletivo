package com.exemplo.vendas.controller;

import com.exemplo.vendas.dto.VendaRequest;
import com.exemplo.vendas.dto.VendedorResumoDTO;
import com.exemplo.vendas.model.Venda;
import com.exemplo.vendas.service.VendaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping
    public ResponseEntity<Venda> criarVenda(@RequestBody VendaRequest request) {
        Venda venda = vendaService.criarVenda(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(venda);
    }

    @GetMapping("/vendedores")
    public ResponseEntity<List<VendedorResumoDTO>> buscarRelatorioVendedores(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        List<VendedorResumoDTO> relatorio = vendaService.buscarRelatorioVendedores(dataInicio, dataFim);
        return ResponseEntity.ok(relatorio);
    }
}
