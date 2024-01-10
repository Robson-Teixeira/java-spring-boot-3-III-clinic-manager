package br.com.alura.clinic.manager.domain.consulta.validacoes;

import br.com.alura.clinic.manager.domain.consulta.DadosAgendamentoConsulta;
import br.com.alura.clinic.manager.domain.medico.MedicoRepository;
import br.com.alura.clinic.manager.infra.exception.ValidacaoException;

public class ValidadorMedicoAtivo {

    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {

        if (dadosAgendamentoConsulta.idMedico() == null)
            return;

        var medicoAtivo = medicoRepository.findAtivoById(dadosAgendamentoConsulta.idMedico());

        if (!medicoAtivo)
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluído!");

    }

}
