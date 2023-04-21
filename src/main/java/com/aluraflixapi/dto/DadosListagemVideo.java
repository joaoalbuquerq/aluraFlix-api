package com.aluraflixapi.dto;

import com.aluraflixapi.domain.Video;

public record DadosListagemVideo(
        String titulo,
        String descricao,
        String url) {

    public DadosListagemVideo(Video video){
       this(video.getTitulo(), video.getDescricao(), video.getUrl());
    }

}
