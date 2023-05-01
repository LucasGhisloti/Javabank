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
        Models model = new Models();

        model.listaBanco.add(new Banco(1, "JavaBank"));
        model.listaBanco.add(new Banco(2, "Banco Atlas"));
        model.listaBanco.add(new Banco(3, "Fenix Financeiro"));
        model.listaBanco.add(new Banco(4, "Prestige Investimentos"));

        Conta conta1 = new Conta(1, 1, "conta corrente", 30000.00, 2000.00);
        Transacao trans = new Transacao(0, "2023-02-01", "08:11:23", 1, 1, 200.30, "Saque");

        model.listaCliente.add(new Cliente(1, "Arnaldo Sacomani", "54.986.782-89", 9632, 78963214));
        model.listaCliente.add(new Cliente(2, "Jorge Ben", "98.741.951-27", 1234, 12345678));
        model.listaCliente.add(new Cliente(3, "John McCarthy", "57.563.951-19", 5637, 78932165));
        model.listaCliente.add(new Cliente(4, "Ada Lovelace", "85.325.859-32", 6295, 84951623));
        model.listaCliente.add(new Cliente(5, "Alan Turing", "12.988.556-78", 7562, 26159483));
        model.listaCliente.add(new Cliente(6, "Orlando de Andrade Figueiredo", "96.556.001-51", 4859, 32654898));

        for(int i = 0; i< model.listaCliente.size(); i++){
            System.out.println(model.listaCliente.get(i).getSenhaTransac());
        }

        /*System.out.println(trans.toString());
        
        System.out.println(cli.getID() + " " + cli.getNome());
        System.out.println(conta1.getSaldo() + " " + conta1.getLimiteSaque());
        if (cli.Sacar(conta1, 2000.01)){
            System.out.println(conta1.getSaldo());
        } else {
            System.out.println("Saque negado.");
        }*/
    }
}
