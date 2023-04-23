package com.aluraflixapi.service;

import com.aluraflixapi.domain.Categoria;
import com.aluraflixapi.dto.categoria.DadosAtualizacaoCategoria;
import com.aluraflixapi.dto.categoria.DadosCadastroCategoria;
import com.aluraflixapi.dto.categoria.DadosDetalhamentoCategoria;
import com.aluraflixapi.dto.categoria.DadosListagemCategoria;
import com.aluraflixapi.dto.video.DadosAtualizacaoVideo;
import com.aluraflixapi.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public DadosDetalhamentoCategoria cadastrar(DadosCadastroCategoria dados){
        var categoria = new Categoria(dados);
        repository.save(categoria);

        return new DadosDetalhamentoCategoria(categoria);
    }

    public ResponseEntity<Page<DadosListagemCategoria>> listar(Pageable paginacao){

        var listaCategorias = repository.findAll(paginacao).map(DadosListagemCategoria::new);

        return ResponseEntity.ok(listaCategorias);
    }

    public ResponseEntity<DadosDetalhamentoCategoria> detalhar(Long id){
        var categoria = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCategoria(categoria));
    }

    public ResponseEntity excluir(Long id){
        var categoria = repository.findById(id);

        if(categoria != null){
            repository.delete(categoria.get());
        }else{
            throw new EntityNotFoundException();
        }

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<DadosDetalhamentoCategoria> atualizar(DadosAtualizacaoCategoria dados) throws Exception {
        var categoria = repository.getReferenceById(dados.id());
        categoria.atualizarInformacoes(dados);
        repository.save(categoria);

        return ResponseEntity.ok(new DadosDetalhamentoCategoria(categoria));

    }

}
