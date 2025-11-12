package com.senai.projetosa.controllers;

import com.senai.projetosa.dtos.RecursoDTO;
import com.senai.projetosa.services.RecursoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecursoAtualizarController {

    private final RecursoService recursoService;

    public RecursoAtualizarController(RecursoService recursoService) {
        this.recursoService = recursoService;
    }

    @GetMapping("/recursoatualizar/{id}")
    public String viewAtualizar(@PathVariable Long id, Model model){
        RecursoDTO recursoDto = recursoService.obterRecurso(id);
        model.addAttribute("recursoDto", recursoDto);
        return "recursoatualizar";
    }
}