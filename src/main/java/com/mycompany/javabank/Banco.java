package com.mycompany.javabank;

public class Banco {
    private int ID;
    private String nome;
    private double limiteInicialDeSaque;

    public Banco(int ID, String nome, double limite) {
        this.ID = ID;
        this.nome = nome;
        this.limiteInicialDeSaque = limite;
    }

    public int getID() {
        return ID;
    }

    public String getNome() {
        return nome;
    }

    public double getLimiteInicialDeSaque() {
        return limiteInicialDeSaque;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
