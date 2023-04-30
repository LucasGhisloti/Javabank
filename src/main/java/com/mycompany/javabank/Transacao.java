package com.mycompany.javabank;

/**
 *
 * @author Guilherme Jardim
 */
public class Transacao {
    public int UUID;
    public String data;
    public String hora;
    public int de;
    public int para;
    public double quantia;
    public String tipo;

    public Transacao(int UUID, String data, String hora, int de, int para, double quantia, String tipo) {
        this.UUID = UUID;
        this.data = data;
        this.hora = hora;
        this.de = de;
        this.para = para;
        this.quantia = quantia;
        this.tipo = tipo;
    }

    public int getUUID() {
        return UUID;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public int getDe() {
        return de;
    }

    public int getPara() {
        return para;
    }

    public double getQuantia() {
        return quantia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setUUID(int UUID) {
        this.UUID = UUID;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setDe(int de) {
        this.de = de;
    }

    public void setPara(int para) {
        this.para = para;
    }

    public void setQuantia(double quantia) {
        this.quantia = quantia;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
