package com.aluraflixapi.dto.categoria;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCategoria(
        @NotBlank
        String titulo,
        @NotBlank
        String cor
) {
}
