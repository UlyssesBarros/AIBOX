package com.aibox.usuario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aibox.R;
import com.aibox.usuario.produtos.dominio.Produto;

import java.math.BigDecimal;
import java.util.List;

import static com.aibox.usuario.ProdutoAdapter.currencyFormat;

public class ItensCarrinhoAdapter extends ArrayAdapter<Produto> {

    public ItensCarrinhoAdapter(Context context, List produtos){super(context, 0, produtos);}

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

        // Return the completed view to render on screen
        return convertView;
    }

}
