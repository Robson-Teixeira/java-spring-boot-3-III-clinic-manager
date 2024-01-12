package br.com.alura.clinic.manager.domain.consulta.validacoes;

import br.com.alura.clinic.manager.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoConsultas {
    void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta);
}
