package paulodev.investmentsaggregator.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
    As funções aqui estao marcadas como nunca usadas,
    O Spring usa uma técnica chamada Reflection.
    Quando acontece um erro na aplicação, o Spring "varre" as classes anotadas com @RestControllerAdvice
    e procura métodos anotados com @ExceptionHandler.
    Quando ele encontra, ele invoca o métod0 magicamente por "debaixo dos panos".
*/

@RestControllerAdvice
public class InvestmentsAggregatorExceptionsHandler {

    // Tratamento para qualquer entity "Not Found"
    @ExceptionHandler(InvestmentsAggregatorExceptions.EntityNotFoundException.class)
    public ResponseEntity<Void> handleNotFound(InvestmentsAggregatorExceptions.EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    // Tratamento para Ação Duplicada
    @ExceptionHandler(InvestmentsAggregatorExceptions.StockAlreadyExistsException.class)
    public ResponseEntity<String> handleConflict(InvestmentsAggregatorExceptions.StockAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
