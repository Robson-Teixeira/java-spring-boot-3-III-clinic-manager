package br.com.alura.clinic.manager.domain.consulta.validacoes;

import br.com.alura.clinic.manager.domain.consulta.DadosAgendamentoConsulta;
import br.com.alura.clinic.manager.domain.paciente.PacienteRepository;
import br.com.alura.clinic.manager.infra.exception.ValidacaoException;

public class ValidadorPacienteAtivo {

    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {

        var pacienteAtivo = pacienteRepository.findAtivoById(dadosAgendamentoConsulta.idPaciente());

        if (!pacienteAtivo)
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído!");

    }

}
