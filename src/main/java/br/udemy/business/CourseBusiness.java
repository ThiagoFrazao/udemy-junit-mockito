package br.udemy.business;

import br.udemy.services.CourseService;
import org.apache.maven.surefire.shared.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseBusiness {

    private final CourseService courseService;

    public CourseBusiness(CourseService courseService) {
        this.courseService = courseService;
    }

    public List<String> recuperarCursosPorAluno(String nomeAluno) {
        List<String> retorno = new ArrayList<>();
        return this.courseService.recuperarCursos(nomeAluno);
    }

    public List<String> recuperarCursosPorAlunoETema(String nomeAluno, String tema) {
        return this.recuperarCursosPorAluno(nomeAluno).stream()
                .filter(curso -> StringUtils.containsIgnoreCase(curso, tema))
                .collect(Collectors.toList());
    }

}
