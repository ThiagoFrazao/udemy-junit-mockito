package br.udemy.mock.spring.error.exceptions;

public class FalhaDadosInvalidosBancoException extends FalhaBancoDados {

    public FalhaDadosInvalidosBancoException(Exception e) {
        super(e);
    }

    public FalhaDadosInvalidosBancoException(String tabela, String message) {
        super("Falha ao consultar a tabela  %s. Msg: %s".formatted(tabela, message));
    }
}
