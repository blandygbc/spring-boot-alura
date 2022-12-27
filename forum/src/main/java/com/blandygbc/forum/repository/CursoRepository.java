package com.blandygbc.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blandygbc.forum.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Curso findByNome(String nome);

}
