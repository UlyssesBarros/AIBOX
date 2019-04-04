package com.aibox.usuario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aibox.usuario.dominio.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDao extends SQLiteOpenHelper {

    public UsuarioDao(Context context) {
        super(context, "Usuarios", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE Usuarios (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, email TEXT, senha TEXT); ";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String sql = "DROP TABLE IF EXISTS Usuarios";
        db.execSQL(sql);
        this.onCreate(db);

    }

    public void insertUsuarioDao(Usuario usuario){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", usuario.getNome());
        cv.put("endereco", usuario.getEndereco());
        cv.put("telefone", usuario.getTelefone());
        cv.put("email", usuario.getEmail());
        cv.put("senha", usuario.getSenha());

        db.insert("Usuarios",null,cv);
        db.close();

    }

    public Boolean existeUsuario(Usuario usuario){
        String email = usuario.getEmail();
        String senha = usuario.getSenha();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("Usuarios", null, " email=? and senha=?",
                new String[] { email,senha }, null, null, null);
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            return true;
        }
        return false;
    }

    public Usuario getUsuario(Usuario usuario){
        String email = usuario.getEmail();
        String senha = usuario.getSenha();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("Usuarios", null, " email=? and senha=?",
                new String[] { email,senha }, null, null, null);
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            usuario.set_id(id);
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            usuario.setNome(nome);
            String end = cursor.getString(cursor.getColumnIndex("endereco"));
            usuario.setNome(end);
            String tel = cursor.getString(cursor.getColumnIndex("telefone"));
            usuario.setNome(tel);
            String emails = cursor.getString(cursor.getColumnIndex("email"));
            usuario.setEmail(emails);
            String password = cursor.getString(cursor.getColumnIndex("senha"));
            usuario.setTelefone(password);
        }
        return usuario;
    }

    public List<Usuario> listar(){
        List<Usuario> result = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            Usuario usuario = new Usuario();
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            usuario.set_id(id);
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            usuario.setNome(nome);
            String endereco = cursor.getString(cursor.getColumnIndex("endereco"));
            usuario.setEndereco(endereco);
            String telefone = cursor.getString(cursor.getColumnIndex("telefone"));
            usuario.setTelefone(telefone);
            String email = cursor.getString(cursor.getColumnIndex("email"));
            usuario.setEmail(email);
            String senha = cursor.getString(cursor.getColumnIndex("senha"));
            usuario.setSenha(senha);
            result.add(usuario);

        } return result;
    }



}
