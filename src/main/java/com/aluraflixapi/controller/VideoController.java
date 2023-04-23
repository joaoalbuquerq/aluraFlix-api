package com.aluraflixapi.controller;

import com.aluraflixapi.dto.video.DadosAtualizacaoVideo;
import com.aluraflixapi.dto.video.DadosListagemVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aluraflixapi.dto.video.DadosCadastroVideo;
import com.aluraflixapi.service.VideoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/videos")
public class VideoController {
	
	@Autowired
	private VideoService service;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroVideo dadosRequisicao) {
		var dto = service.cadastrar(dadosRequisicao);
		return ResponseEntity.ok(dto);

	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemVideo>> listar(@PageableDefault(size = 20, sort = {"titulo"}) Pageable paginacao){
		return service.listar(paginacao);
	}

	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id){
		return service.detalhar(id);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id){
		return service.deletar(id);
	}

	@PutMapping()
	@Transactional
	public ResponseEntity atualizar(@RequestBody DadosAtualizacaoVideo dadosRequisicao){
		return service.atualizar(dadosRequisicao);
	}

}
