package br.com.alura.clinic.manager.domain.medico;

import br.com.alura.clinic.manager.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        @Valid
        DadosEndereco dadosEndereco) {
}
