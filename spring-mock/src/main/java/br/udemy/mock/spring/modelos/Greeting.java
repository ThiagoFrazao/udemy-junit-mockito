package br.udemy.mock.spring.modelos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Greeting {

    private final Long id;

    private final String content;

}