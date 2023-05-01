/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javabank;

import java.util.ArrayList;

/**
 *
 * @author Lucas Ghisloti
 */
public class Cliente {
    public String nome;
    private String documento;
    private int senhaLogin;
    private int senhaTransac;

    Cliente(String nome, String documento, int senhaLogin, int senhaTransac){
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

    public void GerarExtrato(Models model){
        ArrayList<Transacao> extrato;
        int meuId = model.listaCliente.indexOf(this)+1;
        extrato = model.getTransacoesFromCliente(meuId);

        extrato.forEach((t) -> {
            System.out.println(t.toString(model) + "\n");
        });

        //TODO: Sem transacoes, fala que nao tem
    }

    public boolean Sacar(Conta conta, double valor){
        double novoVal = conta.getSaldo() - valor;
        if (valor > conta.getLimiteSaque()) return false;
        if (novoVal < 0) return false;

        conta.setSaldo(conta.getSaldo() - valor);
        return true;
    }

    public void Depositar(Conta conta, double valor){
        double novoVal = conta.getSaldo() + valor;
        conta.setSaldo(novoVal);
    }
}
