package com.example.dh_jsonfilmesapp.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dh_jsonfilmesapp.R;
import com.example.dh_jsonfilmesapp.model.pojo.NovoFilme;

import java.util.List;

public class NovoFilmeRecyclerViewAdapter extends RecyclerView.Adapter<NovoFilmeRecyclerViewAdapter.ViewHolder> {

    private List<NovoFilme> novoFilmeList;

    public NovoFilmeRecyclerViewAdapter (List<NovoFilme> novoFilmeList) {
        this.novoFilmeList = novoFilmeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NovoFilme novoFilme = novoFilmeList.get(position);
        holder.onBind(novoFilme);
    }

    @Override
    public int getItemCount() {
        return novoFilmeList.size();
    }


    public void updateFilmesAdicionados(List<NovoFilme> listaNovosFilmes) {
        this.novoFilmeList.clear();
        this.novoFilmeList = listaNovosFilmes;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titulo;
        private TextView data;
        private TextView descricao;
        private TextView direcao;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.textViewTitulo);
            data = itemView.findViewById(R.id.textViewData);
            descricao = itemView.findViewById(R.id.textViewDescricao);
            direcao = itemView.findViewById(R.id.textViewDirecao);
        }

        public void onBind(NovoFilme novoFilme) {
            titulo.setText(novoFilme.getTituloFilme());
            data.setText(novoFilme.getDataFilme());
            descricao.setText(novoFilme.getDescricaoFilme());
            direcao.setText(novoFilme.getDirecaoFilme());
        }
    }
}
