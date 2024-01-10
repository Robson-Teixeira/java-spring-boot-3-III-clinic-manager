package br.com.alura.clinic.manager.domain.consulta;

import br.com.alura.clinic.manager.domain.medico.MedicoRepository;
import br.com.alura.clinic.manager.domain.paciente.PacienteRepository;
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

        var medico = medicoRepository.getReferenceById(dadosAgendamentoConsulta.idMedico());
        var paciente = pacienteRepository.getReferenceById(dadosAgendamentoConsulta.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dadosAgendamentoConsulta.data());

        consultaRepository.save(consulta);

    }

}
