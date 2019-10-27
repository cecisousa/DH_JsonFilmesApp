package com.example.dh_jsonfilmesapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dh_jsonfilmesapp.model.data.Database;
import com.example.dh_jsonfilmesapp.model.data.FilmeDao;
import com.example.dh_jsonfilmesapp.model.pojo.NovoFilme;

public class AdicionarFilmeActivityViewModel extends AndroidViewModel {
    private MutableLiveData<NovoFilme> novoFilme = new MutableLiveData<>();
    private FilmeDao filmeDao = Database.getDatabase(getApplication()).filmeDao();

    public AdicionarFilmeActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<NovoFilme> retornaNovoFilme() {
        return this.novoFilme;
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
