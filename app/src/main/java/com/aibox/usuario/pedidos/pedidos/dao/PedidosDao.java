package com.aibox.usuario.pedidos.pedidos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aibox.usuario.pedidos.pedidos.dominio.Pedidos;

import java.util.ArrayList;
import java.util.List;

public class PedidosDao extends SQLiteOpenHelper {

    public PedidosDao(Context context) {
        super(context, "Pedidos", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE Pedidos (id INTEGER PRIMARY KEY, idProduto INTEGER NOT NULL, idUsuario INTEGER NOT NULL, status TEXT, dtFinalizacao TEXT); ";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String sql = "DROP TABLE IF EXISTS Pedidos";
        db.execSQL(sql);
        this.onCreate(db);

    }

    public void insertPedidoDao(Pedidos pedidos){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("idProduto", pedidos.get_id());
        cv.put("idUsuario", pedidos.getIdUsuario());
        cv.put("status", pedidos.getStatus());
        cv.put("dtFinalizacao", pedidos.getFinalizacao());
        db.insert("Pedidos",null,cv);
        db.close();

    }

    public List listar(int idUsuario){
        List result = new ArrayList<>();
        String sql = "SELECT * FROM Pedidos WHERE idUsuario="+idUsuario;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("idProduto"));
            result.add(id);

        } return result;
    }


}
