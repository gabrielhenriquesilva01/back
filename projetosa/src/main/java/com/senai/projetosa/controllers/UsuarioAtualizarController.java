package com.senai.projetosa.controllers;

import com.senai.projetosa.dtos.UsuarioDTO;
import com.senai.projetosa.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioAtualizarController {

    private final UsuarioService service;

    public UsuarioAtualizarController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/usuarioatualizar/{id}")
    public String viewAtualizar(@PathVariable Long id, Model model){
        UsuarioDTO usuarioDto = service.obterUsuario(id);
        model.addAttribute("usuarioDto", usuarioDto);
        return "usuarioatualizar";
    }
}
