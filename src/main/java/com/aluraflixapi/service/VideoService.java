package com.aluraflixapi.service;

import com.aluraflixapi.domain.Categoria;
import com.aluraflixapi.dto.video.DadosAtualizacaoVideo;
import com.aluraflixapi.dto.video.DadosDetalhamentoVideo;
import com.aluraflixapi.dto.video.DadosListagemVideo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aluraflixapi.domain.Video;
import com.aluraflixapi.dto.video.DadosCadastroVideo;
import com.aluraflixapi.repository.VideoRepository;

@Service
public class VideoService {

	@Autowired
	private VideoRepository repository;

	@Autowired
	private CategoriaService categoriaService;
	
	public DadosDetalhamentoVideo cadastrar(DadosCadastroVideo dados) {
		var categoria = categoriaService.selecionarCategoria(dados);
		var video = new Video(dados,categoria);
		repository.save(video);
		return new DadosDetalhamentoVideo(video);
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

	public ResponseEntity<DadosDetalhamentoVideo> pesquisarVideoPorNome(String nome) {

		var video = repository.findByTitulo(nome);

		if(video == null){
			throw new EntityNotFoundException();
		}

		return ResponseEntity.ok(new DadosDetalhamentoVideo(video));
	}
}
