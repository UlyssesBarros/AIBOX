package com.aibox.usuario.gui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.aibox.R;
import com.aibox.usuario.MainActivity;
import com.aibox.usuario.dao.UsuarioDao;
import com.aibox.usuario.dominio.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtSenha;
    String PREFERENCE_NAME = "LoginActivityPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail = (EditText) findViewById(R.id.edEmail);
        edtSenha = (EditText) findViewById(R.id.edSenha);
    }

    public void logar(View view) {

        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();

        if(validarCampos(email, senha)){
            Context context = getApplicationContext();
            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setSenha(senha);
            UsuarioDao usuarioDao = new UsuarioDao(context);
            if(usuarioDao.existeUsuario(usuario)){
                usuario = usuarioDao.getUsuario(usuario);
                SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor     = sharedPreferences.edit();
                editor.putInt("IDUSUARIO", usuario.get_id());
                editor.putString("NOME",usuario.getNome());
                editor.putString("ENDERECO",usuario.getEndereco());
                editor.putString("TELEFONE",usuario.getTelefone());
                editor.putString("EMAIL",usuario.getEmail());
                editor.putString("SENHA",usuario.getSenha());
                startActivity(new Intent(this, MainActivity.class));
                editor.commit();
                finish();
            }
            else{
                edtSenha.setError("Email ou senha inválidos");
            }
        }
    }

    public Boolean validarCampos(String email, String senha){

        Boolean result = true;
        if(email.length() == 0){
            result = false;
            edtSenha.setError("Campo Obrigatório");
        }
        if(senha.length() == 0){
            result = false;
            edtSenha.setError("Campo Obrigatório");
        }
        return result;
    }

    public void cadastro(View view){
        startActivity(new Intent(this, CadastroActivity.class));
    }

}
