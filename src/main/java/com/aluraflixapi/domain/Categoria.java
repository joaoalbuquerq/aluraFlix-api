package com.aluraflixapi.domain;

import com.aluraflixapi.dto.categoria.DadosAtualizacaoCategoria;
import com.aluraflixapi.dto.categoria.DadosCadastroCategoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String cor;

    public Categoria(DadosCadastroCategoria dados){
        this.titulo = dados.titulo();
        this.cor = dados.cor();
    }

    public void atualizarInformacoes(DadosAtualizacaoCategoria dados){
        if(dados.titulo() != null && !dados.titulo().equals("")){
            this.titulo = dados.titulo();
        }
        if(dados.cor() != null && !dados.cor().equals("")){
            this.cor = dados.cor();
        }
    }

}
