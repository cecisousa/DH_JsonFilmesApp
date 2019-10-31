package com.example.dh_jsonfilmesapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dh_jsonfilmesapp.model.data.Database;
import com.example.dh_jsonfilmesapp.model.data.FilmeDao;
import com.example.dh_jsonfilmesapp.model.pojo.NovoFilme;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class AdicionarFilmeActivityViewModel extends AndroidViewModel {
    private MutableLiveData<NovoFilme> novoFilme = new MutableLiveData<>();
    private MutableLiveData<List<NovoFilme>> listaNovosFilmes = new MutableLiveData<>();
    private FilmeDao filmeDao = Database.getDatabase(getApplication()).filmeDao();
    private CompositeDisposable disposable = new CompositeDisposable();

    public AdicionarFilmeActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<NovoFilme>> retornaNovoFilme() {
        return this.listaNovosFilmes;
    }

    public void buscaNovosFilmes() {
        disposable.add(
                filmeDao.todosNovosFilmes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(novosFilmes -> {
                    listaNovosFilmes.setValue(novosFilmes);
                }, throwable -> {
                    Log.i("LOG", "Busca todos os novos filmes" + throwable.getMessage());
                })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

    public void insereFilmeBancoDados(NovoFilme novoFilme) {
        new Thread(() -> {
            if (novoFilme != null) {
                filmeDao.inserirFilme(novoFilme);
            }
        }).start();

        this.novoFilme.setValue(novoFilme);
    }
}
