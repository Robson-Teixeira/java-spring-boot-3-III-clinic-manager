package br.com.alura.clinic.manager.domain.consulta;

import br.com.alura.clinic.manager.domain.consulta.validacoes.ValidadorAgendamentoConsultas;
import br.com.alura.clinic.manager.domain.medico.Medico;
import br.com.alura.clinic.manager.domain.medico.MedicoRepository;
import br.com.alura.clinic.manager.domain.paciente.PacienteRepository;
import br.com.alura.clinic.manager.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaConsultas {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private List<ValidadorAgendamentoConsultas> validadorAgendamentoConsultas;

    public void agendar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {

        if (dadosAgendamentoConsulta.idMedico() != null &&
                !medicoRepository.existsById(dadosAgendamentoConsulta.idMedico()))
            throw new ValidacaoException("Id do médico informado não existe!");

        if (!pacienteRepository.existsById(dadosAgendamentoConsulta.idPaciente()))
            throw new ValidacaoException("Id do paciente informado não existe!");

        validadorAgendamentoConsultas.forEach(v -> v.validar(dadosAgendamentoConsulta));

        var medico = escolherMedico(dadosAgendamentoConsulta);
        var paciente = pacienteRepository.getReferenceById(dadosAgendamentoConsulta.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dadosAgendamentoConsulta.data(), null);

        consultaRepository.save(consulta);

    }

    private Medico escolherMedico(DadosAgendamentoConsulta dadosAgendamentoConsulta) {

        if (dadosAgendamentoConsulta.idMedico() != null)
            return medicoRepository.getReferenceById(dadosAgendamentoConsulta.idMedico());

        if (dadosAgendamentoConsulta.especialidade() == null)
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for selecionado!");

        return  medicoRepository.escolherMedicoAleatorioAtivoEspecialidadeDataLivre(dadosAgendamentoConsulta.especialidade(), dadosAgendamentoConsulta.data());

    }

    public void cancelar(DadosCancelamentoConsulta dadosCancelamentoConsulta) {

        if (!consultaRepository.existsById(dadosCancelamentoConsulta.idConsulta()))
            throw new ValidacaoException("Id da consulta informado não existe!");

        var consulta = consultaRepository.getReferenceById(dadosCancelamentoConsulta.idConsulta());
        consulta.cancelar(dadosCancelamentoConsulta.motivoCancelamento());

    }
}
