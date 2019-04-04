package com.aibox.usuario;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.aibox.R;
import com.aibox.usuario.produtos.dao.ProdutoDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
        FloatingActionButton fabCart = (FloatingActionButton) findViewById(R.id.fabCarrinho);
        FloatingActionButton fabPedidos = (FloatingActionButton) findViewById(R.id.fabPedidos);

        fabPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                Intent intent = new Intent(context, ListaPedidosActivity.class);
                startActivity(intent);
            }
        });

        fabCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                Intent intent = new Intent(context, CarrinhoActivity.class);
                startActivity(intent);
            }
        });

        // Aqui estao suas imagens dentro do drawable
        int[] mResources = {
                R.drawable.aiboxlogo,
                R.drawable.ic_cart
        };

        CustomPagerAdapter mCustomPagerAdapter = new CustomPagerAdapter(this, mResources);
        mViewPager.setAdapter(mCustomPagerAdapter);

        ProdutoDao pessoaDao = new ProdutoDao(this);
        List itens = pessoaDao.listar();
        ListView lista = (ListView) findViewById(R.id.listview_Lista);

        ProdutoAdapter adapter = new ProdutoAdapter(this, itens);

        lista.setAdapter(adapter);



    }


}
