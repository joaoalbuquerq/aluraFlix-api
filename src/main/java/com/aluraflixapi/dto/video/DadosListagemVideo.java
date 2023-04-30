package com.aluraflixapi.dto.video;

import com.aluraflixapi.domain.Video;

public record DadosListagemVideo(
        String titulo,
        String descricao,
        String url,
        String categoria
) {

    public DadosListagemVideo(Video video){
       this(video.getTitulo(), video.getDescricao(), video.getUrl(),video.getCategoria().getTitulo());
    }

}
