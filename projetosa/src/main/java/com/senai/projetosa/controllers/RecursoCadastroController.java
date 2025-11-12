package com.senai.projetosa.controllers;

import com.senai.projetosa.dtos.RequisicaoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecursoCadastroController {

    @GetMapping("/recursocadastro")
    public String viewCadastro(Model model){
        model.addAttribute("recursoDto", new RequisicaoDTO());
        return "recursocadastro";
    }
}
