package com.mycompany.javabank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Guilherme Jardim
 */
public class Transacao {
    public LocalDate data;
    public LocalTime hora;
    public int de;
    public int para;
    public double quantia;
    public String tipo;

    public Transacao(String data, String hora, int de, int para, double quantia, String tipo) {
        this.setData(data);
        this.setHora(hora);
        this.de = de;
        this.para = para;
        this.quantia = quantia;
        this.tipo = tipo;
    }

    public String getData() {
        return this.data.toString();
    }

    public String getHora() {
        return this.hora.toString();
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

    public void setData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.data = LocalDate.parse(data, formatter);
    }

    public void setHora(String hora) {
        this.hora = LocalTime.parse(hora);
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
    
    @Override
    public String toString(){
        String texto;
        //TODO: substituir id em "DE" e "PARA" por nomes
        texto = this.getDe()+" -> "+this.getPara()+
                ", "+this.getData()+" as "+this.getHora()+"\n";
        texto += "Tipo: "+this.getTipo()+"\n";
        texto += "Valor: "+this.getQuantia();
        return texto;
    }
}