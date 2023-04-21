package com.aluraflixapi.service;

import com.aluraflixapi.dto.DadosAtualizacaoVideo;
import com.aluraflixapi.dto.DadosDetalhamentoVideo;
import com.aluraflixapi.dto.DadosListagemVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public ResponseEntity<Page<DadosListagemVideo>> listar(Pageable paginacao){
		var listaVideos = repository.findAll(paginacao).map(DadosListagemVideo::new);
		return ResponseEntity.ok(listaVideos);
	}

	public ResponseEntity detalhar(Long id){
		var video = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoVideo(video));
	}

	public ResponseEntity deletar(Long id){
		var video = repository.getReferenceById(id);
		repository.delete(video);

		return ResponseEntity.noContent().build();
	}

	public ResponseEntity atualizar(DadosAtualizacaoVideo dados){
		var video = repository.getReferenceById(dados.id());
		video.atualizarDados(dados);
		return ResponseEntity.ok(new DadosDetalhamentoVideo(video));
	}

}
