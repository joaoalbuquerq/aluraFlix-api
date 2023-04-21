package com.aluraflixapi.service;

import com.aluraflixapi.dto.DadosDetalhamentoVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aluraflixapi.domain.Video;
import com.aluraflixapi.dto.DadosCadastroVideo;
import com.aluraflixapi.repository.VideoRepository;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class VideoService {

	@Autowired
	private VideoRepository repository;
	
	public ResponseEntity cadastrar(DadosCadastroVideo dados, UriComponentsBuilder uriBuilder) {
		
		var video = new Video(dados);
		repository.save(video);

		var uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId());

		return ResponseEntity.created(uri.toUri()).body(new DadosDetalhamentoVideo(video));
	}

}
