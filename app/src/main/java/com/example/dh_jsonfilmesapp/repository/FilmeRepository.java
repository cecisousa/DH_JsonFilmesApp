package com.example.dh_jsonfilmesapp.repository;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.dh_jsonfilmesapp.model.pojo.FilmeResposta;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.reactivex.Observable;

public class FilmeRepository {

    public Observable<FilmeResposta> obterListaFilmes (Context context) {
        try {
            AssetManager manager = context.getAssets();
            InputStream inputStream = manager.open("filmes.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            Gson gson = new Gson();
            FilmeResposta resposta = gson.fromJson(reader, FilmeResposta.class);
            return Observable.just(resposta);

        } catch (Exception ex) {
            ex.printStackTrace();
            return Observable.error(ex.getCause());
        }
    }
}
