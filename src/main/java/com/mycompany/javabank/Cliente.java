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
    private int ID;
    private String nome;
    private String documento;
    private int senhaLogin;
    private int senhaTransac;
    private int qtdDepositoCC;
    private int qtdDepositoPoupanca;

    Cliente(int ID, String nome, String documento, int senhaLogin, int senhaTransac){
        this.ID = ID;
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
    
    public int getQtdDepositoCC() {
        return qtdDepositoCC;
    }
        
    public void setQtdDepositoCC(int qtdDepositoCC) {
        this.qtdDepositoCC = qtdDepositoCC;
    }
    
    public int getQtdDepositoPoupanca() {
        return qtdDepositoPoupanca;
    }
        
    public void setQtdDepositoPoupanca(int qtdDepositoPoupanca) {
        this.qtdDepositoPoupanca = qtdDepositoPoupanca;
    }
    
    public boolean estourouLimiteDeposito(Models model, Conta conta){
        if(conta.getTipo().equals("Conta Corrente"))
            if(this.getQtdDepositoCC()+1 > model.listaBanco.get(conta.getBancoID()-1).getQtdLimiteDeposito()){
                return true;
            }else{
                this.setQtdDepositoCC(qtdDepositoCC+1);
                return false;
            }
        else{
            if(this.getQtdDepositoPoupanca()+1 > model.listaBanco.get(conta.getBancoID()-1).getQtdLimiteDeposito()){
                return true;
            }else{
                this.setQtdDepositoPoupanca(qtdDepositoPoupanca+1);
                return false;
            }
        }
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

    public void Depositar(Models model,Conta conta, double valor) throws DepositoLimiteException{
        if (this.estourouLimiteDeposito(model, conta)) {
            throw new DepositoLimiteException("Limite diário de depósitos atingido!");
        }
        
        double novoVal = conta.getSaldo() + valor;
        
        conta.setSaldo(novoVal);
        LocalDateTime data_atual =  LocalDateTime.now();
        // data formato "yyyy-MM-dd"
        String dataString = data_atual.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //hora formato "HH:mm:ss"
        String horaString = data_atual.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        model.listaTransacao.add(new Transacao(dataString,horaString,conta.getID(),conta.getID(), valor, "Deposito"));
    }

    public void Transferencia(Models model,Conta contaOrigem, Conta contaDestino, double valor)
        throws SaldoInsuficienteException {
        
        double novoVal = contaOrigem.getSaldo() - valor;

        if (novoVal < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }

        contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
        contaDestino.setSaldo(contaDestino.getSaldo() + valor);
        LocalDateTime data_atual =  LocalDateTime.now();
        
        // data formato "yyyy-MM-dd"
        String dataString = data_atual.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //hora formato "HH:mm:ss"
        String horaString = data_atual.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        model.listaTransacao.add(new Transacao(dataString,horaString,contaOrigem.getID(),contaDestino.getID(), valor, "Transferencia"));
    }
}
