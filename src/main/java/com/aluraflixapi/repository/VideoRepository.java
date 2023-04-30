package com.aluraflixapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aluraflixapi.domain.Video;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>{

    @Query("""
            select v 
            from Video v
            where v.categoria.id = :id
            """)
    List<Video> pesquisarVideosPorCategoria(Long id);

    Video findByTitulo(String nome);
}
