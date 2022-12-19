package com.blandygbc.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blandygbc.forum.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    List<Topico> findByCursoNome(String nomeCurso);
}
