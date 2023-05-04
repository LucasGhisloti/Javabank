/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.javabank;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.DateFormatter;

/**
 *
 * @author Lucas Ghisloti
 * @author Leonardo Bezerra
 */

 class SaldoInsuficienteException extends Exception{
    public SaldoInsuficienteException(String message){
        super(message);
    }
}

class limiteSaqueException extends Exception{
    public limiteSaqueException(String message){
        super(message);
    }
}

public class Javabank {

    public static void main(String[] args) {

        
        Scanner scanner = new Scanner(System.in);
        Instancias instanc = new Instancias();
        String [] menuLoginItems= {"Login","Nova Conta", "Sair"};
        String [] menuItems= {"Extrato", "Saque", "Deposito", "Transferencia"};
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
                
                optionX= login.load("SEJA BEM VINDO ! #################\n","[Down:s "+"Up:w "+"Select:x]");
            }
            else if(whichmenu=="Menu"){
                menu.setMenuIndexatual(0);
                
                optionX= menu.load(clienteAtual.getNome()+"[ID: "+clienteAtual.getID()+"]"+"------Saldo atual: "+contaAtual.getSaldo()+"\n\n","\n[Down:s "+"Up:w "+"Select:x]");
            }
            //Menu Inicial -----------------------------------------------------
            
            
            //Login 
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

                //verifica se tem poupanca
                Conta contaPoupanca=instanc.getConta(clienteAtual.getID(),IDBanco,"Conta Poupanca");

                if(contaPoupanca!=null){
                    menu.addMenuItem("Saque Poupanca");
                    menu.addMenuItem("Deposito Poupanca");
                    menu.addMenuItem("Saldo Poupanca");
                
                }
                if("Sair" != new ArrayList<>(Arrays.asList(menu.getMenuItems())).get(menu.getMenuItems().length-1)){
                    menu.addMenuItem("Sair");
                }
                
                
                if(clienteAtual.getSenhaLogin()!=Integer.parseInt(senha)){
                    System.out.println("Senha incorreta");
                    scanner.nextLine();
                    continue;
                }else{
                    whichmenu="Menu";
                }
                
                
            }
            
            //Nova Conta
            if(optionX=="Nova Conta" && whichmenu=="Login"){
                
                Cliente cliente = new Cliente("","",0,0);
                Conta conta = new Conta(0,1,"",0,0);
                //limpar tela
                System.out.print("\033[H\033[2J");
                System.out.println("CADASTRO DE CLIENTE:\n");


            }

            
            //Menu --------------------------------------------------------------
            //Extrato
            if(optionX=="Extrato" && whichmenu=="Menu"){

                //transacoes
                System.out.print("\033[H\033[2J");
                System.out.println("Extrato\n");
                LocalDate data = LocalDate.now();

                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                data = LocalDate.parse(data.toString(), dateFormat);
                LocalDate data_minus60 = LocalDate.parse(data.minusDays(300).toString(), dateFormat);


                
                contaAtual.GerarExtrato(instanc.model,data_minus60.toString(),data.toString());

                System.out.println("Pressione enter para voltar!");
                scanner.nextLine();
                
            }
            
            //Saque
            if(optionX=="Saque" && whichmenu=="Menu"){
                System.out.print("\033[H\033[2J");
                System.out.println("Saque\n");
                System.out.println("Digite o valor do saque:");
                String valor = scanner.nextLine();
                
                try {
                    clienteAtual.Sacar(instanc.model,contaAtual,Double.parseDouble(valor));
                } catch (SaldoInsuficienteException e) {
                    System.out.println(e.getMessage());
                }catch (limiteSaqueException e) {
                    System.out.println(e.getMessage());
                }
                


                System.out.println("Pressione enter para voltar!");
                scanner.nextLine();
            }
            
            //Deposito
            if(optionX=="Deposito" && whichmenu=="Menu"){
                System.out.print("\033[H\033[2J");
                System.out.println("Deposito\n");
                System.out.println("Digite o valor do deposito:");
                String valor = scanner.nextLine();
                
                clienteAtual.Depositar(instanc.model,contaAtual,Double.parseDouble(valor));

            }

            //Transferencia
            if(optionX=="Transferencia" && whichmenu=="Menu"){
                System.out.print("\033[H\033[2J");
                System.out.println("Transferencia\n");
                System.out.println("Digite o valor da transferencia:");
                String valor = scanner.nextLine();
                System.out.println("Digite o ID do cliente que recebera a transferencia:");
                String IDCliente = scanner.nextLine();
                

                String [] tipodecontas = {"Conta Corrente"};

                
                
                
                
                Ui selecaoConta = new Ui(tipodecontas);
                //verifica se destino tem poupanca
                Conta contaPoupanca=instanc.getConta(Integer.parseInt(IDCliente),IDBanco,"Conta Poupanca");

                if(contaPoupanca!=null){
                    selecaoConta.addMenuItem("Conta Poupanca");
                }
                selecaoConta.addMenuItem("Voltar");

                
                selecaoConta.setMenuIndexatual(0);
                String optionConta = selecaoConta.load("Selecione o tipo de conta que recebera a transferencia: \n\n","[Down:s "+"Up:w "+"Select:x]");
                
                if(optionConta=="Voltar"){
                    continue;
                }

                Cliente clienteDestino = instanc.getCliente(Integer.parseInt(IDCliente));
                Conta contaDestino = instanc.getConta(clienteDestino.getID(),IDBanco,optionConta);
                
                clienteAtual.Transferencia(instanc.model, contaAtual, contaDestino, Double.parseDouble(valor));

            }

            //Saque Poupanca
            if(optionX=="Saque Poupanca" && whichmenu=="Menu"){
                //pegar poupanca
                Conta contaPoupanca=instanc.getConta(clienteAtual.getID(),IDBanco,"Conta Poupanca");
                
                System.out.print("\033[H\033[2J");
                System.out.println("Saque Poupanca\n");
                System.out.println("Digite o valor do saque:");
                String valor = scanner.nextLine();
                
                try {
                    clienteAtual.Sacar(instanc.model,contaPoupanca,Double.parseDouble(valor));
                } catch (SaldoInsuficienteException e) {
                    System.out.println(e.getMessage());
                }catch (limiteSaqueException e) {
                    System.out.println(e.getMessage());
                }

                System.out.println("Pressione enter para voltar!");
                scanner.nextLine();

            }

            //Deposito Poupanca
            if(optionX=="Deposito Poupanca" && whichmenu=="Menu"){
                //pegar poupanca
                Conta contaPoupanca=instanc.getConta(clienteAtual.getID(),IDBanco,"Conta Poupanca");
                System.out.print("\033[H\033[2J");
                System.out.println("Deposito Poupanca\n");
                System.out.println("Digite o valor do deposito:");
                String valor = scanner.nextLine();

                clienteAtual.Depositar(instanc.model,contaPoupanca,Double.parseDouble(valor));

                System.out.println("Pressione enter para voltar!");
            }

            //Saldo Poupanca
            if(optionX=="Saldo Poupanca" && whichmenu=="Menu"){
                //pegar poupanca
                Conta contaPoupanca=instanc.getConta(clienteAtual.getID(),IDBanco,"Conta Poupanca");
                System.out.print("\033[H\033[2J");
                System.out.println("Saldo Poupanca\n");
                System.out.println("Saldo: "+contaPoupanca.getSaldo());
                System.out.println("Pressione enter para voltar!");
                scanner.nextLine();
                

            }











        }
}}
