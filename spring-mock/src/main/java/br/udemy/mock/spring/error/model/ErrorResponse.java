package br.udemy.mock.spring.error.model;

import lombok.Getter;


@Getter
public class ErrorResponse {

    private final String mensagem;

    private final ErrorType tipo;

    public ErrorResponse(String mensagem, ErrorType tipo) {
        this.mensagem = mensagem;
        this.tipo = tipo;
    }

}