package br.udemy.mock.spring.servicos;

import br.udemy.mock.spring.modelos.Greeting;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GreetingService {

    private final Random random;

    public GreetingService() {
        this.random = new Random();
    }

    public Greeting gerarGreetingContent(String content) {
        try {
            return new Greeting(this.random.nextLong(), content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Greeting gerarGreetingAleatorio() {
        try {
            byte[] randomBytes = new byte[this.random.nextInt(100)];
            this.random.nextBytes(randomBytes);
            return new Greeting(this.random.nextLong(), new String(randomBytes));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
