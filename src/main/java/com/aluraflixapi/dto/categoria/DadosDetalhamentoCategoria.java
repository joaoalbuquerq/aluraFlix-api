package com.aluraflixapi.dto.categoria;

import com.aluraflixapi.domain.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosDetalhamentoCategoria(
        @NotNull
        Long id,

        @NotBlank
        String titulo,

        @NotBlank
        String cor
) {

    public DadosDetalhamentoCategoria(Categoria categ){
        this(categ.getId(), categ.getTitulo(), categ.getCor());
    }

}
