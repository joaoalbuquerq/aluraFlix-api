package com.aluraflixapi.dto.categoria;

import com.aluraflixapi.domain.Categoria;

public record DadosListagemCategoria(
        Long id,
        String titulo,
        String cor
) {

    public DadosListagemCategoria(Categoria categoria){
        this(categoria.getId(), categoria.getTitulo(), categoria.getCor());
    }

}
