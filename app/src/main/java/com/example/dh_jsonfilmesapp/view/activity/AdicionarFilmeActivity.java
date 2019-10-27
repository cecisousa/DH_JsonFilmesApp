package com.example.dh_jsonfilmesapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.dh_jsonfilmesapp.R;
import com.example.dh_jsonfilmesapp.model.pojo.Filme;
import com.example.dh_jsonfilmesapp.model.pojo.NovoFilme;
import com.example.dh_jsonfilmesapp.viewmodel.AdicionarFilmeActivityViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class AdicionarFilmeActivity extends AppCompatActivity {
    private TextInputLayout titulo;
    private TextInputLayout data;
    private TextInputLayout descricao;
    private TextInputLayout direcao;
    private Button btnAddFilme;
    private AdicionarFilmeActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_filme);

        initViews ();

        btnAddFilme.setOnClickListener(v -> {
            String tituloFilme = titulo.getEditText().getText().toString();
            String dataFilme = data.getEditText().getText().toString();
            String descricaoFilme = descricao.getEditText().getText().toString();
            String direcaoFilme = direcao.getEditText().getText().toString();

            if (!tituloFilme.isEmpty() && !dataFilme.isEmpty() && !descricaoFilme.isEmpty() && !direcaoFilme.isEmpty()) {
                NovoFilme novoFilme = new NovoFilme(tituloFilme, dataFilme, descricaoFilme, direcaoFilme);

                viewModel.insereFilmeBancoDados(novoFilme);

                viewModel.retornaNovoFilme().observe(this, filmeRetornado -> {
                    Log.i("LOG", "Filme inserido no banco" + filmeRetornado.toString());
                });

                titulo.getEditText().setText("");
                data.getEditText().setText("");
                descricao.getEditText().setText("");
                direcao.getEditText().setText("");

            } else {
                titulo.setError("Preencha o título do filme!");
                data.setError("Preencha a data do filme!");
                descricao.setError("Preencha a descrição do filme!");
                direcao.setError("Preencha a direção do filme!");
            }

            startActivity(new Intent(AdicionarFilmeActivity.this, FilmeActivity.class));
        });

    }

    public void initViews () {
        titulo = findViewById(R.id.txtTitulo);
        data = findViewById(R.id.txtData);
        descricao = findViewById(R.id.txtDescricao);
        direcao = findViewById(R.id.txtDiretor);
        btnAddFilme = findViewById(R.id.buttonAdd);
        viewModel = ViewModelProviders.of(this).get(AdicionarFilmeActivityViewModel.class);
    }
}
