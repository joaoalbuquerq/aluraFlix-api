package com.aluraflixapi.controller;

import com.aluraflixapi.dto.categoria.DadosAtualizacaoCategoria;
import com.aluraflixapi.dto.categoria.DadosCadastroCategoria;
import com.aluraflixapi.dto.categoria.DadosDetalhamentoCategoria;
import com.aluraflixapi.dto.categoria.DadosListagemCategoria;
import com.aluraflixapi.dto.video.DadosListagemVideo;
import com.aluraflixapi.service.CategoriaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCategoria> cadastrar(@RequestBody @Valid DadosCadastroCategoria dadosRequisicao){
        var categoria = service.cadastrar(dadosRequisicao);
        return ResponseEntity.ok(categoria);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCategoria>> listar(@PageableDefault(size = 20, sort = {"titulo"}) Pageable paginacao){
        return service.listar(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCategoria> detalhar(@PathVariable Long id){
        return service.detalhar(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable Long id){
        return service.excluir(id);
    }

    @PutMapping
    public ResponseEntity<DadosDetalhamentoCategoria> atualizar(@RequestBody @Valid DadosAtualizacaoCategoria dadosRequisicao) throws Exception {
        return service.atualizar(dadosRequisicao);
    }

    @GetMapping("{id}/videos")
    public ResponseEntity<List<DadosListagemVideo>> listarPorCategoria(@PathVariable Long id){
        return service.listarPorCategoria(id);
    }

}
