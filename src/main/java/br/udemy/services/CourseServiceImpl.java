package br.udemy.services;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class CourseServiceImpl implements CourseService {

    private final HashMap<String, List<String>> cursosAluno;

    public CourseServiceImpl() {
        this.cursosAluno = new HashMap<>();
    }


    @Override
    public List<String> recuperarCursos(String nomeAluno) {
        List<String> retorno = cursosAluno.get(nomeAluno);
        if(retorno == null) {
            System.out.println("Aluno sem curso");
            retorno = new ArrayList<>();
            this.cursosAluno.put(nomeAluno, retorno);
        }
        return retorno;
    }

}
