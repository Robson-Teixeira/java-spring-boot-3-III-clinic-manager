package br.com.alura.clinic.manager.domain.consulta.validacoes;

import br.com.alura.clinic.manager.domain.consulta.DadosAgendamentoConsulta;
import br.com.alura.clinic.manager.infra.exception.ValidacaoException;

import java.time.Duration;
import java.time.LocalDate;

public class ValidadorHorarioAntecedencia {

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {

        var dataConsulta = dadosAgendamentoConsulta.data();
        var dataAgora = LocalDate.now();
        var diferencaMinutos = Duration.between(dataAgora, dataConsulta).toMinutes();

        if (diferencaMinutos < 30)
            throw new ValidacaoException("Consulta deve ser agendada com antecedência de 30 minutos!!");

    }

}
