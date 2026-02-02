package com.exemplo.vendas.service;

import com.exemplo.vendas.dto.VendaRequest;
import com.exemplo.vendas.model.Venda;
import com.exemplo.vendas.repository.VendaRepository;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;

    public VendaService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    public Venda criarVenda(VendaRequest request) {
        Venda venda = new Venda(
            request.getDataVenda(),
            request.getValor(),
            request.getVendedorId(),
            request.getVendedorNome()
        );
        return vendaRepository.save(venda);
    }
}
