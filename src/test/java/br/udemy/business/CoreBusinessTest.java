package br.udemy.business;

import br.udemy.services.CourseService;
import br.udemy.services.CourseServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class CoreBusinessTest {

    static CourseBusiness courseBusiness;

    @BeforeAll
    static void setUpBefore() {
        final CourseService courseService = Mockito.mock(CourseServiceImpl.class);
        courseBusiness = new CourseBusiness(courseService);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Spring"})
    void testCoursesRelatedToStringWhenUsingMockito(String tema) {
        final String nomeAluno = RandomStringUtils.randomAlphabetic(10);
        Mockito.when(courseBusiness.recuperarCursosPorAlunoETema(nomeAluno, tema)).thenReturn(gerarCursosAluno());
        final List<String> cursos = courseBusiness.recuperarCursosPorAlunoETema(nomeAluno, tema);
        Assertions.assertFalse(cursos.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Oreilly"})
    void testCoursesRelatedToOreillyWhenUsingMockito(String tema) {
        final String nomeAluno = RandomStringUtils.random(10);
        Mockito.when(courseBusiness.recuperarCursosPorAlunoETema(nomeAluno, tema)).thenReturn(gerarCursosAluno());
        final List<String> cursos = courseBusiness.recuperarCursosPorAlunoETema(nomeAluno, tema);
        Assertions.assertTrue(cursos.isEmpty());
    }

    private List<String> gerarCursosAluno() {
        List<String> retorno = new ArrayList<>();
        final String basePar = "Udemy";
        final String baseImpar = "Spring";
        final String templateNome = "%s-%s";
        for(int i = 0; i <= 10; i++) {
            if(i%2 == 0) {
                retorno.add(templateNome.formatted(basePar , RandomStringUtils.random(10, basePar)));
            } else {
                retorno.add(templateNome.formatted(baseImpar, RandomStringUtils.random(10, baseImpar)));
            }
        }
        return retorno;
    }


}
