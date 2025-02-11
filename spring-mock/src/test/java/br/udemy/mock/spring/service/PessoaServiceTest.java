package br.udemy.mock.spring.service;

import br.udemy.mock.spring.entidades.Pessoa;
import br.udemy.mock.spring.repository.PessoaRepository;
import br.udemy.mock.spring.servicos.PessoaService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    private Pessoa pessoaMock;

    @BeforeEach
    public void criarPessoaMock() {
        final String nome = RandomStringUtils.randomAlphabetic(10);
        final String cpf = RandomStringUtils.randomNumeric(11);
        final String email = RandomStringUtils.randomAlphabetic(10);
        this.pessoaMock = new Pessoa(nome, cpf, email);
    }

    @Test
    void salvarPessoa() {
        Mockito.when(this.pessoaRepository.save(Mockito.any(Pessoa.class))).thenReturn(this.pessoaMock);
        final Pessoa cadastrada = this.pessoaService.cadastrar(this.pessoaMock);
        Assertions.assertNotNull(cadastrada);
        Assertions.assertEquals(cadastrada.getCpf(), this.pessoaMock.getCpf());
    }

    void falhaSalvarPessoaMesmoCpf() {
        Mockito.when(this.pessoaRepository.save(Mockito.any(Pessoa.class))).thenReturn(this.pessoaMock);
    }


}
