package br.udemy.mock.spring.error.exceptions;

public class FalhaBancoDados extends RuntimeException {

    public FalhaBancoDados(Exception e) {
        super(e);
    }

    public FalhaBancoDados(String message) {
        super(message);
    }

}
