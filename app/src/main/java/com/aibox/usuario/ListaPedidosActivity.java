package com.aibox.usuario;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.aibox.R;
import com.aibox.usuario.pedidos.pedidos.dao.PedidosDao;
import com.aibox.usuario.produtos.dao.ProdutoDao;
import com.aibox.usuario.produtos.dominio.Produto;

import java.util.ArrayList;

public class ListaPedidosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);
        ListView pedidos = (ListView) findViewById(R.id.listviewPedidos);

        PedidosDao idItens = new PedidosDao(this);
        SharedPreferences sharedPreferences = getSharedPreferences("LoginActivityPreferences", Context.MODE_PRIVATE);
        int idUsuario = sharedPreferences.getInt("IDUSUARIO", -1);
        ArrayList listIdItens = (ArrayList) idItens.listar(idUsuario);

        final ArrayList<Produto> produtos = listaProdutos(listIdItens);
        ItensCarrinhoAdapter itensCarrinhoAdapter = new ItensCarrinhoAdapter(this, produtos);
        pedidos.setAdapter(itensCarrinhoAdapter);


    }

    public ArrayList<Produto> listaProdutos(ArrayList ids){
        ArrayList<Produto> result = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            Produto produto = new Produto();
            ProdutoDao produtoDao = new ProdutoDao(this);
            int valor = (Integer) ids.get(i);
            produto = produtoDao.getProduto(valor);
            result.add(produto);
        }
        return result;
    }

}
