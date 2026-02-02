package com.exemplo.vendas.controller;

import com.exemplo.vendas.dto.VendaRequest;
import com.exemplo.vendas.model.Venda;
import com.exemplo.vendas.service.VendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
