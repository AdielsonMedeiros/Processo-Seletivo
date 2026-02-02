package com.exemplo.vendas;

import com.exemplo.vendas.model.Venda;
import com.exemplo.vendas.repository.VendaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VendaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VendaRepository vendaRepository;

    @Test
    void deveCriarVendaViaPost() throws Exception {
        String json = """
            {
                "dataVenda": "2026-02-01",
                "valor": 100.00,
                "vendedorId": 1,
                "vendedorNome": "Joao"
            }
            """;

        mockMvc.perform(post("/vendas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.vendedorNome").value("Joao"))
            .andExpect(jsonPath("$.valor").value(100.00));
    }

    @Test
    void deveBuscarRelatorioDeVendedores() throws Exception {
        vendaRepository.deleteAll();

        vendaRepository.save(new Venda(LocalDate.of(2026, 1, 1), new BigDecimal("100.00"), 1L, "Carlos"));
        vendaRepository.save(new Venda(LocalDate.of(2026, 1, 2), new BigDecimal("200.00"), 1L, "Carlos"));
        vendaRepository.save(new Venda(LocalDate.of(2026, 1, 2), new BigDecimal("150.00"), 2L, "Ana"));

        mockMvc.perform(get("/vendas/vendedores")
                .param("dataInicio", "2026-01-01")
                .param("dataFim", "2026-01-02"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2));
    }
}
