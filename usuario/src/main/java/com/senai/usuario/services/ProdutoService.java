package com.senai.usuario.services;

import com.senai.usuario.dtos.ProdutoDTO;
import com.senai.usuario.dtos.ProdutoRequestDTO;
import com.senai.usuario.dtos.ProdutoResponseDTO;
import com.senai.usuario.model.ProdutoModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    List<ProdutoModel> list = new ArrayList<>();
    private int cont = 0;

    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO dados){
        for (ProdutoModel p : list){
            if (p.getNome().equals(dados.getNome())){
                ProdutoResponseDTO resposta = new ProdutoResponseDTO();
                resposta.setMensagem("PRODUTO JÁ EXISTE!");
                return resposta;
            }
        }
        cont += 1;
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setId((long) cont);
        produtoModel.setNome(dados.getNome());
        produtoModel.setPreco(dados.getPreco());
        produtoModel.setCategoriaID(dados.getCategoriaID());
        list.add(produtoModel);
        ProdutoResponseDTO resposta = new ProdutoResponseDTO();
        resposta.setMensagem("sucesso");
        resposta.setId((long) cont);
        resposta.setNome(dados.getNome());
        resposta.setPreco(dados.getPreco());
        resposta.setCategoriaID(dados.getCategoriaID());
        return resposta;
    }

    public List<ProdutoDTO> listarProdutos(){
        List<ProdutoDTO> listDTO = new ArrayList<>();
        for (ProdutoModel p : list){
            ProdutoDTO produtoDTO = new ProdutoDTO();
            produtoDTO.setId(p.getId());
            produtoDTO.setNome(p.getNome());
            produtoDTO.setPreco(p.getPreco());
            produtoDTO.setCategoriaID(p.getCategoriaID());
            listDTO.add(produtoDTO);
        }
        return listDTO;
    }
}
