package com.aluraflixapi.controller;

import com.aluraflixapi.dto.video.DadosAtualizacaoVideo;
import com.aluraflixapi.dto.video.DadosCadastroVideo;
import com.aluraflixapi.dto.video.DadosDetalhamentoVideo;
import com.aluraflixapi.service.VideoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class VideoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VideoService service;

    @Autowired
    private JacksonTester<DadosCadastroVideo> dadosCadastroVideoJson;

    @Autowired
    private JacksonTester<DadosAtualizacaoVideo> dadosAtualizacaoVideo;

    @Autowired
    private JacksonTester<DadosDetalhamentoVideo> dadosDetalhamentoVideoJson;

    @Test
    @DisplayName("DEVERIA RETORNAR 400 QUANDO FOR PASSADO UM JSON FALTANDO INFORMAÇÕES PARA O CADASTRO")
    void cadastrarVideoCenario1() throws Exception{
        var response = mvc.perform(MockMvcRequestBuilders.post("/videos")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("DEVE RETORNAR CÓDIGO 200 QUANDO UM VIDEO FOR CRIADO COM SUCESSO")
    void cadastrarVideoCenario3() throws Exception{
        var titulo = "Demon Slayer";
        var descricao = "O melhor anime do mundo";
        var url = "http://demonslayer.com.br";

        var dados = new DadosDetalhamentoVideo(null,titulo, descricao, url);
        when(service.cadastrar(any())).thenReturn(dados);

        var response = mvc.perform(
                MockMvcRequestBuilders.post("/videos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroVideoJson.write(
                                new DadosCadastroVideo(titulo,descricao,url)
                        ).getJson())
        ).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("DEVE RETORNAR UM JSON VÁLIDO QUANDO UM VIDEO FOR CRIADO COM SUCESSO")
    void cadastrarVideoCenario2() throws Exception{
        var titulo = "Demon Slayer";
        var descricao = "O melhor anime do mundo";
        var url = "http://demonslayer.com.br";

        var dados = new DadosDetalhamentoVideo(null,titulo, descricao, url);
        when(service.cadastrar(any())).thenReturn(dados);

        var response = mvc.perform(
                MockMvcRequestBuilders.post("/videos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroVideoJson.write(
                                new DadosCadastroVideo(titulo,descricao,url)
                        ).getJson())
        ).andReturn().getResponse();

        var jsonEsperado = dadosDetalhamentoVideoJson.write(dados).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("DEVE RETORNAR CÓDIGO 200 QUANDO UMA LISTAGEM FOR SOLICITADA MESMO VAZIA")
    void listarVideos() throws Exception{
        var response = mvc.perform(MockMvcRequestBuilders.get("/videos")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("DEVE RETORNAR 200 QUANDO UM VIDEO FOR ATUALIZADO COM SUCESSO")
    void atualizarVideoCenario1() throws Exception{
        var titulo = "Demon Slayer 3 TEMPORADA";
        var descricao = "O melhor anime do mundo SIM";
        var url = "http://demonslayer.com.br";

        var dados = new DadosDetalhamentoVideo(1l,titulo, descricao, url);

        var response = mvc.perform(
                MockMvcRequestBuilders.put("/videos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAtualizacaoVideo.write(
                                new DadosAtualizacaoVideo(1l,titulo,descricao,url)
                        ).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("DEVE RETORNAR 400 QUANDO NÃO FOREM PASSADOS OS DADOS NECESSÁRIOS PARA ATUALIZAÇÃO")
    void atualizarVideoCenario2() throws Exception{
        var response = mvc.perform(MockMvcRequestBuilders.put("/videos")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("DEVE RETORNAR 405 QUANDO NAO FOR INFORMADO UM ID PARA EXCLUSAO DE VIDEO")
    void deletarVideoCenario1() throws Exception{
        var response = mvc.perform(MockMvcRequestBuilders.delete("/videos")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED.value());
    }

    @Test
    @DisplayName("DEVE RETORNAR 200 QUANDO UM VIDEO FOR EXCLUIDO COM SUCESSO")
    void deletarVideoCenario2() throws Exception{

        var response = mvc.perform(MockMvcRequestBuilders.delete("/videos/{id}", "1")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}
