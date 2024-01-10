package br.com.alura.clinic.manager.domain.medico;

public record DadosListagemMedico(Long id, String nome, String email, String crm, Especilidade especialidade) {

    public DadosListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}
