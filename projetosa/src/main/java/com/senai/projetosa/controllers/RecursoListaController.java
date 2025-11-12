package com.senai.projetosa.controllers;

import com.senai.projetosa.dtos.RecursoDTO;
import com.senai.projetosa.services.RecursoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RecursoListaController {

    private final RecursoService recursoService;

    public RecursoListaController(RecursoService recursoService) {
        this.recursoService = recursoService;
    }

    @GetMapping("/recursolista")
    public String viewUsuarioLista(Model model){
        List<RecursoDTO> listaDto = recursoService.obterRecursos();
        model.addAttribute("listaDto", listaDto);
        return "recursolista";
    }
}