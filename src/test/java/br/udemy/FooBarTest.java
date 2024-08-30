package br.udemy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Stream;


class FooBarTest {

    public static String nomeTeste;
    public static Stack<String> testes;

    @BeforeAll
    static void setUp() {
        nomeTeste = "batata";
        testes = new Stack<>();
    }

    @BeforeEach
    void setUpBefore() {
        testes.push("Iniciando");
    }

    @Test
    @DisplayName("Teste falso")
    void testFoo() {
        System.out.println("Teste Foo");
        Assertions.assertThrows(ArithmeticException.class, () -> {
            int a = 10;
            int b = 0;
            int result = a / b;
        });
    }

    @Test
    void testCompareArray() {
        int[] arrayInicial = {5,4,3,2,1};
        int[] arrayFinal = {1,2,3,4,5};
        Arrays.sort(arrayInicial);
        Assertions.assertArrayEquals(arrayInicial, arrayFinal);
    }

    @ParameterizedTest
    @MethodSource("generatorForTestWithParameters")
    void testWithParametersFromMethodSource(Integer anoNascimento, Integer anoAtual, Integer idadeEsperada) {
        Assertions.assertEquals(anoAtual - anoNascimento, idadeEsperada);
    }

    @ParameterizedTest
    @CsvSource({
            "Thiago Frazao , 1993, 31",
            "Godofredo da Silva e Santos, 2009, 15"
    })
    void testWithParamethersFromCsvSource(String nome, Integer anoNascimento, Integer idadeEsperada) {
        System.out.println("Calculando idade de %s".formatted(nome));
        Assertions.assertEquals(LocalDate.now().getYear() - anoNascimento, idadeEsperada);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/tests/parametersForCsvTest.csv")
    void testWithParamethersFromCsvFile(String nome, Integer anoNascimento, Integer idadeEsperada) {
        System.out.println("Calculando idade de %s".formatted(nome));
        Assertions.assertEquals(LocalDate.now().getYear() - anoNascimento, idadeEsperada);
    }


    public static Stream<Arguments> generatorForTestWithParameters() {
        //Ano Nascimento - Ano Atual - Idade esperada
        return Stream.of(Arguments.of(1993, LocalDate.now().getYear(), 31));
    }


    @AfterEach
    void tearDownAfter() {
        testes.pop();
    }


    @AfterAll
    static void tearDown() {
        nomeTeste = null;
    }
    
}
