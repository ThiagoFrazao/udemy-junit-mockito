package br.udemy.business;

import br.udemy.exceptions.UnrecoverableException;
import br.udemy.models.Course;
import br.udemy.services.CourseService;
import org.apache.maven.surefire.shared.lang3.StringUtils;

import java.util.List;

public class CourseBusiness {

    private final CourseService courseService;

    public CourseBusiness(CourseService courseService) {
        this.courseService = courseService;
    }

    public List<Course> recuperarCursosPorAluno(String nomeAluno) {
        return this.courseService.recuperarCursosPorNomeAluno(nomeAluno);
    }

    public List<Course> recuperarCursosPorAlunoETema(String nomeAluno, String tema) {
        return this.recuperarCursosPorAluno(nomeAluno).stream()
                .filter(curso -> StringUtils.containsIgnoreCase(curso.getNome(), tema))
                .toList();
    }

    public Course recuperarCursoPorNome(String nomeCurso) {
        try {
            return this.courseService.recuperarCursoPorNomeCurso(nomeCurso);
        } catch (Exception e) {
            throw new UnrecoverableException(e.getMessage());
        }
    }

    public void adicionarNovoCurso(Course novoCurso) {
        this.courseService.adicionarCurso(novoCurso);
    }

}
