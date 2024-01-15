package br.com.alura.clinic.manager.domain.consulta.validacoes.cancelamento;

import br.com.alura.clinic.manager.domain.consulta.ConsultaRepository;
import br.com.alura.clinic.manager.domain.consulta.DadosCancelamentoConsulta;
import br.com.alura.clinic.manager.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosCancelamentoConsulta dadosCancelamentoConsulta) {

        var consulta = consultaRepository.getReferenceById(dadosCancelamentoConsulta.idConsulta());
        var dataAgora = LocalDateTime.now();
        var diferencaHoras = Duration.between(dataAgora, consulta.getData()).toHours();

        if (diferencaHoras < 24)
            throw new ValidacaoException("Consulta pode ser cancelada somente com antecedência mínima de 24h!");

    }
}