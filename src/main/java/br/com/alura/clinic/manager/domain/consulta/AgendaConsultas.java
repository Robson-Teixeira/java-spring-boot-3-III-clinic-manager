package br.com.alura.clinic.manager.domain.consulta;

import br.com.alura.clinic.manager.domain.medico.Medico;
import br.com.alura.clinic.manager.domain.medico.MedicoRepository;
import br.com.alura.clinic.manager.domain.paciente.PacienteRepository;
import br.com.alura.clinic.manager.infra.exception.ValidacaoExcepetion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaConsultas {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ConsultaRepository consultaRepository;

    public void agendar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {

        if (dadosAgendamentoConsulta.idMedico() != null &&
                !medicoRepository.existsById(dadosAgendamentoConsulta.idMedico()))
            throw new ValidacaoExcepetion("Id do médico informado não existe!");

        if (!pacienteRepository.existsById(dadosAgendamentoConsulta.idPaciente()))
            throw new ValidacaoExcepetion("Id do paciente informado não existe!");

        var medico = escolherMedico(dadosAgendamentoConsulta);
        var paciente = pacienteRepository.getReferenceById(dadosAgendamentoConsulta.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dadosAgendamentoConsulta.data());

        consultaRepository.save(consulta);

    }

    private Medico escolherMedico(DadosAgendamentoConsulta dadosAgendamentoConsulta) {

        if (dadosAgendamentoConsulta.idMedico() != null)
            return medicoRepository.getReferenceById(dadosAgendamentoConsulta.idMedico());

        if (dadosAgendamentoConsulta.especialidade() == null)
            throw new ValidacaoExcepetion("Especialidade é obrigatória quando médico não for selecionado!");

        return  medicoRepository.escolherMedicoAleatorioAtivoEspecialidadeDataLivre(dadosAgendamentoConsulta.especialidade(), dadosAgendamentoConsulta.data());

    }

}
