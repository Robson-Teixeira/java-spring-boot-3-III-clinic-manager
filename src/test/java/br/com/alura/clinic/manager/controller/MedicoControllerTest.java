package br.com.alura.clinic.manager.controller;

import br.com.alura.clinic.manager.domain.endereco.DadosEndereco;
import br.com.alura.clinic.manager.domain.endereco.Endereco;
import br.com.alura.clinic.manager.domain.medico.*;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<DadosCadastroMedico> dadosCadastroMedicoJacksonTester;
    @Autowired
    private JacksonTester<DadosDetalhamentoMedico> dadosDetalhamentoMedicoJacksonTester;

    @MockBean
    private MedicoRepository medicoRepository;

    @Test
    @DisplayName("Erro 400 - Informações inválidas")
    @WithMockUser
    void cadastrarCenario1() throws Exception {

        var response = mockMvc.perform(
                        post("/medicos"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Sucesso 200 - Informações válidas")
    @WithMockUser
    void cadastrarCenario2() throws Exception {

        // given ou arrange
        var dadosCadastroMedico = new DadosCadastroMedico(
                "Medico",
                "medico@voll.med",
                "31999886655",
                "123456",
                Especialidade.Cardiologia,
                dadosEndereco());
        var dadosDetalhamentoMedico = new DadosDetalhamentoMedico(
                null,
                dadosCadastroMedico.nome(),
                dadosCadastroMedico.email(),
                dadosCadastroMedico.telefone(),
                dadosCadastroMedico.crm(),
                dadosCadastroMedico.especialidade(),
                new Endereco(dadosCadastroMedico.endereco())
        );
        var jsonEsperado = dadosDetalhamentoMedicoJacksonTester.write(dadosDetalhamentoMedico).getJson();

        when(medicoRepository.save(any())).thenReturn(new Medico(dadosCadastroMedico));

        // when ou act
        var response = mockMvc.perform(
                post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroMedicoJacksonTester.write(
                                dadosCadastroMedico
                        ).getJson())
        ).andReturn().getResponse();

        // then ou assert
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "Rua R",
                "Bairro B",
                "12345600",
                "Cidade C",
                "UF",
                "13",
                "Casa"
        );
    }

}