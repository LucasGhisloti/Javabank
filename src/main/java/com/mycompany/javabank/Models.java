/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javabank;

import java.util.ArrayList;
import java.time.LocalDate;

/**
 *
 * @author Lucas Ghisloti
 */
public class Models {
    ArrayList<Cliente> listaCliente = new ArrayList<>();
    ArrayList<Conta> listaConta = new ArrayList<>();
    ArrayList<Transacao> listaTransacao = new ArrayList<>();
    ArrayList<Banco> listaBanco = new ArrayList<>();
    
    public String getNomeClienteFromContaID(int id){
        int clienteID = this.listaConta.get(id-1).getClienteID();
        return this.listaCliente.get(clienteID-1).getNome();
    }

    public ArrayList<Transacao> getTransacoesFromCliente(int clienteID, LocalDate dtini, LocalDate dtfim){
        ArrayList<Transacao> retorno = new ArrayList<>();

        // TODO: ordenar por data
        this.listaTransacao.forEach((t) -> {
            if ((t.getDe() == clienteID || t.getPara() == clienteID) 
                && ((t.getData().isAfter(dtini) || (t.getData().isEqual(dtini))) && 
                    (t.getData().isBefore(dtfim)) || (t.getData().isEqual(dtfim))))
                retorno.add(t);
        });

        return retorno;
    }
}
