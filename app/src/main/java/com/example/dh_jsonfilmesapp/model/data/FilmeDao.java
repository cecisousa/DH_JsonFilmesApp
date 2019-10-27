package com.example.dh_jsonfilmesapp.model.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dh_jsonfilmesapp.model.pojo.NovoFilme;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface FilmeDao {

    @Insert
    void inserirFilme (NovoFilme novoFilme);

    @Query("SELECT * FROM filmes")
    Observable<List<NovoFilme>> todosNovosFilmes();
}
