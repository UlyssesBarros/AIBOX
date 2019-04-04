package com.aibox.usuario.produtos.dominio;

import android.text.style.TtsSpan;

import java.math.BigDecimal;

public class Produto {

    private int _id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void produto(){};

    public void produto(String nome, String descricao, BigDecimal preco){
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
