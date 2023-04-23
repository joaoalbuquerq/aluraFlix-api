package com.aluraflixapi.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoVideo(
        @NotNull
        Long id,
        String titulo,
        String descricao,
        String url
) {

}
