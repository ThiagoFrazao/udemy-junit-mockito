package br.udemy.models;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class Course {

    private final String nome;

    private final String tema;

    public Course(String nome) {
        this.nome = nome;
        this.tema = StringUtils.abbreviate(nome, 5);
    }

    public Course(String nome, String tema) {
        this.nome = nome;
        this.tema = tema;
    }

    @Override
    public String toString() {
        return "Course{" +
                "nome='" + nome + '\'' +
                ", tema='" + tema + '\'' +
                '}';
    }
}
