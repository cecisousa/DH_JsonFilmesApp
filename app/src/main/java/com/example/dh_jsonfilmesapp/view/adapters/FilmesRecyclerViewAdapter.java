package com.example.dh_jsonfilmesapp.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dh_jsonfilmesapp.R;
import com.example.dh_jsonfilmesapp.model.pojo.Filme;

import java.util.List;

public class FilmesRecyclerViewAdapter extends RecyclerView.Adapter<FilmesRecyclerViewAdapter.ViewHolder> {
    private List<Filme> listaFilmes;

    public FilmesRecyclerViewAdapter(List<Filme> listaFilmes) {
        this.listaFilmes = listaFilmes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Filme filme = listaFilmes.get(position);
        holder.onBind(filme);
    }

    @Override
    public int getItemCount() {
        return listaFilmes.size();
    }

    public void updateFilmes(List<Filme> filmesList) {
        this.listaFilmes.clear();
        this.listaFilmes = filmesList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTitulo;
        private TextView txtData;
        private TextView txtDescricao;
        private TextView txtDiretor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitulo = itemView.findViewById(R.id.textViewTitulo);
            txtData = itemView.findViewById(R.id.textViewData);
            txtDescricao = itemView.findViewById(R.id.textViewDescricao);
            txtDiretor = itemView.findViewById(R.id.textViewDirecao);
        }

        public void onBind(Filme filme) {
            txtTitulo.setText(filme.getTitulo());
            txtData.setText(filme.getData());
            txtDescricao.setText(filme.getDescricao());
            txtDiretor.setText(filme.getDirecao());
        }
    }
}
