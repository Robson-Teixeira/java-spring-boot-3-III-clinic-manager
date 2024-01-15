package br.com.alura.clinic.manager.domain.consulta.validacoes.agendamento;

import br.com.alura.clinic.manager.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoConsultas {
    void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta);
}
