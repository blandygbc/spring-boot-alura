package com.blandygbc.forum.dto;

import com.blandygbc.forum.model.Curso;
import com.blandygbc.forum.model.Topico;
import com.blandygbc.forum.repository.CursoRepository;

import javax.validation.constraints.NotBlank;

public class TopicoForm {

    @NotBlank
    private String titulo;
    @NotBlank
    private String mensagem;
    private Long cursoId;

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

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public Topico converter(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findById(this.cursoId).orElse(new Curso());

        return new Topico(this.titulo, this.mensagem, curso);
    }

}
