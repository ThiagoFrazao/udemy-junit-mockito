package br.udemy.mock.spring.error;

import br.udemy.mock.spring.error.exceptions.FalhaBancoDados;
import br.udemy.mock.spring.error.model.ErrorResponse;
import br.udemy.mock.spring.error.model.ErrorType;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@NoArgsConstructor
public class ErrorControllerAdvice {


    @ExceptionHandler({
            Exception.class
    })
    public ResponseEntity<ErrorResponse> errorGenerico(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(exception.getMessage(), ErrorType.NAO_RECUPERAVEL));
    }

    @ExceptionHandler({
            FalhaBancoDados.class
    })
    public ResponseEntity<ErrorResponse> errorGenerico(FalhaBancoDados exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(exception.getMessage(), ErrorType.RECUPERAVEL));
    }


}
