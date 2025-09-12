package com.senai.crud.services;

import com.senai.crud.dtos.ProdutoDTO;
import com.senai.crud.dtos.ProdutoRequisiçãoDTO;
import com.senai.crud.dtos.RespostaDto;
import com.senai.crud.models.ProdutoModel;
import com.senai.crud.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
        private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public RespostaDto criarProduto(ProdutoRequisiçãoDTO dados){
            ProdutoModel produto = new ProdutoModel();
            produto.setNome(dados.getNome());
            produto.setPreco(dados.getPreco());
            produto.setCategoriaID(dados.getCategoriaId());
            repository.save(produto);

            RespostaDto resposta = new  RespostaDto();
            resposta.setMensagem("sucesso");
            return resposta ;
        }

        public RespostaDto excluirProduto(Long id){
            Optional<ProdutoModel> model = repository.findById(id);

            if (model.isPresent()){
                repository.delete(model.get());
                RespostaDto resposta = new  RespostaDto();
                resposta.setMensagem("sucesso");
                return resposta ;
            }
            RespostaDto resposta = new  RespostaDto();
            resposta.setMensagem("Não foi possível remover o produto id = " + id);
            return resposta ;
        }

        public RespostaDto atualizarProduto(Long id, ProdutoRequisiçãoDTO dados){
            Optional<ProdutoModel> model = repository.findById(id);

            if (model.isPresent()){
                ProdutoModel produto = model.get();
                produto.setNome(dados.getNome());
                produto.setPreco(dados.getPreco());
                produto.setCategoriaID(dados.getCategoriaId());
                repository.save(produto);
                RespostaDto resposta = new  RespostaDto();
                resposta.setMensagem("sucesso");
                return resposta ;
            }
            RespostaDto resposta = new  RespostaDto();
            resposta.setMensagem("Não foi possível atualziar o produto id = " + id);
            return resposta ;
        }

        public ProdutoDTO obterProduto(Long id){
            ProdutoDTO produtoDTO = new ProdutoDTO();
            Optional<ProdutoModel> model = repository.findById(id);

            if (model.isPresent()){
                produtoDTO.setId(model.get().getId());
                produtoDTO.setNome(model.get().getNome());
                produtoDTO.setPreco(model.get().getPreco());
                produtoDTO.setCategoriaID(model.get().getCategoriaID());
                return produtoDTO;
            }
            return produtoDTO;
        }

        public List<ProdutoDTO> obterProdutos(){
            List<ProdutoDTO> produtoDTO = new ArrayList<>();
            List<ProdutoModel> produtoModel = repository.findAll();

            for (ProdutoModel produto :  produtoModel) {
                ProdutoDTO produtos = new ProdutoDTO();
                produtos.setId(produto.getId());
                produtos.setNome(produto.getNome());
                produtos.setPreco(produto.getPreco());
                produtos.setCategoriaID(produto.getCategoriaID());
                produtoDTO.add(produtos);
            }
            return produtoDTO;
        }
}
