package com.senai.usuario.services;

import com.senai.usuario.dtos.CategoriaDTO;
import com.senai.usuario.dtos.CategoriaRequestDTO;
import com.senai.usuario.dtos.CategoriaResponseDTO;
import com.senai.usuario.dtos.ProdutoRequestDTO;
import com.senai.usuario.model.CategoriaModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {
    List<CategoriaModel> categoriaModelList = new ArrayList<>();
    private int cont = 0;

    public CategoriaResponseDTO criarCategoria(CategoriaRequestDTO dados){
        for (CategoriaModel u : categoriaModelList){
            if (u.getNome().equals(dados.getNome())){
                CategoriaResponseDTO resposta = new CategoriaResponseDTO();
                resposta.setMensagem("CATEGORIA JÁ EXISTE!");
                return resposta;
            }
        }
        cont += 1;
        CategoriaModel categoriaModel = new CategoriaModel();
        categoriaModel.setId((long) cont);
        categoriaModel.setNome(dados.getNome());
        categoriaModelList.add(categoriaModel);
        CategoriaResponseDTO resposta = new CategoriaResponseDTO();
        resposta.setMensagem("sucesso");
        return resposta;
    }

    public List<CategoriaDTO> listarCategorias(){
    List<CategoriaDTO> list = new ArrayList<>();
    for (CategoriaModel c : categoriaModelList){
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(c.getId());
        categoriaDTO.setNome(c.getNome());
        list.add(categoriaDTO);
    }
    return list;
    }
}