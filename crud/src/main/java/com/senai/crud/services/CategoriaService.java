package com.senai.crud.services;

import com.senai.crud.dtos.*;
import com.senai.crud.models.CategoriaModel;
import com.senai.crud.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public RespostaDto criarCategoria(CategoriaRequisiçãoDTO dados){
        CategoriaModel model = new CategoriaModel();
        model.setNome(dados.getNome());
        repository.save(model);

        RespostaDto resposta = new  RespostaDto();
        resposta.setMensagem("sucesso");
        return resposta ;
    }

    public RespostaDto excluirCategoria(Long id){
        Optional<CategoriaModel> model = repository.findById(id);

        if (model.isPresent()){
            repository.delete(model.get());
            RespostaDto resposta = new  RespostaDto();
            resposta.setMensagem("sucesso");
            return resposta ;
        }
        RespostaDto resposta = new  RespostaDto();
        resposta.setMensagem("Não foi possível remover a categoria id = " + id);
        return resposta ;
    }

    public RespostaDto atualizarCategoria(Long id, CategoriaRequisiçãoDTO dados){
        Optional<CategoriaModel> model = repository.findById(id);

        if (model.isPresent()){
            CategoriaModel categoria = model.get();
            categoria.setNome(dados.getNome());
            repository.save(categoria);
            RespostaDto resposta = new  RespostaDto();
            resposta.setMensagem("sucesso");
            return resposta ;
        }
        RespostaDto resposta = new  RespostaDto();
        resposta.setMensagem("Não foi possível atualizar a categoria id = " + id);
        return resposta ;
    }

    public CategoriaDTO obterCategoria(Long id){
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        Optional<CategoriaModel> model = repository.findById(id);

        if (model.isPresent()){
            categoriaDTO.setId(model.get().getId());
            categoriaDTO.setNome(model.get().getNome());
            return categoriaDTO;
        }
        return categoriaDTO;
    }

    public List<CategoriaDTO> listarCategorias(){
        List<CategoriaDTO> list = new ArrayList<>();
        List<CategoriaModel> listaModel = repository.findAll();
        for (CategoriaModel c : listaModel){
            CategoriaDTO categoriaDTO = new CategoriaDTO();
            categoriaDTO.setId(c.getId());
            categoriaDTO.setNome(c.getNome());
            list.add(categoriaDTO);
        }
        return list;
    }
}
