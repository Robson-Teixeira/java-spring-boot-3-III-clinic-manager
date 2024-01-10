package br.com.alura.clinic.manager.domain.medico;

import br.com.alura.clinic.manager.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Medico")
@Table(name = "medicos")
@Getter // Geração dos métodos getters
@NoArgsConstructor // Construtor padrão (sem argumentos) exigido pela JPA
@AllArgsConstructor // Construtor com todos os campos/propriedades
@EqualsAndHashCode(of = "id") // Gera métodos equals e hashCode para id
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Medico(DadosCadastroMedico dadosCadastroMedico) {
        this.nome = dadosCadastroMedico.nome();
        this.email = dadosCadastroMedico.email();
        this.telefone = dadosCadastroMedico.telefone();
        this.crm = dadosCadastroMedico.crm();
        this.especialidade = dadosCadastroMedico.especialidade();
        this.endereco = new Endereco(dadosCadastroMedico.endereco());
        this.ativo = true;
    }

    public void atualizar(DadosAtualizacaoMedico dadosAtualizacaoMedico) {
        if (dadosAtualizacaoMedico != null) {
            this.nome = dadosAtualizacaoMedico.nome() != null ? dadosAtualizacaoMedico.nome() : this.nome;
            this.telefone = dadosAtualizacaoMedico.telefone() != null ? dadosAtualizacaoMedico.telefone() : this.telefone;
            this.endereco.atualizar(dadosAtualizacaoMedico.dadosEndereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
