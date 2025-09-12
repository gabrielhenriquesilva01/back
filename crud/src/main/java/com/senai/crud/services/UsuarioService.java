package com.senai.crud.services;

import com.senai.crud.dtos.RequisicaoDto;
import com.senai.crud.dtos.RespostaDto;
import com.senai.crud.dtos.UsuarioDto;
import com.senai.crud.models.UsuarioModel;
import com.senai.crud.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private List<UsuarioModel> listaUsuario = new ArrayList<UsuarioModel>();

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    //--adicionar usuário
    public RespostaDto cadastrar(RequisicaoDto usuarioDto){

        //--Cria objeto usuarioModel
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setNome(usuarioDto.getNome());
        usuarioModel.setLogin(usuarioDto.getLogin());
        usuarioModel.setSenha(usuarioDto.getSenha());

        //--Adicionar no Banco
        repository.save(usuarioModel);

        //--Retornar resposta positiva!
        RespostaDto resposta = new  RespostaDto();
        resposta.setMensagem("sucesso");
        return resposta ;
    }

    //--excluir usuário
    public RespostaDto excluir(Long id){
        Optional<UsuarioModel> model = repository.findById(id);

        if (model.isPresent()){
            repository.delete(model.get());
            RespostaDto resposta = new  RespostaDto();
            resposta.setMensagem("sucesso");
            return resposta;
        }

        RespostaDto resposta = new  RespostaDto();
        resposta.setMensagem("Não foi possível remover o usuário id = " + id);
        return resposta ;
    }

    //--atualizar usuário
    public RespostaDto atualizar(Long id, RequisicaoDto usuarioDto){

        //--Localizando o usuário para atualizar
        Optional<UsuarioModel> model = repository.findById(id);

        if ( model.isPresent()){
            UsuarioModel usuario = model.get();
            usuario.setNome(usuarioDto.getNome());
            usuario.setLogin(usuarioDto.getLogin());
            usuario.setSenha(usuarioDto.getSenha());
            repository.save(usuario);

            RespostaDto resposta = new  RespostaDto();
            resposta.setMensagem("sucesso");
            return resposta ;
        }

        RespostaDto resposta = new  RespostaDto();
        resposta.setMensagem("Não foi possível atualizar o usuário id = " + id);
        return resposta ;
    }

    //--obter um unico usuário
    public UsuarioDto obterUsuario(Long id){
        UsuarioDto usuarioDto = new UsuarioDto();

        Optional<UsuarioModel> model = repository.findById(id);

        if (model.isPresent()){
            usuarioDto.setId(model.get().getId());
            usuarioDto.setNome(model.get().getNome());
            usuarioDto.setSenha(model.get().getSenha());
            usuarioDto.setLogin(model.get().getLogin());
            return usuarioDto;
        }
        return usuarioDto;
    }

    //--obter todos os usuários
    public List<UsuarioDto> obterUsuarios(){

        //--Criar a lista de usuarioDTO
        List<UsuarioDto> listaUsuarioDto = new ArrayList<>();

        List<UsuarioModel> listaModel = repository.findAll();
        //--percorrer a lista de usuarioModel
        for (UsuarioModel usuario : listaModel){
            //--Criar um objeto usuarioDTO novo
            UsuarioDto usuarioDto = new UsuarioDto();
            //--Converter os dados do usuarioModel para usuarioDto
            usuarioDto.setId(usuario.getId());
            usuarioDto.setNome(usuario.getNome());
            usuarioDto.setLogin(usuario.getLogin());
            usuarioDto.setSenha(usuario.getSenha());
            //--adicionar o usuarioDTO na lista de usuarioDTO
            listaUsuarioDto.add(usuarioDto);
        }
        //--retornar a lista de usuarioDTO
        return listaUsuarioDto;
    }
}