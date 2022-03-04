package com.example.despesaspessoais.entidade;

import java.util.Date;

public class Despesa {

    private int id;
    private String nome;
    private float valor;
    private Date data;

    public Despesa() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public float getValor() {
        return valor;
    }

    public Date getData() {
        return data;
    }
}
