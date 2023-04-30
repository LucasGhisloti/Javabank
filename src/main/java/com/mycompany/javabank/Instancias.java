/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javabank;

/**
 *
 * @author Lucas Ghisloti
 */
public class Instancias {
    
    Instancias(){
        Cliente cli = new Cliente(1, "Arnaldo Sacomani", "54.986.782-89", 9632, 78963214);
        Conta conta1 = new Conta(1, 1, "conta corrente", 100.00, 25.50);
        Transacao trans = new Transacao(0, "2023-02-01", "08:11:23", 1, 1, 200.30, "Saque");
        
        System.out.println("Cliente "+cli.getID()+": "+cli.getNome()+", documento: "
                +cli.getDocumento()+", senha para transacoes: "+cli.getSenhaTransac()+
                ", senha para login: "+cli.getSenhaLogin()+"\n"+
                "possuindo a conta "+conta1.getID()+": do tipo "+conta1.getTipo()+
                " com saldo de R$ "+conta1.getSaldo()+" e limite de saque de R$ "+conta1.getLimiteSaque());

        System.out.println(trans.getTipo() + ": " + trans.getDe() + " -> " +
                trans.getPara() + " " + trans.getData() + " " + trans.getHora()
                + " " + trans.getQuantia());
    }
}
