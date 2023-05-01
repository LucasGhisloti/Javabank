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
    public int clienteID;
    public int bancoID;
    public String tipo;
    public double saldo;
    public double limiteSaque;

    public Conta(int clienteID, int bancoID, String tipo, double saldo, double limiteSaque) {
        this.clienteID = clienteID;
        this.bancoID = bancoID;
        this.tipo = tipo;
        this.saldo = saldo;
        this.limiteSaque = limiteSaque;
    }

    public int getClienteID() {
        return clienteID;
    }

    public int getBancoID() {
        return bancoID;
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

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    public void setBancoID(int bancoID) {
        this.bancoID = bancoID;
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
