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
    private double saldo;
    private double limiteSaque;
    
    Conta(int ID, int clienteID, String tipo, double saldo, double limiteSaque){
        this.setID(ID);
        this.setClienteID(clienteID);
        this.setSaldo(saldo);
        this.setTipo(tipo);
        this.setLimiteSaque(limiteSaque);
    }
    
    public void setID(int id){
        this.ID = id;
    }
    
    public int getID(){
        return this.ID;
    }
    
    public void setClienteID(int clienteID){
        this.clienteID = clienteID;
    }
    
    public int getClienteID(){
        return this.clienteID;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    public String getTipo(){
        return this.tipo;
    }
    
    public void setSaldo(double saldo){
        this.saldo = saldo;
    }
    
    public double getSaldo(){
        return this.saldo;
    }
    
    public void setLimiteSaque(double limiteSaque){
        this.limiteSaque = limiteSaque;
    }
    
    public double getLimiteSaque(){
        return this.limiteSaque;
    }
}
