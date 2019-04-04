package com.aibox.usuario.pedidos.pedidos.dominio;

import java.util.ArrayList;
import java.util.Date;

public class Pedidos {

    private int idProduto;
    private String status;
    private String finalizacao;
    private int idUsuario;
    private int _id;

    public Pedidos(int idUsuario, int idProduto, String status, String finalizacao){
        this.idUsuario = idUsuario;
        this.idProduto = idProduto;
        this.status = status;
        this.finalizacao = finalizacao;
    };

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getIdProdutos() {
        return idProduto;
    }

    public void setIdProdutos(int idProdutos) {
        this.idProduto = idProdutos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFinalizacao() {
        return finalizacao;
    }

    public void setFinalizacao(String finalizacao) {
        this.finalizacao = finalizacao;
    }
}
