package com.exemplo.vendas;

import com.exemplo.vendas.dto.VendaRequest;
import com.exemplo.vendas.dto.VendedorResumoDTO;
import com.exemplo.vendas.model.Venda;
import com.exemplo.vendas.repository.VendaRepository;
import com.exemplo.vendas.service.VendaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VendaServiceTest {

    @Mock
    private VendaRepository vendaRepository;

    @InjectMocks
    private VendaService vendaService;

    @Test
    void deveCriarVendaComSucesso() {
        VendaRequest request = new VendaRequest();
        request.setDataVenda(LocalDate.of(2026, 2, 1));
        request.setValor(new BigDecimal("100.00"));
        request.setVendedorId(1L);
        request.setVendedorNome("Joao");

        Venda vendaSalva = new Venda(
            request.getDataVenda(),
            request.getValor(),
            request.getVendedorId(),
            request.getVendedorNome()
        );
        vendaSalva.setId(1L);

        when(vendaRepository.save(any(Venda.class))).thenReturn(vendaSalva);

        Venda resultado = vendaService.criarVenda(request);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Joao", resultado.getVendedorNome());
        assertEquals(new BigDecimal("100.00"), resultado.getValor());
    }

    @Test
    void deveRetornarRelatorioDeVendedores() {
        LocalDate dataInicio = LocalDate.of(2026, 2, 1);
        LocalDate dataFim = LocalDate.of(2026, 2, 2);

        Venda venda1 = new Venda(dataInicio, new BigDecimal("100.00"), 1L, "Joao");
        Venda venda2 = new Venda(dataFim, new BigDecimal("200.00"), 1L, "Joao");
        Venda venda3 = new Venda(dataFim, new BigDecimal("150.00"), 2L, "Maria");

        when(vendaRepository.findByDataVendaBetween(dataInicio, dataFim))
            .thenReturn(Arrays.asList(venda1, venda2, venda3));

        List<VendedorResumoDTO> resultado = vendaService.buscarRelatorioVendedores(dataInicio, dataFim);

        assertEquals(2, resultado.size());

        VendedorResumoDTO joao = resultado.stream()
            .filter(v -> v.getNome().equals("Joao"))
            .findFirst()
            .orElse(null);

        VendedorResumoDTO maria = resultado.stream()
            .filter(v -> v.getNome().equals("Maria"))
            .findFirst()
            .orElse(null);

        assertNotNull(joao);
        assertEquals(2L, joao.getTotalVendas());
        assertEquals(new BigDecimal("1.00"), joao.getMediaVendasDiarias());

        assertNotNull(maria);
        assertEquals(1L, maria.getTotalVendas());
        assertEquals(new BigDecimal("0.50"), maria.getMediaVendasDiarias());
    }
}
