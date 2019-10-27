package com.example.dh_jsonfilmesapp.model.pojo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "filmes")
public class NovoFilme {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "titulo")
    private String tituloFilme;

    @ColumnInfo(name = "data")
    private String dataFilme;

    @ColumnInfo(name = "descricao")
    private String descricaoFilme;

    @ColumnInfo(name = "direcao")
    private String direcaoFilme;

    public NovoFilme (String tituloFilme, String dataFilme, String descricaoFilme, String direcaoFilme) {
        this.tituloFilme = tituloFilme;
        this.dataFilme = dataFilme;
        this.descricaoFilme = descricaoFilme;
        this.direcaoFilme = direcaoFilme;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTituloFilme() {
        return tituloFilme;
    }

    public void setTituloFilme(String tituloFilme) {
        this.tituloFilme = tituloFilme;
    }

    public String getDataFilme() {
        return dataFilme;
    }

    public void setDataFilme(String dataFilme) {
        this.dataFilme = dataFilme;
    }

    public String getDescricaoFilme() {
        return descricaoFilme;
    }

    public void setDescricaoFilme(String descricaoFilme) {
        this.descricaoFilme = descricaoFilme;
    }

    public String getDirecaoFilme() {
        return direcaoFilme;
    }

    public void setDirecaoFilme(String direcaoFilme) {
        this.direcaoFilme = direcaoFilme;
    }
}
