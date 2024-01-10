package br.com.alura.clinic.manager.domain.medico;

import br.com.alura.clinic.manager.domain.endereco.Endereco;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String telefone, String crm, Especilidade especilidade, Endereco endereco) {

    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }

}
