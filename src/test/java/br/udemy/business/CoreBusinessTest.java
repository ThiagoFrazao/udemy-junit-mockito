package br.udemy.business;

import br.udemy.exceptions.UnrecoverableException;
import br.udemy.models.Course;
import br.udemy.services.CourseService;
import br.udemy.services.CourseServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class CoreBusinessTest {

    static CourseBusiness courseBusiness;
    static CourseService courseService;

    @BeforeAll
    static void setUpBefore() {
        courseService = Mockito.mock(CourseServiceImpl.class);
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

    ///Verify: testar metodos sem retorno (void) - verifica se ao chamar um metodo do "Business" o metodo correto do
    ///Service sera chamado com os parametros corretos!
    ///Mockito.times verifica quantas vezes o metodo esperado pelo Mockito.verify foi chamado
    @Test
    void testVerifyAddCourseWithoutReturn() {
        Course course = new Course("CursoNovo","GERENCIADOR");
        courseBusiness.adicionarNovoCurso(course);
        Mockito.verify(courseService, Mockito.times(1)).adicionarCurso(course);
    }

    ///then: testar metodos sem retorno (void) - verifica se ao chamar um metodo do "Business" o metodo correto do
    ///Service sera chamado com os parametros corretos!
    ///Should - verifica se o metodo seguinte foi chamado
    @Test
    void testThenAddCourseWithoutReturn() {
        Course course = new Course("CursoNovo","GERENCIADOR");
        courseBusiness.adicionarNovoCurso(course);
        BDDMockito.then(courseService).should().adicionarCurso(course);
    }

    ///then: testar metodos sem retorno (void) - verifica se ao chamar um metodo do "Business" o metodo correto do
    ///Service sera chamado com os parametros corretos!
    ///Should com Never - verifica se o metodo foi chamado, mas com um resultado diferente
    @Test
    void testThenNeverAddCourseDifferentName() {
        Course course = new Course("CursoNovo","GERENCIADOR");
        courseBusiness.adicionarNovoCurso(course);
        BDDMockito.then(courseService).should(BDDMockito.never()).adicionarCurso(new Course("curso diferente"));
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
