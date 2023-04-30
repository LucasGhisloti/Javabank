/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javabank;

/**
 *
 * @author Lucas Ghisloti
 */
public class Cliente {
    public int ID;
    public String nome;
    private String documento;
    private int senhaLogin;
    private int senhaTransac;
    
    Cliente(int ID, String nome, String documento, int senhaLogin, int senhaTransac){
        this.setID(ID);
        this.setNome(nome);
        this.setSenhaLogin(senhaLogin);
        this.setSenhaTransac(senhaTransac);
        this.setDocumento(documento);
    }
    
    public void setID(int id){
        this.ID = id;
    }
    
    public int getID(){
        return this.ID;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setDocumento(String documento){
        this.documento = documento;
    }
    
    public String getDocumento(){
        return this.documento;
    }
    
    public void setSenhaLogin(int senhaLogin){
        this.senhaLogin = senhaLogin;
    }
    
    public int getSenhaLogin(){
        return this.senhaLogin;
    }
    
    public void setSenhaTransac(int senhaTransac){
        this.senhaTransac = senhaTransac;
    }
    
    public int getSenhaTransac(){
        return this.senhaTransac;
    }

    // TODO: GerarExtrato

    public boolean Sacar(Conta conta, double valor){
        // TODO: Como é o limite de saque?
        double novoVal = conta.getSaldo() - valor;
        if (novoVal < 0) return false;

        conta.setSaldo(conta.getSaldo() - valor);
        return true;
    }

    public boolean Depositar(Conta conta, double valor){
        double novoVal = conta.getSaldo() + valor;
        conta.setSaldo(novoVal);
        return true;
    }
}
