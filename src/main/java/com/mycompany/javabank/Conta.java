/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javabank;

/**
 *
 * @author Lucas Ghisloti
 */
public class Conta {
    public int ID;
    public int clienteID;
    public String tipo;
    public double saldo;
    public double limiteSaque;

    public Conta(int ID, int clienteID, String tipo, double saldo, double limiteSaque) {
        this.ID = ID;
        this.clienteID = clienteID;
        this.tipo = tipo;
        this.saldo = saldo;
        this.limiteSaque = limiteSaque;
    }

    public int getID() {
        return ID;
    }

    public int getClienteID() {
        return clienteID;
    }

    public String getTipo() {
        return tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimiteSaque() {
        return limiteSaque;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setLimiteSaque(double limiteSaque) {
        this.limiteSaque = limiteSaque;
    }
}
