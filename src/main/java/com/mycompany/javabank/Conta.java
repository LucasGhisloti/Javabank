/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javabank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Conta {
    private int clienteID;
    private int bancoID;
    private String tipo;
    private double saldo;
    private double limiteSaque;
    private static int IDcount = 1;
    private int ID;
    public Conta(int clienteID, int bancoID, String tipo, double saldo, double limiteSaque) {
        this.ID= IDcount++;
        this.clienteID = clienteID;
        this.bancoID = bancoID;
        this.tipo = tipo;
        this.saldo = saldo;
        this.limiteSaque = limiteSaque;
    }

    public int getClienteID() {
        return clienteID;
    }

    public int getID() {
        return ID;
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

    public void GerarExtrato(Models model, String dtini, String dtfim){
        ArrayList<Transacao> extrato;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        
        extrato = model.getTransacoesFromConta(
                this.ID,
                LocalDate.parse(dtini, formatter),
                LocalDate.parse(dtfim, formatter)
        );

        System.out.println(this.tipo+"\n");

        if(extrato.isEmpty()){
            System.out.println("Nao ha transacoes nesse periodo\n");
        }else{
            extrato.forEach((t) -> {
                System.out.println(t.toString(model) + "\n");
            });
        }

        System.out.println("Saldo atual: "+this.getSaldo()+"\n");
    }
}
