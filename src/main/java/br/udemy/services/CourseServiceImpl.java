package br.udemy.services;

import br.udemy.models.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CourseServiceImpl implements CourseService {

    private final HashMap<String, List<Course>> cursosAluno;

    private final List<Course> cursosDisponiveis;

    public CourseServiceImpl() {
        this.cursosAluno = new HashMap<>();
        this.cursosDisponiveis = new ArrayList<>();
    }

    @Override
    public List<Course> recuperarCursosPorNomeAluno(String nomeAluno) {
        return this.cursosAluno.computeIfAbsent(nomeAluno, k -> new ArrayList<>());
    }

    @Override
    public Course recuperarCursoPorNomeCurso(String nomeCurso) {
        return this.cursosDisponiveis.stream().filter(curso -> curso.getNome().equals(nomeCurso))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Curso nao encontrado %s".formatted(nomeCurso)));
    }

    @Override
    public boolean removerCurso(String nomeCurso) {
        return this.cursosDisponiveis.removeIf(curso -> curso.getNome().equals(nomeCurso));
    }

    @Override
    public void adicionarCurso(Course course) {
        this.cursosDisponiveis.add(course);
    }

}
