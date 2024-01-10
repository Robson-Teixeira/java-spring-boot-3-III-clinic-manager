package br.com.alura.clinic.manager.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;

    public Endereco(DadosEndereco dadosEndereco) {
        this.logradouro = dadosEndereco.logradouro();
        this.bairro = dadosEndereco.bairro();
        this.cep = dadosEndereco.cep();
        this.cidade = dadosEndereco.cidade();
        this.uf = dadosEndereco.uf();
        this.numero = dadosEndereco.numero();
        this.complemento = dadosEndereco.complemento();
    }

    public void atualizar(DadosEndereco dadosEndereco) {
        if (dadosEndereco != null) {
            this.logradouro = dadosEndereco.logradouro() != null ? dadosEndereco.logradouro() : this.logradouro;
            this.bairro = dadosEndereco.bairro() != null ? dadosEndereco.bairro() : this.bairro;
            this.cep = dadosEndereco.cep() != null ? dadosEndereco.cep() : this.cep;
            this.cidade = dadosEndereco.cidade() != null ? dadosEndereco.cidade() : this.cidade;
            this.uf = dadosEndereco.uf() != null ? dadosEndereco.uf() : this.uf;
            this.numero = dadosEndereco.numero() != null ? dadosEndereco.numero() : this.numero;
            this.complemento = dadosEndereco.complemento() != null ? dadosEndereco.complemento() : this.complemento;
        }
    }
}
