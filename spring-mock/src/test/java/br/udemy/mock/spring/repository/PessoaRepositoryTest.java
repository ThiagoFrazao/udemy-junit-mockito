package br.udemy.mock.spring.repository;

import br.udemy.mock.spring.entidades.Pessoa;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    void testSalvarPessoa() {
        final String nome = RandomStringUtils.randomAlphabetic(10);
        final String cpf = RandomStringUtils.randomNumeric(11);
        final String email = RandomStringUtils.randomAlphabetic(10);
        Pessoa pessoa = new Pessoa(nome, cpf, email);
        final Pessoa saved = pessoaRepository.save(pessoa);
        Assertions.assertNotNull(saved);
        Assertions.assertEquals(nome, saved.getNome());
        Assertions.assertEquals(cpf, saved.getCpf());
        Assertions.assertEquals(email, saved.getEmail());
        Assertions.assertNotNull(saved.getId());
        Assertions.assertTrue(pessoaRepository.findById(saved.getId()).isPresent());
        Assertions.assertTrue(saved.getId() > 0);
    }



}
