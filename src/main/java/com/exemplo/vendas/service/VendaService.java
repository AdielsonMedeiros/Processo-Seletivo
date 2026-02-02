package com.exemplo.vendas.service;

import com.exemplo.vendas.dto.VendaRequest;
import com.exemplo.vendas.dto.VendedorResumoDTO;
import com.exemplo.vendas.model.Venda;
import com.exemplo.vendas.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<VendedorResumoDTO> buscarRelatorioVendedores(LocalDate dataInicio, LocalDate dataFim) {
        List<Venda> vendas = vendaRepository.findByDataVendaBetween(dataInicio, dataFim);

        long diasNoPeriodo = ChronoUnit.DAYS.between(dataInicio, dataFim) + 1;

        Map<String, List<Venda>> vendasPorVendedor = vendas.stream()
            .collect(Collectors.groupingBy(Venda::getVendedorNome));

        return vendasPorVendedor.entrySet().stream()
            .map(entry -> {
                String nome = entry.getKey();
                List<Venda> vendasDoVendedor = entry.getValue();
                long totalVendas = vendasDoVendedor.size();

                BigDecimal mediaVendasDiarias = BigDecimal.valueOf(totalVendas)
                    .divide(BigDecimal.valueOf(diasNoPeriodo), 2, RoundingMode.HALF_UP);

                return new VendedorResumoDTO(nome, totalVendas, mediaVendasDiarias);
            })
            .collect(Collectors.toList());
    }
}
