package br.com.alura.clinic.manager.infra.exception;

public class ValidacaoExcepetion extends RuntimeException {
    public ValidacaoExcepetion(String mensagem) {
        super(mensagem);
    }
}
