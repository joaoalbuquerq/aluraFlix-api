package com.aluraflixapi.domain;

import com.aluraflixapi.dto.DadosCadastroVideo;

import jakarta.persistence.*;

@Entity
@Table(name = "videos")
public class Video {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	private String descricao;
	
	private String url;
	
	public Video(DadosCadastroVideo dados) {
		this.titulo = dados.titulo();
		this.descricao = dados.descricao();
		this.url = dados.url();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
