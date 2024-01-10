package br.com.alura.clinic.manager.domain.consulta.validacoes;

import br.com.alura.clinic.manager.domain.consulta.ConsultaRepository;
import br.com.alura.clinic.manager.domain.consulta.DadosAgendamentoConsulta;
import br.com.alura.clinic.manager.infra.exception.ValidacaoException;

public class ValidadorMedicoComOutraConsultaNoMesmoHorario {

    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {

        var medicoPossuiOutraConsultaNoMesmoHorario =
                consultaRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(
                        dadosAgendamentoConsulta.idMedico(), dadosAgendamentoConsulta.data());

        if (medicoPossuiOutraConsultaNoMesmoHorario)
            throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário");
        
    }

}
