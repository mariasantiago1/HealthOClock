package com.example.doug.healthoclock.model;

/**
 * Created by Doug on 08/02/2017.
 */

public class Exame {
    private int id_ans;
    private String descricao;

    public Exame() {
    }

    public Exame(int id_ans, String descricao) {
        this.id_ans = id_ans;
        this.descricao = descricao;
    }

    public int getId_ans() {
        return id_ans;
    }

    public void setId_ans(int id_ans) {
        this.id_ans = id_ans;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
