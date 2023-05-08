/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javabank;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class Models {
    ArrayList<Cliente> listaCliente = new ArrayList<>();
    ArrayList<Conta> listaConta = new ArrayList<>();
    ArrayList<Transacao> listaTransacao = new ArrayList<>();
    ArrayList<Banco> listaBanco = new ArrayList<>();

    public String getNomeClienteFromContaID(int id){
        int clienteID = this.listaConta.get(id-1).getClienteID();
        return this.listaCliente.get(clienteID-1).getNome();
    }

    public ArrayList<Transacao> getTransacoesFromConta(int contaID, LocalDate dtini, LocalDate dtfim){
        ArrayList<Transacao> retorno = new ArrayList<>();

        this.listaTransacao.forEach((t) -> {
            if ((t.getDe() == contaID || t.getPara() == contaID) 
                && ((t.getData().isAfter(dtini) || (t.getData().isEqual(dtini))) && 
                    (t.getData().isBefore(dtfim)) || (t.getData().isEqual(dtfim))))
                retorno.add(t);
        });

        return retorno;
    }

    public String getNomeBancoFromClienteBancoID(int id){
        int bancoID = this.listaConta.get(id-1).getBancoID();
        return this.listaBanco.get(bancoID-1).getNome();
    }

    public int getIDdeNovoCliente(){
        return this.listaCliente.size()+1;
    }
    
    public int contaDepositoHoje(int id){
        int countDep = 0;

        for(int i = this.listaTransacao.size()-1; i>0; i--){
            if(this.listaTransacao.get(i).getData().equals(LocalDate.now())){
                if(this.listaTransacao.get(i).getDe() == id &&
                   this.listaTransacao.get(i).getPara() == id){
                    countDep =+ 1;
                }
            }else return countDep;
        }

        return countDep;
    }

    public Banco getBancobyName(String nome){

        for(int i = 0; i< this.listaBanco.size(); i++){
            if(this.listaBanco.get(i).getNome().equals(nome)){
                return this.listaBanco.get(i);
            }
        }
        return null;
    }

    public List<Transacao> getTransacoes(int id, int banco){
        ArrayList<Transacao> transacoes = new ArrayList<>();
        for(Transacao t : this.listaTransacao){
            if(t.getDe() == id){
                transacoes.add(t);
            }
        }
        return transacoes;
    }

    public Banco getBanco(int id){

        for(int i = 0; i< this.listaBanco.size(); i++){
            if(this.listaBanco.get(i).getID() == id){
                return this.listaBanco.get(i);
            }
        }
        return null;
    }

    public Cliente getCliente(int id){

        for(int i = 0; i< this.listaCliente.size(); i++){
            if(this.listaCliente.get(i).getID() == id){
                return this.listaCliente.get(i);
            }
        }
        return null;
    }

    public Conta getConta(int id, int banco){

        for(int i = 0; i< this.listaConta.size(); i++){
            if(this.listaConta.get(i).getClienteID() == id
                    && this.listaConta.get(i).getBancoID() == banco){
                return this.listaConta.get(i);
            }
        }
        return null;
    }

    public Conta getConta(int id, int banco, String tipo){

        for(int i = 0; i< this.listaConta.size(); i++){
            if(this.listaConta.get(i).getClienteID() == id
                    && this.listaConta.get(i).getBancoID() == banco
                    && this.listaConta.get(i).getTipo().equals(tipo)){
                return this.listaConta.get(i);
            }
        }
        return null;
    }
}
