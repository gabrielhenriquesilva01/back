package com.senai.crud.dtos;

import com.senai.crud.models.CategoriaModel;

public class ProdutoRequisiçãoDTO {
    private String nome;
    private double preco;
    private Long categoriaModel;

    public ProdutoRequisiçãoDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Long getCategoriaId() {
        return categoriaModel;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaModel = categoriaId;
    }
}
