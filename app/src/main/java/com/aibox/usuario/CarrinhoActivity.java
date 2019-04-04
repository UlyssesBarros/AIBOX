package com.aibox.usuario;

import android.content.Context;
import android.content.Intent;
import android.content.PeriodicSync;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aibox.R;
import com.aibox.usuario.pedidos.pedidos.dao.PedidosDao;
import com.aibox.usuario.pedidos.pedidos.dominio.Pedidos;
import com.aibox.usuario.produtos.dao.ProdutoDao;
import com.aibox.usuario.produtos.dominio.Produto;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoActivity extends AppCompatActivity {

    private BigDecimal valorTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        TextView txtValor = (TextView) findViewById(R.id.txtValorTotal);
        ListView listView = (ListView) findViewById(R.id.listaItensCarrinho);
        final Button finalizarCompra = (Button) findViewById(R.id.btnConfirmarCompra);

        itensCarrinhoDao idItens = new itensCarrinhoDao(this);
        SharedPreferences sharedPreferences = getSharedPreferences("LoginActivityPreferences", Context.MODE_PRIVATE);
        int idUsuario = sharedPreferences.getInt("IDUSUARIO", -1);
        ArrayList listIdItens = (ArrayList) idItens.listar(idUsuario);

        final ArrayList<Produto> produtos = listaProdutos(listIdItens);
        BigDecimal preco = soma(produtos);
        txtValor.setText("Valor Total: "+ currencyFormat(preco));
        //Toast toast = Toast.makeText(this, produtos.get(0).getNome(), Toast.LENGTH_SHORT);
        //toast.show();
        ItensCarrinhoAdapter itensCarrinhoAdapter = new ItensCarrinhoAdapter(this, produtos);
        listView.setAdapter(itensCarrinhoAdapter);

        finalizarCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalizarCompra(produtos);
                Context context = getApplicationContext();
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
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

    public BigDecimal soma(ArrayList produtos){
        BigDecimal valorSoma = BigDecimal.valueOf(0);
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = (Produto) produtos.get(i);
            valorSoma = valorSoma.add(produto.getPreco());
        }
        return valorSoma;
    }

    public static String currencyFormat(BigDecimal n) {
        return NumberFormat.getCurrencyInstance().format(n);
    }

    public void finalizarCompra(ArrayList produtos){
        SharedPreferences sharedPreferences = getSharedPreferences("LoginActivityPreferences", Context.MODE_PRIVATE);
        int idUsuario = sharedPreferences.getInt("IDUSUARIO", -1);
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = (Produto) produtos.get(i);
            Context context = getApplicationContext();
            PedidosDao pedidosDao = new PedidosDao(context);
            int idProduto = produto.get_id();
            Pedidos pedidos = new Pedidos(idUsuario,idProduto,"entregar","aberto");
            pedidosDao.insertPedidoDao(pedidos);
        }

    }



}
