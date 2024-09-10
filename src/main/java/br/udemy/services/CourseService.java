package br.udemy.services;

import br.udemy.models.Course;

import java.util.List;

public interface CourseService {
    List<Course> recuperarCursosPorNomeAluno(String nomeAluno);

    Course recuperarCursoPorNomeCurso(String nomeCurso);

}
