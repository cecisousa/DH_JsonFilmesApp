package com.example.dh_jsonfilmesapp.views.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.dh_jsonfilmesapp.R;
import com.example.dh_jsonfilmesapp.model.pojo.Filme;
import com.example.dh_jsonfilmesapp.model.pojo.NovoFilme;
import com.example.dh_jsonfilmesapp.viewmodel.AdicionarFilmeActivityViewModel;
import com.example.dh_jsonfilmesapp.views.adapters.FilmesRecyclerViewAdapter;
import com.example.dh_jsonfilmesapp.viewmodel.FilmeViewModel;
import com.example.dh_jsonfilmesapp.views.adapters.NovoFilmeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class FilmeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private ProgressBar progressBar;
    private FilmeViewModel viewModel;
    private FilmesRecyclerViewAdapter adapter;
    private List<Filme> filmes = new ArrayList<>();
    private AdicionarFilmeActivityViewModel viewModel2;
    private NovoFilmeRecyclerViewAdapter adapter2;
    private List<NovoFilme> filmesAdicionados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);

        initViews();

        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(adapter2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));


        viewModel.buscaFilmes();

        viewModel.retornaFilmes().observe(this, filmesRetornados -> {
            adapter.updateFilmes(filmesRetornados);
        });

        viewModel2.buscaNovosFilmes();

        viewModel2.retornaNovoFilme().observe(this, novosFilmesRetornados -> {
            adapter2.updateFilmesAdicionados(novosFilmesRetornados);
        });

        viewModel.retornaValorLoading().observe(this, loading -> {
            if (loading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_addfilme) {
            startActivity(new Intent(FilmeActivity.this, AdicionarFilmeActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        recyclerView = findViewById(R.id.listaRecyclerView);
        recyclerView2 = findViewById(R.id.listaRecyclerView2);
        progressBar = findViewById(R.id.progressBar);
        viewModel = ViewModelProviders.of(this).get(FilmeViewModel.class);
        adapter = new FilmesRecyclerViewAdapter(filmes);
        viewModel2 = ViewModelProviders.of(this).get(AdicionarFilmeActivityViewModel.class);
        adapter2 = new NovoFilmeRecyclerViewAdapter(filmesAdicionados);
    }
}
