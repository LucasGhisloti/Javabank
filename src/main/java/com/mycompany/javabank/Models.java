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
public class Models {
    ArrayList<Cliente> listaCliente = new ArrayList<>();
    ArrayList<Conta> listaConta = new ArrayList<>();
    ArrayList<Transacao> listaTransacao = new ArrayList<>();
    ArrayList<Banco> listaBanco = new ArrayList<>();
    
    public String getNomeFromClienteID(int id){
        return this.listaCliente.get(id-1).getNome();
    }
}
