package br.udemy.mock.spring.servicos;

import br.udemy.mock.spring.entidades.Pessoa;
import br.udemy.mock.spring.error.exceptions.FalhaBancoDados;
import br.udemy.mock.spring.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> recuperarTodos() {
        return this.pessoaRepository.findAll();
    }

    public Pessoa cadastrar(Pessoa pessoa) {
        try {
          return this.pessoaRepository.save(pessoa);
        } catch (Exception e) {
            throw new FalhaBancoDados(e);
        }
    }
}
