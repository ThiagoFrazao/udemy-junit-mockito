package br.udemy.services;

import java.util.List;

public interface CourseService {
    List<String> recuperarCursos(String nomeAluno);
}
