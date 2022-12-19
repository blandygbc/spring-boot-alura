package com.blandygbc.forum.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blandygbc.forum.dto.TopicoDto;
import com.blandygbc.forum.dto.TopicoForm;
import com.blandygbc.forum.model.Topico;
import com.blandygbc.forum.repository.CursoRepository;
import com.blandygbc.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> listaDeTopicos() {
        List<Topico> topicos = topicoRepository.findAll();
        return TopicoDto.converter(topicos);
    }

    @GetMapping("/{idTopico}")
    public TopicoDto getTopico(@PathVariable Long idTopico) {
        Optional<Topico> topico = topicoRepository.findById(idTopico);
        return new TopicoDto(topico.orElseThrow());
    }

    @GetMapping("/curso")
    public List<TopicoDto> fetchTopicosByCursoName(@Param("nomeCurso") String nomeCurso) {
        List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
        return TopicoDto.converter(topicos);

    }

    @PostMapping
    public void cadastrar(@RequestBody TopicoForm topicoForm) {
        Topico topico = topicoForm.converter(cursoRepository);
        topicoRepository.save(topico);
    }
}
