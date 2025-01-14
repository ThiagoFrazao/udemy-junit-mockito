package br.udemy.mockito;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class HamcrestTest {

    /**
     * Testes do Hamscrest: exemplos com Matchers
     */
    @Test
    void testMatchers() {
        //GIVEN
        List<Integer> lista = Arrays.asList(123, 456, 678);

        //THEN
        //List Integer
        MatcherAssert.assertThat(lista, CoreMatchers.hasItems(123, 456, 678));
        MatcherAssert.assertThat(lista, Matchers.hasSize(3));
        MatcherAssert.assertThat(lista, Matchers.everyItem(Matchers.greaterThan(100)));

        //Strings
        String vazio = StringUtils.EMPTY;
        MatcherAssert.assertThat(vazio, Matchers.is(Matchers.emptyString()));
        MatcherAssert.assertThat(null, Matchers.is(Matchers.emptyOrNullString()));

        //Array
        Integer[] listaArray = new Integer[]{123, 456, 678};
        MatcherAssert.assertThat(listaArray, Matchers.arrayWithSize(3));
        MatcherAssert.assertThat(listaArray, Matchers.arrayContaining(123, 456, 678));
        MatcherAssert.assertThat(listaArray, Matchers.arrayContainingInAnyOrder(456,678, 123));
    }


}
