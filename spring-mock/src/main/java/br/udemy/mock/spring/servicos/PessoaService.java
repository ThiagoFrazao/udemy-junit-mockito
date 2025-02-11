package br.udemy.mock.spring.servicos;

import br.udemy.mock.spring.entidades.Pessoa;
import br.udemy.mock.spring.error.exceptions.FalhaBancoDados;
import br.udemy.mock.spring.error.exceptions.FalhaDadosInvalidosBancoException;
import br.udemy.mock.spring.repository.PessoaRepository;
import org.apache.commons.lang3.StringUtils;
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
            final Pessoa pessoaCpf = this.pessoaRepository.findPessoaByCpf(pessoa.getCpf());
            if (pessoaCpf == null) {
                return this.pessoaRepository.save(pessoa);
            } else {
                throw new FalhaDadosInvalidosBancoException("ja existe alguem cadastrado com esse CPF", "Pessoa");
            }
        } catch (FalhaBancoDados e) {
            throw e;
        } catch (Exception e) {
            throw new FalhaBancoDados(e);
        }
    }

    public List<Pessoa> recuperarPessoaPorEmail(String email) {
        try {
            if(StringUtils.isBlank(email)) {
                throw new FalhaDadosInvalidosBancoException("email nao informado",
                        this.pessoaRepository.getClass().getSimpleName());
            }
            return this.pessoaRepository.findPessoasByEmail(StringUtils.upperCase(email));
        } catch (FalhaBancoDados e) {
            throw e;
        } catch (Exception e) {
            throw new FalhaBancoDados(e);
        }
    }

}
