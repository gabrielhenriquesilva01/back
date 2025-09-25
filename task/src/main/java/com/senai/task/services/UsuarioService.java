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

    public RespostaDTO criarUsuario(UsuarioDTO dados){
        Optional<UsuarioModel> model = usuarioRepository.findByEmail(dados.getEmail());

        if (model.isPresent()) {
            RespostaDTO respostaDTO = new RespostaDTO();
            respostaDTO.setMensagem("Este usuário já existe!");
            return respostaDTO;
        }

        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setNome(dados.getNome());
        usuarioModel.setEmail(dados.getEmail());
        usuarioRepository.save(usuarioModel);
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setMensagem("sucesso");
        return respostaDTO;
    }

    public RespostaDTO atualizarUsuario(String email, UsuarioDTO dados){
        Optional<UsuarioModel> model = usuarioRepository.findByEmail(email);

        if (model.isPresent()) {
            Optional<UsuarioModel> emailExistente = usuarioRepository.findByEmail(dados.getEmail());
            if (emailExistente.isPresent() && !emailExistente.get().getEmail().equals(email)) {
                RespostaDTO respostaDTO = new RespostaDTO();
                respostaDTO.setMensagem("email");
                return respostaDTO;
            }
            UsuarioModel usuarioModel = model.get();
            usuarioModel.setNome(dados.getNome());
            usuarioModel.setEmail(dados.getEmail());
            usuarioRepository.save(usuarioModel);
            RespostaDTO respostaDTO = new RespostaDTO();
            respostaDTO.setMensagem("sucesso");
            return respostaDTO;
        }
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setMensagem("Usuário não encontrado!");
        return respostaDTO;
    }

    public RespostaDTO deletarUsuario(String email){
        Optional<UsuarioModel> model = usuarioRepository.findByEmail(email);

        if (model.isPresent()) {
            UsuarioModel usuarioModel = model.get();
            usuarioRepository.delete(usuarioModel);
            RespostaDTO respostaDTO = new RespostaDTO();
            respostaDTO.setMensagem("sucesso");
            return respostaDTO;
        }
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setMensagem("Usuário não existe!");
        return respostaDTO;
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