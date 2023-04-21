package com.aluraflixapi.controller;

import com.aluraflixapi.dto.DadosListagemVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aluraflixapi.dto.DadosCadastroVideo;
import com.aluraflixapi.service.VideoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/videos")
public class VideoController {
	
	@Autowired
	private VideoService service;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroVideo dadosRequisicao, UriComponentsBuilder uri) {
		return service.cadastrar(dadosRequisicao, uri);
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemVideo>> listar(@PageableDefault(size = 20, sort = {"titulo"}) Pageable paginacao){
		return service.listar(paginacao);
	}

}
