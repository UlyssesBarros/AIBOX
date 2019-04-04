package com.aibox.usuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aibox.usuario.dominio.Usuario;

import java.util.ArrayList;
import java.util.List;

public class itensCarrinhoDao extends SQLiteOpenHelper {

    public itensCarrinhoDao(Context context) {
        super(context, "ItensCarrinho", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE ItensCarrinho (id INTEGER PRIMARY KEY, idProduto INTEGER NOT NULL, idUsuario INTEGER NOT NULL); ";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String sql = "DROP TABLE IF EXISTS ItensCarrinho";
        db.execSQL(sql);
        this.onCreate(db);

    }

    public void insertItenCarrinhoDao(int id, int idUsuario){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("idProduto", id);
        cv.put("idUsuario", idUsuario);
        db.insert("ItensCarrinho",null,cv);
        db.close();

    }

    public List listar(int idUsuario){
        List result = new ArrayList<>();
        String sql = "SELECT * FROM ItensCarrinho WHERE idUsuario="+idUsuario;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("idProduto"));
            result.add(id);

        } return result;
    }



}
