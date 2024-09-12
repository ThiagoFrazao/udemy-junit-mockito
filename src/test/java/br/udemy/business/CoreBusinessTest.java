package br.udemy.business;

import br.udemy.exceptions.UnrecoverableException;
import br.udemy.models.Course;
import br.udemy.services.CourseService;
import br.udemy.services.CourseServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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
        final List<Course> cursos = courseBusiness.recuperarCursosPorAlunoETema(nomeAluno, tema);
        Assertions.assertFalse(cursos.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Oreilly"})
    void testCoursesRelatedToOreillyWhenUsingMockito(String tema) {
        final String nomeAluno = RandomStringUtils.random(10);
        Mockito.when(courseBusiness.recuperarCursosPorAlunoETema(nomeAluno, tema)).thenReturn(gerarCursosAluno());
        final List<Course> cursos = courseBusiness.recuperarCursosPorAlunoETema(nomeAluno, tema);
        Assertions.assertTrue(cursos.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Oreilly"})
    void testCoursesRelatedToOreillyTwoThenWhenUsingMockito(String tema) {
        final String nomeAluno = RandomStringUtils.random(10);
        Mockito.when(courseBusiness.recuperarCursosPorAlunoETema(nomeAluno, tema))
                .thenReturn(gerarCursosAluno())
                .thenReturn(gerarCursosAluno(tema, tema));
        List<Course> cursos = courseBusiness.recuperarCursosPorAlunoETema(nomeAluno, tema);
        Assertions.assertTrue(cursos.isEmpty());
        cursos = courseBusiness.recuperarCursosPorAlunoETema(nomeAluno, tema);
        Assertions.assertEquals(10, cursos.size());
    }

    @ParameterizedTest
    @ValueSource(strings = {"TesteTematico"})
    void testCourseNameWithArgumentMatcher(String temaCurso) {
        final String nomeCurso = RandomStringUtils.random(10);
        List<Course> listaCursos = Mockito.mock(List.class);
        Mockito.when(listaCursos.get(Mockito.anyInt()))
                .thenReturn(new Course(nomeCurso, temaCurso));
        final Course course = listaCursos.get(0);
        Assertions.assertNotNull(course);
        Assertions.assertEquals(nomeCurso, course.getNome());
        Assertions.assertEquals(temaCurso, course.getTema());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Falha esperada do teste"})
    void testCourseNameThrownsUnrecoverableException(String errorMsg) {
        try {
            final String nomeCurso = "FALHA_CURSO";
            Mockito.when(courseBusiness.recuperarCursoPorNome(nomeCurso))
                    .thenThrow(new UnrecoverableException(errorMsg));
            courseBusiness.recuperarCursoPorNome(nomeCurso);
            Assertions.fail("Deveria ter lancado uma exception %s".formatted(UnrecoverableException.class.getSimpleName()));
        } catch (Exception e) {
            Assertions.assertEquals(UnrecoverableException.class, e.getClass());
            Assertions.assertEquals(errorMsg, e.getMessage());
        }
    }

    void testDeleteCourseWithoutReturn() {

    }

    private List<Course> gerarCursosAluno() {
        return gerarCursosAluno("Udemy", "Spring");
    }

    private List<Course> gerarCursosAluno(String basePar, String baseImpar) {
        List<Course> retorno = new ArrayList<>();
        final String templateNome = "%s-%s";
        for(int i = 0; i < 10; i++) {
            if(i%2 == 0) {
                retorno.add(new Course(templateNome.formatted(basePar , RandomStringUtils.random(10, basePar))));
            } else {
                retorno.add(new Course(templateNome.formatted(baseImpar, RandomStringUtils.random(10, baseImpar))));
            }
        }
        return retorno;
    }


}
