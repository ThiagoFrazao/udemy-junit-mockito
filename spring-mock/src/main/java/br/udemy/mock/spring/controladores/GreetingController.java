package br.udemy.mock.spring.controladores;

import br.udemy.mock.spring.modelos.Greeting;
import br.udemy.mock.spring.servicos.GreetingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private final GreetingService service;

    public GreetingController(GreetingService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Greeting> getGreeting(@RequestParam(required = false) String conteudo) {
        try {
            final Greeting retorno;
            if(StringUtils.isEmpty(conteudo)) {
                retorno = this.service.gerarGreetingAleatorio();
            } else {
                retorno = this.service.gerarGreetingContent(StringUtils.trimToEmpty(conteudo));
            }
            return ResponseEntity.ok(retorno);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Greeting(-1L,
                    "Falha %s ao gerar greeting: %s".formatted(e.getClass().getSimpleName(), e.getMessage())));
        }

    }


}
