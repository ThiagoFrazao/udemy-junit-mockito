package br.udemy.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class MockitoSpyTest {

    /**
     * Uso do SPY: faz um Mock parcial do Objeto mockado.
     * Cria um objeto real e faz mock apenas daquilo que for chamado.
     * Utilizar quando precisa controlar parte do comportamento do objeto, mas nao tudo.
     */
    @Test
    void test() {
        //given
        List<String> listMock = Mockito.spy(List.class);

        //when
        Assertions.assertEquals(0, listMock.size());
        Mockito.when(listMock.size()).thenReturn(10);
        listMock.add("NAO_FAZ_DIFERENCA");
        //MESMO ADICIONANDO UM ELEMENTO O SIZE AINDA RETORNA 10
        Assertions.assertEquals(10, listMock.size());

    }





}
