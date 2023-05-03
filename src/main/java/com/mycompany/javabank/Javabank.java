/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.javabank;

import java.util.Scanner;

/**
 *
 * @author Lucas Ghisloti
 */
public class Javabank {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Instancias instanc = new Instancias();
        String [] menuLoginItems= {"Login","Nova Conta", "Sair"};
        String [] menuItems= {"Extrato", "Saque", "Deposito", "Transferencia", "Sair"};
        String optionX = "";
        String whichmenu="Login";
        Ui login = new Ui(menuLoginItems);
        Ui menu = new Ui(menuItems);
        Cliente clienteAtual= new Cliente("","",0,0);
        String IDBanco="";
        while(optionX!="Sair"){
            login.setMenuIndexatual(0);
            if(whichmenu=="Login"){
                login.setMenuIndexatual(0);
                login.setMenuItems(menuLoginItems);
                optionX= login.load();
            }
            else if(whichmenu=="Menu"){
                menu.setMenuIndexatual(0);
                menu.setMenuItems(menuItems);
                optionX= menu.load(clienteAtual.getNome()+"[ID: "+clienteAtual.getID()+"]\n");
            }
            

            //LOGIN
            if(optionX=="Login" && whichmenu=="Login"){
                //limpar tela
                System.out.print("\033[H\033[2J");
                System.out.println("Login\n");
                System.out.println("Digite o numero do cliente:");
                String IDCliente = scanner.nextLine();
                System.out.println("Digite a senha:");
                String senha = scanner.nextLine();
                System.out.println("ID Banco: ");
                IDBanco =  scanner.nextLine();
                

                //pesquisar conta nas instancias
                
                clienteAtual = instanc.getCliente(Integer.parseInt(IDCliente));
                
                if(clienteAtual.getSenhaLogin()!=Integer.parseInt(senha)){
                    System.out.println("Senha incorreta");
                    scanner.nextLine();
                    continue;
                }else{
                    whichmenu="Menu";
                }
                

            }

            //Menu 
            if(optionX=="Extrato" && whichmenu=="Menu"){
                Conta conta=instanc.getConta(clienteAtual.getID(),Integer.parseInt(IDBanco));
            }

        }
}}
