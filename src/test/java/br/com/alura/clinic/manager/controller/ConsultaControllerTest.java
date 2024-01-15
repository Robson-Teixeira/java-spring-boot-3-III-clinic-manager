package br.com.alura.clinic.manager.controller;

import br.com.alura.clinic.manager.domain.consulta.AgendaConsultas;
import br.com.alura.clinic.manager.domain.consulta.DadosAgendamentoConsulta;
import br.com.alura.clinic.manager.domain.consulta.DadosDetalhamentoConsulta;
import br.com.alura.clinic.manager.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJacksonTester;
    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJacksonTester;
    @MockBean
    private AgendaConsultas agendaConsultas;

    @Test
    @DisplayName("Erro 400 - Informações inválidas")
    // Considera que há um usuário logado
    @WithMockUser
    void agendarCenario1() throws Exception {

        // when ou act
        var response = mockMvc.perform(post("/consultas")).andReturn().getResponse();

        // then ou assert
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("Sucesso 200 - Informações válidas")
    @WithMockUser
    void agendarCenario2() throws Exception {

        // given ou arrange
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.Cardiologia;
        var dadosDetalhamentoConsulta = new DadosDetalhamentoConsulta(null, 1L, 1L, data);
        var jsonEsperado = dadosDetalhamentoConsultaJacksonTester.write(dadosDetalhamentoConsulta).getJson();

        when(agendaConsultas.agendar(any())).thenReturn(dadosDetalhamentoConsulta);

        // when ou act
        var response = mockMvc.perform(
                post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAgendamentoConsultaJacksonTester.write(
                                new DadosAgendamentoConsulta(1L, 1L, data, especialidade)
                        ).getJson())
        ).andReturn().getResponse();

        // then ou assert
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

    }

}