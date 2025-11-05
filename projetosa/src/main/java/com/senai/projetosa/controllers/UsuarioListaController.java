package com.senai.projetosa.controllers;

import com.senai.projetosa.dtos.UsuarioDTO;
import com.senai.projetosa.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UsuarioListaController {

private final UsuarioService service;

    public UsuarioListaController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/usuariolista")
    public String viewUsuarioLista(Model model){
        List<UsuarioDTO> listaDto = service.obterUsuarios();
        model.addAttribute("listaDto", listaDto);
        return "usuariolista";
    }
}
