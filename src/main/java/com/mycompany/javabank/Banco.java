package com.mycompany.javabank;

public class Banco {
    private int ID;
    private String nome;

    public Banco(int ID, String nome) {
        this.ID = ID;
        this.nome = nome;
    }

    public int getID() {
        return ID;
    }

    public String getNome() {
        return nome;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
