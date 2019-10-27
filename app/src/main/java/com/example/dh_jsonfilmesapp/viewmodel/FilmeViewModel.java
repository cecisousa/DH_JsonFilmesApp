package com.example.dh_jsonfilmesapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dh_jsonfilmesapp.model.pojo.Filme;
import com.example.dh_jsonfilmesapp.repository.FilmeRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class FilmeViewModel extends AndroidViewModel {
    private MutableLiveData<List<Filme>> listaFilmes = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private FilmeRepository repository = new FilmeRepository();
    private CompositeDisposable disposable = new CompositeDisposable();

    public FilmeViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Filme>> retornaFilmes() {
        return this.listaFilmes;
    }

    public LiveData<Boolean> retornaValorLoading() {
        return this.loading;
    }

    public void buscaFilmes() {
        disposable.add(
                repository.obterListaFilmes(getApplication().getApplicationContext())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable1 -> loading.setValue(true))
                .doAfterTerminate(() -> loading.setValue(false))
                .subscribe(filmeResposta -> {
                    listaFilmes.setValue(filmeResposta.getFilmes());
                }, throwable -> {
                    Log.i("LOG", "Busca filmes" + throwable.getMessage());
                })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
