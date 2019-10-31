package br.com.facef.trabalhoapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestException extends Throwable {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response exception(Exception ex) {
        return new Response(HttpStatus.INTERNAL_SERVER_ERROR,ex.getLocalizedMessage(), "Erro interno do Servidor!");
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response exception (NoSuchElementException ex) {
        return new Response(HttpStatus.BAD_REQUEST,ex.getLocalizedMessage(), "Problemas com a Requisição!");

    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response exception (RuntimeException ex) {
        return new Response(HttpStatus.NOT_FOUND,ex.getLocalizedMessage(), "Registro não encontrado!");

    }
}
