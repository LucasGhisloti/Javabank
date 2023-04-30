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
        
        System.out.println("Cliente "+cli.getID()+": "+cli.getNome()+", documento: "
                +cli.getDocumento()+", senha para transacoes: "+cli.getSenhaTransac()+
                ", senha para login: "+cli.getSenhaLogin());
    }
}
