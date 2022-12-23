package com.blandygbc.forum.dto;

import javax.validation.constraints.NotBlank;

import com.blandygbc.forum.model.Topico;

public class AtualizacaoTopicoForm {

    @NotBlank
    private String titulo;
    @NotBlank
    private String mensagem;

    public AtualizacaoTopicoForm(@NotBlank String titulo, @NotBlank String mensagem) {
        this.titulo = titulo;
        this.mensagem = mensagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Topico atualizar(Topico topico) {
        topico.setTitulo(titulo);
        topico.setMensagem(mensagem);
        return topico;
    }

}
