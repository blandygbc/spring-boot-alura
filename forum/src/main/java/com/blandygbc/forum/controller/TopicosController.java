package com.blandygbc.forum.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.blandygbc.forum.dto.AtualizacaoTopicoForm;
import com.blandygbc.forum.dto.TopicoDetalhadoDto;
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
    public Page<TopicoDto> listarTodos(@RequestParam(required = false) String nomeCurso,
            @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 3) Pageable paginacao) {

        if (nomeCurso == null) {
            Page<Topico> topicos = topicoRepository.findAll(paginacao);
            return TopicoDto.converter(topicos);
        } else {
            Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
            return TopicoDto.converter(topicos);
        }

    }

    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm topicoForm,
            UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = topicoForm.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{idTopico}")
    public ResponseEntity<TopicoDetalhadoDto> detalharTopico(@PathVariable Long idTopico) {
        Optional<Topico> topico = topicoRepository.findById(idTopico);
        if (topico.isPresent())
            return ResponseEntity.ok(new TopicoDetalhadoDto(topico.get()));
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{idTopico}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@RequestBody @Valid AtualizacaoTopicoForm atualizacaoTopicoForm,
            UriComponentsBuilder uriComponentsBuilder, @PathVariable Long idTopico) {
        Optional<Topico> topicoOptional = topicoRepository.findById(idTopico);
        if (topicoOptional.isPresent()) {
            Topico topico = atualizacaoTopicoForm.atualizar(topicoOptional.get());
            return ResponseEntity.ok(new TopicoDto(topico));
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{idTopico}")
    @Transactional
    public ResponseEntity<String> remover(@PathVariable Long idTopico) {
        Optional<Topico> topicoOptional = topicoRepository.findById(idTopico);
        if (topicoOptional.isPresent()) {
            topicoRepository.delete(topicoOptional.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
