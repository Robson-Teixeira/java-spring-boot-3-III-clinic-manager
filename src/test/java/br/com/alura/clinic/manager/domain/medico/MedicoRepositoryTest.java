package br.com.alura.clinic.manager.domain.medico;

import br.com.alura.clinic.manager.domain.consulta.Consulta;
import br.com.alura.clinic.manager.domain.endereco.DadosEndereco;
import br.com.alura.clinic.manager.domain.paciente.DadosCadastroPaciente;
import br.com.alura.clinic.manager.domain.paciente.Paciente;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

// Testar uma interface repository
@DataJpaTest
// Utilizar o mesmo banco de dados da aplicação ao invés de um banco de dados em memória
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// Utilizar o application-test.properties que sobrescreve o application.properties
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Devolve null quando não há médico disponível na data")
    void escolherMedicoAleatorioAtivoEspecialidadeDataLivreCenario1() {

        // given ou arrange
        var proximaSegunda10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = cadastrarMedico("Médico", "medico@voll.med", "123456", Especialidade.Cardiologia);

        var paciente = cadastrarPaciente("Paciente", "paciente@email.com", "123.456.789-00");

        cadastrarConsulta(medico, paciente, proximaSegunda10);

        // when ou act
        var medicoLivre = medicoRepository.escolherMedicoAleatorioAtivoEspecialidadeDataLivre(Especialidade.Cardiologia, proximaSegunda10);

        // then ou assert
        assertThat(medicoLivre).isNull();

    }

    @Test
    @DisplayName("Devolve médico que está disponível na data")
    void escolherMedicoAleatorioAtivoEspecialidadeDataLivreCenario2() {

        // given ou arrange
        var proximaSegunda10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = cadastrarMedico("Médico", "medico@voll.med", "123456", Especialidade.Cardiologia);

        // when ou act
        var medicoLivre = medicoRepository.escolherMedicoAleatorioAtivoEspecialidadeDataLivre(Especialidade.Cardiologia, proximaSegunda10);

        // then ou assert
        assertThat(medicoLivre).isEqualTo(medico);

    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        testEntityManager.persist(new Consulta(null, medico, paciente, data, null));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        testEntityManager.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        testEntityManager.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedico(
                nome,
                email,
                "31999886655",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPaciente(
                nome,
                email,
                "31999112200",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "Rua R",
                "Bairro B",
                "123456000",
                "Cidade C",
                "UF",
                "13",
                "Casa"
        );
    }

}