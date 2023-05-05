/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javabank;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Cliente {
    private static int idcount = 1;
    private int ID;
    private String nome;
    private String documento;
    private int senhaLogin;
    private int senhaTransac;
    


    Cliente(String nome, String documento, int senhaLogin, int senhaTransac){
        this.ID = idcount++;
        this.setNome(nome);
        this.setSenhaLogin(senhaLogin);
        this.setSenhaTransac(senhaTransac);
        this.setDocumento(documento);
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }
    public int getID(){
        return this.ID;
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

    public boolean Sacar(Models model,Conta conta, double valor) throws SaldoInsuficienteException, limiteSaqueException{
        double novoVal = conta.getSaldo() - valor;
        if (valor > conta.getLimiteSaque()) {throw new limiteSaqueException("Limite de saque excedido");}
        if (novoVal < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
            
        }
        
        LocalDateTime data_atual =  LocalDateTime.now();
        // data formato "yyyy-MM-dd"
        String dataString = data_atual.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //hora formato "HH:mm:ss"
        String horaString = data_atual.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        model.listaTransacao.add(new Transacao(dataString,horaString,conta.getID(),conta.getID(), valor, "Saque"));

        conta.setSaldo(conta.getSaldo() - valor);
        return true;
    }

    public void Depositar(Models model,Conta conta, double valor){
        double novoVal = conta.getSaldo() + valor;
        conta.setSaldo(novoVal);
        LocalDateTime data_atual =  LocalDateTime.now();
        // data formato "yyyy-MM-dd"
        String dataString = data_atual.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //hora formato "HH:mm:ss"
        String horaString = data_atual.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        model.listaTransacao.add(new Transacao(dataString,horaString,conta.getID(),conta.getID(), valor, "Deposito"));
    }

    public boolean Transferencia(Models model,Conta contaOrigem, Conta contaDestino, double valor){
        
        double novoVal = contaOrigem.getSaldo() - valor;
        if (novoVal < 0) return false;
        contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
        contaDestino.setSaldo(contaDestino.getSaldo() + valor);
        LocalDateTime data_atual =  LocalDateTime.now();
        
        // data formato "yyyy-MM-dd"
        String dataString = data_atual.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //hora formato "HH:mm:ss"
        String horaString = data_atual.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        model.listaTransacao.add(new Transacao(dataString,horaString,contaOrigem.getID(),contaDestino.getID(), valor, "Transferencia"));

        return true;
    }
}
