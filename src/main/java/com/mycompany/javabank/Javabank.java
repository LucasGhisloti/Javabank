/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.javabank;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.DateFormatter;

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
        int IDBanco= instanc.getBancobyName("JavaBank").getID();
        Conta contaAtual= new Conta(0,1,"",0,0);
       
        while(optionX!="Sair"){
            login.setMenuIndexatual(0);
            if(whichmenu=="Login"){
                login.setMenuIndexatual(0);
                login.setMenuItems(menuLoginItems);
                optionX= login.load("SEJA BEM VINDO ! #################\n","[Down:s "+"Up:w "+"Select:x]");
            }
            else if(whichmenu=="Menu"){
                menu.setMenuIndexatual(0);
                menu.setMenuItems(menuItems);
                optionX= menu.load(clienteAtual.getNome()+"[ID: "+clienteAtual.getID()+"]"+"------Saldo atual: "+contaAtual.getSaldo()+"\n\n","\n[Down:s "+"Up:w "+"Select:x]");
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
                //System.out.println("ID Banco: ");
                //IDBanco =  scanner.nextLine();
                

                //pesquisar conta nas instancias
                
                clienteAtual = instanc.getCliente(Integer.parseInt(IDCliente));
                
                contaAtual=instanc.getConta(clienteAtual.getID(),IDBanco,"Conta Corrente");
                
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

                //transacoes
                System.out.print("\033[H\033[2J");
                System.out.println("Extrato\n");
                LocalDate data = LocalDate.now();

                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                data = LocalDate.parse(data.toString(), dateFormat);
                LocalDate data_minus60 = LocalDate.parse(data.minusDays(60).toString(), dateFormat);


                
                contaAtual.GerarExtrato(instanc.model,data.toString(),data_minus60.toString());


                scanner.nextLine();
                
            }


        }
}}
