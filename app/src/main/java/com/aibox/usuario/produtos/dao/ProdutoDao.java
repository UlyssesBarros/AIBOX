package com.aibox.usuario.produtos.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aibox.usuario.dominio.Usuario;
import com.aibox.usuario.produtos.dominio.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao extends SQLiteOpenHelper {


    public ProdutoDao(Context context) {
        super(context, "Produtos", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE Produtos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, descricao TEXT, preco REAL); ";
        db.execSQL(sql);
        String insert = "INSERT INTO Produtos ( nome, descricao, preco ) Values ( 'macarrao', 'Massa com ovos', 4.50 ); ";
        db.execSQL(insert);
        String insert1 = "INSERT INTO Produtos ( nome, descricao, preco ) Values ( 'carne', 'picanha suína defumada', 54.50 ); ";
        db.execSQL(insert1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String sql = "DROP TABLE IF EXISTS Produtos";
        db.execSQL(sql);
        this.onCreate(db);

    }

    public List<Produto> listar(){
        List<Produto> result = new ArrayList<>();
        String sql = "SELECT * FROM Produtos";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            Produto produto = new Produto();
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            produto.set_id(id);
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            produto.setNome(nome);
            String descricao = cursor.getString(cursor.getColumnIndex("descricao"));
            produto.setDescricao(descricao);
            produto.setPreco(new BigDecimal(cursor.getString(cursor.getColumnIndex("preco"))));
            result.add(produto);

        } return result;
    }

    public Produto getProduto(int id){
        String sql = "SELECT * FROM Produtos WHERE id="+id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            Produto produto = new Produto();
            int _id = cursor.getInt(cursor.getColumnIndex("id"));
            produto.set_id(_id);
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            produto.setNome(nome);
            String descricao = cursor.getString(cursor.getColumnIndex("descricao"));
            produto.setDescricao(descricao);
            produto.setPreco(new BigDecimal(cursor.getString(cursor.getColumnIndex("preco"))));
            return produto;

        } return null;
    }

}
