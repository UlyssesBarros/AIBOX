package com.aibox.usuario.dominio;

public class Usuario {

    private int _id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String senha;

    public Usuario(){};

    public Usuario(String email, String senha){
        this.email = email;
        this.senha = senha;
    }

    public Usuario(int id, String nome, String endereco, String telefone, String email, String senha){
        this._id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;

    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
