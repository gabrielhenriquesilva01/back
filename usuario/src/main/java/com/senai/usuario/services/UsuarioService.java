package com.senai.usuario.services;

import com.senai.usuario.dtos.RequisicaoDTO;
import com.senai.usuario.dtos.RespostaDTO;
import com.senai.usuario.dtos.UsuarioDTO;
import com.senai.usuario.model.UsuarioModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService {
    private List<UsuarioModel> listaUsuario = new ArrayList<>();
    private int ultimoID = 0;

    public RespostaDTO criar(RequisicaoDTO dados) {
        for (UsuarioModel u : listaUsuario){
            if (u.getLogin().equals(dados.getLogin())) {
                RespostaDTO resposta = new RespostaDTO();
                resposta.setMensagem("USUÁRIO JÁ EXISTE!");
                return resposta;
            }
        }
            ultimoID += 1;
            UsuarioModel usuarioModel = new UsuarioModel();
            usuarioModel.setId(ultimoID);
            usuarioModel.setLogin(dados.getLogin());
            usuarioModel.setNome(dados.getNome());
            usuarioModel.setSenha(dados.getSenha());
            listaUsuario.add(usuarioModel);

            RespostaDTO resposta = new RespostaDTO();
            resposta.setMensagem("sucesso");
            return resposta;
    }

        public RespostaDTO atualizar(int id, RequisicaoDTO dados){
            for (UsuarioModel u : listaUsuario){
                if (u.getId() == id){
                    u.setId(id);
                    u.setSenha(dados.getSenha());
                    u.setNome(dados.getNome());
                    u.setLogin(dados.getLogin());

                    if (u.getLogin().equals(dados.getLogin()));{
                        RespostaDTO resposta = new RespostaDTO();
                        resposta.setMensagem("sucesso");
                        return resposta;
                    }
                }
                RespostaDTO resposta = new RespostaDTO();
                resposta.setMensagem("ESTE LOGIN JÁ EXISTE!");
                return resposta;
            }
            RespostaDTO resposta = new RespostaDTO();
            resposta.setMensagem("USUÁRIO NÃO ENCONTRADO!");
            return resposta;
        }

        public List<UsuarioDTO> localizar(int id){
            for (UsuarioModel u : listaUsuario) {
                if (u.getId() == id){
                    UsuarioDTO usuarioDTO = new UsuarioDTO();
                    usuarioDTO.setId(u.getId());
                    usuarioDTO.setLogin(u.getLogin());
                    usuarioDTO.setNome(u.getNome());
                    usuarioDTO.setSenha(u.getSenha());
                    return List.of(usuarioDTO);
                }
            }
            RespostaDTO resposta = new RespostaDTO();
            resposta.setMensagem("USUÁRIO NÃO ENCONTRADO!");
            return (List<UsuarioDTO>) resposta;
        }

        public List<UsuarioDTO> localizarUsuarios() {
            List<UsuarioDTO> listaUsuarioDTO = new ArrayList<>();
            for (UsuarioModel u : listaUsuario) {
                UsuarioDTO usuarioDTO = new UsuarioDTO();
                usuarioDTO.setId(u.getId());
                usuarioDTO.setLogin(u.getLogin());
                usuarioDTO.setNome(u.getNome());
                usuarioDTO.setSenha(u.getSenha());
                listaUsuarioDTO.add(usuarioDTO);
            }
            return listaUsuarioDTO;
        }

        public RespostaDTO deleta(int id){
        for (UsuarioModel u : listaUsuario){
                if (u.getId() == id){
                    listaUsuario.remove(u);
                    RespostaDTO resposta = new RespostaDTO();
                    resposta.setMensagem("sucesso");
                    return resposta;
                }
            }
            RespostaDTO resposta = new RespostaDTO();
            resposta.setMensagem("USUÁRIO NÃO ENCONTRADO!");
            return resposta;
    }
}