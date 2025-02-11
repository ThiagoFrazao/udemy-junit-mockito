package br.udemy.mock.spring.controller;

import br.udemy.mock.spring.controladores.PessoaController;
import br.udemy.mock.spring.entidades.Pessoa;
import br.udemy.mock.spring.servicos.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


//WEB Mvc sobe apenas os Beans necessarios para testar o controlador em questao
//Melhor utilizado em testes unitarios
@WebMvcTest(PessoaController.class)
class PessoaControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaService pessoaService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private Pessoa pessoaMock;
    private long contadorPessoas;

    @BeforeEach
    void setUp() {
        final String nome = RandomStringUtils.randomAlphabetic(10);
        final String cpf = RandomStringUtils.randomNumeric(11);
        final String email = "%s@emailmock.com.br".formatted(RandomStringUtils.randomAlphabetic(10));
        this.pessoaMock = new Pessoa(nome, cpf, email);
        this.contadorPessoas++;
        this.pessoaMock.setId(contadorPessoas);
    }

    @Test
    void testSalvarPessoa() {
        try {
            Mockito.when(this.pessoaService.cadastrar(Mockito.any(Pessoa.class))).thenReturn(this.pessoaMock);
            ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/pessoa")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.objectMapper.writeValueAsString(this.pessoaMock)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(jsonPath("$.nome", CoreMatchers.is(this.pessoaMock.getNome())));

        } catch (Exception e) {

        }
    }


}
