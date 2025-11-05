package com.senai.projetosa.controllers;

import com.senai.projetosa.dtos.*;
import com.senai.projetosa.services.UsuarioService;
import com.senai.projetosa.sessao.ControleSessao;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UsuarioService service;

    public LoginController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String viewLogin(Model model){
        LoginDTO emailDTO = new LoginDTO();
        model.addAttribute("loginDto", emailDTO);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginDto") LoginDTO emailDTO, HttpServletRequest request){
        UsuarioSessaoDTO usuarioSessao = service.validarEmail(emailDTO);

        if (usuarioSessao.getId() != 0L){

            ControleSessao.registrar(request, usuarioSessao);
            // sucesso no login
            return "redirect:/home";
        }else{
            //--erro no login
            return "redirect:/login?erro";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        ControleSessao.encerrar(request);
        return "redirect:/login";
    }
}
