package com.aibox.usuario.gui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import com.aibox.R;
import com.aibox.usuario.dao.UsuarioDao;
import com.aibox.usuario.dominio.Usuario;

import java.util.ArrayList;
import java.util.List;

public class CadastroActivity extends AppCompatActivity {

    private EditText cdNome, cdEnd, cdTel, cdEmail, cdSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        cdNome = (EditText) findViewById(R.id.cdNome);
        cdEnd = (EditText) findViewById(R.id.cdEnd);
        cdTel = (EditText) findViewById(R.id.cdTel);
        cdEmail = (EditText) findViewById(R.id.cdEmail);
        cdSenha = (EditText) findViewById(R.id.cdSenha);
    }


    public void cadastro(View view){

        String nome = cdNome.getText().toString();
        String endereco = cdEnd.getText().toString();
        String telefone = cdTel.getText().toString();
        String email = cdEmail.getText().toString();
        String senha = cdSenha.getText().toString();

        if(validarCampos(nome, endereco, telefone, email, senha)){
            Context context = getApplicationContext();
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEndereco(endereco);
            usuario.setTelefone(telefone);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            UsuarioDao usuarioDao = new UsuarioDao(context);
            if(!usuarioDao.existeUsuario(usuario)){
                usuarioDao.insertUsuarioDao(usuario);
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
            else{
                cdEmail.setError("Usuário já cadastrado");
            }
        }

    }

    public Boolean validarCampos(String nome, String endereco, String telefone, String email, String senha){

        Boolean result = true;
        if(nome.length() == 0){
            result = false;
            cdNome.setError("Campo Obrigatório");
        }
        if(endereco.length() == 0){
            result = false;
            cdEnd.setError("Campo Obrigatório");
        }
        if(telefone.length() == 0){
            result = false;
            cdTel.setError("Campo Obrigatório");
        }
        if(email.length() == 0){
            result = false;
            cdEmail.setError("Campo Obrigatório");
        }
        if(senha.length() == 0){
            result = false;
            cdSenha.setError("Campo Obrigatório");
        }
        return result;
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

}
