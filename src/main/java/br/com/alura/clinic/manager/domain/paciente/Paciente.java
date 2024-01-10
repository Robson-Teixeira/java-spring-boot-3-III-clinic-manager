package br.com.alura.clinic.manager.domain.paciente;

import br.com.alura.clinic.manager.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Paciente(DadosCadastroPaciente dadosCadastroPaciente) {
        this.nome = dadosCadastroPaciente.nome();
        this.email = dadosCadastroPaciente.email();
        this.telefone = dadosCadastroPaciente.telefone();
        this.cpf = dadosCadastroPaciente.cpf();
        this.endereco = new Endereco(dadosCadastroPaciente.endereco());
        this.ativo = true;
    }

    public void atualizar(DadosAtualizacaoPaciente dadosAtualizacaoPaciente) {
        if (dadosAtualizacaoPaciente != null) {
            this.nome = dadosAtualizacaoPaciente.nome() != null ? dadosAtualizacaoPaciente.nome() : this.nome;
            this.telefone = dadosAtualizacaoPaciente.telefone() != null ? dadosAtualizacaoPaciente.telefone() : this.telefone;
            this.endereco.atualizar(dadosAtualizacaoPaciente.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
