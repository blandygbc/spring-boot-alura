package com.blandygbc.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blandygbc.forum.dto.TopicoDto;
import com.blandygbc.forum.model.Curso;
import com.blandygbc.forum.model.Topico;

@RestController
public class TopicosController {

    @GetMapping("/topicos")
    public List<TopicoDto> listaDeTopicos() {
        Topico topico = new Topico("Dúvida", "Dúvida com Spring", new Curso("Spring", "Programação"));
        return TopicoDto.converter(Arrays.asList(topico, topico, topico));
    }
}
