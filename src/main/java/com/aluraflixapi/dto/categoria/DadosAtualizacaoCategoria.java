package com.aluraflixapi.dto.categoria;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCategoria(
        @NotNull
        Long id,

        String titulo,
        String cor
) {
}
