package com.senai.crud.services;

import com.senai.crud.dtos.ProdutoDTO;
import com.senai.crud.dtos.ProdutoRequisiçãoDTO;
import com.senai.crud.dtos.RespostaDto;
import com.senai.crud.models.CategoriaModel;
import com.senai.crud.models.ProdutoModel;
import com.senai.crud.repositories.CategoriaRepository;
import com.senai.crud.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
        private final ProdutoRepository repository;
        private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository repository, CategoriaRepository categoriaRepository) {
        this.repository = repository;
        this.categoriaRepository = categoriaRepository;
    }

    public RespostaDto criarProduto(ProdutoDTO dados) {

        Optional<CategoriaModel> categoriaModel = categoriaRepository.findById(dados.getCategoriaID());

        if ( categoriaModel.isPresent() ) {
            ProdutoModel produto = new ProdutoModel();
            produto.setNome(dados.getNome());
            produto.setPreco(dados.getPreco());
            produto.setCategoriaModel(categoriaModel.get());
            repository.save(produto);

            RespostaDto resposta = new RespostaDto();
            resposta.setMensagem("sucesso");
            return resposta;
        }
        RespostaDto resposta = new  RespostaDto();
        resposta.setMensagem("Não foi possível criar o produto");
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

        public RespostaDto atualizarProduto(Long id, ProdutoDTO dados){

        Optional<CategoriaModel> categoriaModel = categoriaRepository.findById(dados.getCategoriaID());
        if (categoriaModel.isPresent()) {
            Optional<ProdutoModel> model = repository.findById(id);

            if ( model.isPresent() ) {
                ProdutoModel produto = model.get();
                produto.setNome(dados.getNome());
                produto.setPreco(dados.getPreco());
                repository.save(produto);
                RespostaDto resposta = new RespostaDto();
                resposta.setMensagem("sucesso");
                return resposta;
            }
            RespostaDto resposta = new RespostaDto();
            resposta.setMensagem("Não foi possível atualziar o produto id = " + id);
            return resposta;
        }
            RespostaDto resposta = new RespostaDto();
            resposta.setMensagem("Não foi possível localizar a categoria");
            return resposta;
        }

        public ProdutoDTO obterProduto(Long id){
            ProdutoDTO produtoDTO = new ProdutoDTO();
            Optional<ProdutoModel> model = repository.findById(id);

            if (model.isPresent()){
                produtoDTO.setId(model.get().getId());
                produtoDTO.setNome(model.get().getNome());
                produtoDTO.setPreco(model.get().getPreco());
                produtoDTO.setCategoriaID(model.get().getCategoriaModel().getId());
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
                produtos.setCategoriaID(produto.getCategoriaModel().getId());
                produtoDTO.add(produtos);
            }
            return produtoDTO;
        }
}
