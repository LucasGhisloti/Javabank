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
        Banco b1 = new Banco(1, "Banco Atlas");
        Banco b2 = new Banco(2, "Fênix Financeiro");
        Banco b3 = new Banco(3, "Prestige Investimentos");

        Cliente cli = new Cliente(1, "Arnaldo Sacomani", "54.986.782-89", 9632, 78963214);
        Conta conta1 = new Conta(1, 1, "conta corrente", 100.00, 25.50);
        Transacao trans = new Transacao(0, "2023-02-01", "08:11:23", 1, 1, 200.30, "Saque");

        System.out.println(cli.getID() + " " + cli.getNome());
        System.out.println(conta1.getSaldo());
        if (cli.Sacar(conta1, 100.03)){
            System.out.println(conta1.getSaldo());
        } else {
            System.out.println("Saque negado (acima do saldo)");
        }

    }
}
