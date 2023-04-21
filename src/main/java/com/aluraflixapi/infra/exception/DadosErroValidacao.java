package com.aluraflixapi.infra.exception;

import org.springframework.validation.FieldError;

public record DadosErroValidacao(String campo, String mensagem) {

    public DadosErroValidacao(FieldError err){
        this(err.getField(), err.getDefaultMessage());
    }

}
