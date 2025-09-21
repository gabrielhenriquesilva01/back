package com.senai.task.services;

import com.senai.task.Models.*;
import com.senai.task.dtos.*;
import com.senai.task.repositorys.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TarefaRepository tarefaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, TarefaRepository tarefaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.tarefaRepository = tarefaRepository;
    }

    public boolean criarUsuario(UsuarioDTO dados){
        Optional<UsuarioModel> model = usuarioRepository.findByEmail(dados.getEmail());

        if (model.isPresent()) {
            return false;
        }

        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setNome(dados.getNome());
        usuarioModel.setEmail(dados.getEmail());
        usuarioRepository.save(usuarioModel);
        return true;
    }

    public boolean atualizarUsuario(String email, UsuarioDTO dados){
        Optional<UsuarioModel> model = usuarioRepository.findByEmail(email);

        if (model.isPresent()) {
            Optional<UsuarioModel> emailExistente = usuarioRepository.findByEmail(dados.getEmail());
            if (emailExistente.isPresent() && !emailExistente.get().getEmail().equals(email)) {
                return false;
            }
            UsuarioModel usuarioModel = model.get();
            usuarioModel.setNome(dados.getNome());
            usuarioModel.setEmail(dados.getEmail());
            usuarioRepository.save(usuarioModel);
            return true;
        }
        return false;
    }

    public boolean deletarUsuario(String email){
        Optional<UsuarioModel> model = usuarioRepository.findByEmail(email);

        if (model.isPresent()) {
            UsuarioModel usuarioModel = model.get();
            usuarioRepository.delete(usuarioModel);
            return true;
        }
        return false;
    }

    public List<UsuarioDTO> obterUsuarios(){
        List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
        List<UsuarioModel> usuarioModelList = usuarioRepository.findAll();

        for (UsuarioModel usuarioModel : usuarioModelList){
            UsuarioDTO dto = new UsuarioDTO();
            dto.setNome(usuarioModel.getNome());
            dto.setEmail(usuarioModel.getEmail());
            usuarioDTOList.add(dto);
        }
        return usuarioDTOList;
    }
}