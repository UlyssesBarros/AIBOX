package com.aibox.usuario;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aibox.R;
import com.aibox.usuario.produtos.dominio.Produto;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ProdutoAdapter extends ArrayAdapter<Produto> {

    private AlertDialog alerta;


    public ProdutoAdapter(Context context, List produtos) {
        super(context, 0, produtos);
    }

    public ProdutoAdapter(Context context){
        super(context,0);
    };

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Produto produto = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_produto, parent, false);
        }
        // Lookup view for data population
        TextView tvNome = (TextView) convertView.findViewById(R.id.tvNome);
        TextView tvDescricao = (TextView) convertView.findViewById(R.id.tvDescricao);
        TextView tvPreco = (TextView) convertView.findViewById(R.id.precoProduto);
        ImageView imgProduto = (ImageView) convertView.findViewById(R.id.imgProduto);
        LinearLayout item = (LinearLayout) convertView.findViewById(R.id.item);
        // Populate the data into the template view using the data object
        tvNome.setText(produto.getNome());
        tvDescricao.setText("Descrição: " + produto.getDescricao());
        BigDecimal preco = (produto.getPreco());
        tvPreco.setText("Preço: " + currencyFormat(preco));
        final Context context = convertView.getContext();
        int imageid = context.getResources().getIdentifier(produto.getNome(), "drawable", context.getPackageName());
        imgProduto.setImageResource(imageid);

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmarProduto(produto);
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }

    private void confirmarProduto(final Produto produto) {
        final Context context = getContext();
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //define o titulo
        builder.setTitle(produto.getNome());
        //define a mensagem
        builder.setMessage("Deseja adicionar este produto ao carrinho?");
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("LoginActivityPreferences", Context.MODE_PRIVATE);
                int idUsuario = sharedPreferences.getInt("IDUSUARIO", -1);
                itensCarrinhoDao itens = new itensCarrinhoDao(context);
                itens.insertItenCarrinhoDao(produto.get_id(), idUsuario);
                //Toast.makeText(context,produto.getNome(), Toast.LENGTH_SHORT).show();
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //Toast.makeText(context, "negativo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }

    public static String currencyFormat(BigDecimal n) {
        return NumberFormat.getCurrencyInstance().format(n);
    }

}
