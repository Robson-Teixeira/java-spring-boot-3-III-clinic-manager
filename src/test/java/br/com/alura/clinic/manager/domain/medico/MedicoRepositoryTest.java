package br.com.alura.clinic.manager.domain.medico;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

// Testar uma interface repository
@DataJpaTest
// Utilizar o mesmo banco de dados da aplicação ao invés de um banco de dados em memória
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// Utilizar o application-test.properties que sobrescreve o application.properties
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Test
    void escolherMedicoAleatorioAtivoEspecialidadeDataLivre() {
    }

}