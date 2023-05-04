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

class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String message) {
        super(message);
    }
}

class limiteSaqueException extends Exception {
    public limiteSaqueException(String message) {
        super(message);
    }
}

public class Javabank {
    
    static void imprimeTitulo(String titulo){
        System.out.println("\n\033[H\033[2J"
                + "<< " + titulo + " >>");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Instancias instanc = new Instancias();
        String[] menuLoginItems = { "Login", "Nova Conta", "Sair" };
        String[] menuItems = { "Extrato", "Saque", "Deposito", "Transferencia" };
        String optionX = "";
        String whichmenu = "Login";
        Ui login = new Ui(menuLoginItems);
        Ui menu = new Ui(menuItems);
        Cliente clienteAtual = new Cliente("", "", 0, 0);
        int IDBanco = instanc.getBancobyName("JavaBank").getID();
        Conta contaAtual = new Conta(0, 1, "", 0, 0);
        final String footer = "[" + menu.getFooter() + "]: ";

        while (optionX != "Sair") {
            login.setMenuIndexatual(0);
            if (whichmenu == "Login") {
                login.setMenuIndexatual(0);

                optionX = login.load("\nJavaBank: seja bem-vindo!\n", footer);
            } else if (whichmenu == "Menu") {
                menu.setMenuIndexatual(0);

                optionX = menu.load(
                        "\n" + clienteAtual.getNome() + " [ID: " + clienteAtual.getID() + "]"
                            + "\nSaldo atual: " + contaAtual.getSaldo() + "\n", 
                        footer);
            }
            // Menu Inicial -----------------------------------------------------

            // Login
            if (optionX == "Login" && whichmenu == "Login") {
                // limpar tela
                System.out.print("\033[H\033[2J");
                imprimeTitulo("Login");
                System.out.print("Numero do cliente: ");
                String IDCliente = scanner.nextLine();
                System.out.print("Senha: ");
                String senha = scanner.nextLine();

                // System.out.println("ID Banco: ");
                // IDBanco = scanner.nextLine();

                // pesquisar conta nas instancias

                clienteAtual = instanc.getCliente(Integer.parseInt(IDCliente));

                contaAtual = instanc.getConta(clienteAtual.getID(), IDBanco, "Conta Corrente");

                // verifica se tem poupanca
                Conta contaPoupanca = instanc.getConta(clienteAtual.getID(), IDBanco, "Conta Poupanca");

                if (contaPoupanca != null) {
                    menu.addMenuItem("Saque Poupanca");
                    menu.addMenuItem("Deposito Poupanca");
                    menu.addMenuItem("Saldo Poupanca");

                }
                if ("Sair" != new ArrayList<>(Arrays.asList(menu.getMenuItems())).get(menu.getMenuItems().length - 1)) {
                    menu.addMenuItem("Sair");
                }

                if (clienteAtual.getSenhaLogin() != Integer.parseInt(senha)) {
                    System.out.println("Senha incorreta");
                    scanner.nextLine();
                    continue;
                } else {
                    whichmenu = "Menu";
                }

            }

            // Nova Conta
            if (optionX == "Nova Conta" && whichmenu == "Login") {

                Cliente cliente = new Cliente("", "", 0, 0);
                Conta conta = new Conta(0, 1, "", 0, 0);
                // limpar tela
                imprimeTitulo("Nova conta");
                System.out.print("Seu nome: ");
                cliente.setNome(scanner.nextLine());
                System.out.print("Seu documento (CPF/CNPJ): ");
                cliente.setDocumento(scanner.nextLine());
                
                //limpar tela
                
                boolean condition = false;
                String senha = "";
                do {
                    System.out.print("Senha de login: ");
                    senha = scanner.nextLine();
                    System.out.print("Confirme sua senha: ");
                    String senha2 = scanner.nextLine();
                    if(senha.equals(senha2)){
                        cliente.setSenhaLogin(Integer.parseInt(senha));
                        condition = true;
                    }else{
                        System.out.print("Senhas não conferem!\nPressione enter para continuar");
                        scanner.nextLine();
                    }
                    
                } while (!condition);

                cliente.setSenhaLogin(Integer.parseInt(senha));

                condition = false;
                do {
                    System.out.print("Senha de transação: ");
                    senha = scanner.nextLine();
                    System.out.print("Confirme sua senha: ");
                    String senha2 = scanner.nextLine();
                    if(senha.equals(senha2)){
                        cliente.setSenhaLogin(Integer.parseInt(senha));
                        condition = true;
                    }else{
                        System.out.print("Senhas não conferem!\nPressione enter para continuar.");
                        scanner.nextLine();
                    }
                    
                } while (!condition);

                cliente.setSenhaTransac(Integer.parseInt(senha));
                //configura conta
                conta.setTipo("Conta Corrente");
                conta.setSaldo(0);
                conta.setBancoID(IDBanco);
                conta.setClienteID(cliente.getID());
                System.out.println("Digite o valor do limite de saque:");
                conta.setLimiteSaque(Double.parseDouble(scanner.nextLine()));



                //pergunta se quer conta poupanca
                System.out.print("Deseja criar uma conta poupanca? [s/n]: ");
                String option = scanner.nextLine();
                


                Conta contaPoupanca = new Conta(0, 1, "", 0, 0);
                if(option.equals("s")){
                   
                    contaPoupanca.setTipo("Conta Poupanca");
                    contaPoupanca.setSaldo(0);
                    contaPoupanca.setBancoID(IDBanco);
                    contaPoupanca.setClienteID(cliente.getID());
                    System.out.println("Digite o valor do limite de saque:");
                    conta.setLimiteSaque(Double.parseDouble(scanner.nextLine()));
                }else{
                    contaPoupanca = null;
                }

                //adiciona cliente e conta nas instancias
                instanc.getModel().listaCliente.add(cliente);
                instanc.getModel().listaConta.add(conta);
                if(contaPoupanca != null){
                    instanc.getModel().listaConta.add(contaPoupanca);
                }

                System.out.print("\nConta criada com sucesso!\n"
                        + "GRAVE SEUS DADOS E NÃO PERCA!\n"
                        + "- Seu ID é ["+cliente.getID()+"].\n"
                        + "Pressione enter para continuar...");
                scanner.nextLine();
            }

            // Menu --------------------------------------------------------------
            // Extrato
            if (optionX == "Extrato" && whichmenu == "Menu") {

                // transacoes
                imprimeTitulo("Extrato");
                LocalDate data = LocalDate.now();

                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                data = LocalDate.parse(data.toString(), dateFormat);
                LocalDate data_minus60 = LocalDate.parse(data.minusDays(300).toString(), dateFormat);

                contaAtual.GerarExtrato(instanc.getModel(), data_minus60.toString(), data.toString());

                System.out.print("Pressione enter para voltar!");
                scanner.nextLine();

            }

            // Saque
            if (optionX == "Saque" && whichmenu == "Menu") {
                imprimeTitulo("Saque");
                System.out.print("Valor do saque: ");
                String valor = scanner.nextLine();
                
                System.out.print("Senha de transacao: ");

                boolean condition = false;
                do {
                    int senha = Integer.parseInt(scanner.nextLine());
                    if(senha == clienteAtual.getSenhaTransac()){
                        condition = true;
                    }else{
                        System.out.print("Senha incorreta!\nTente novamente: ");
                    }
                } while (!condition);

                try {
                    clienteAtual.Sacar(instanc.getModel(), contaAtual, Double.parseDouble(valor));
                    System.out.println("\nSaque feito com sucesso!");
                } catch (SaldoInsuficienteException e) {
                    System.out.println(e.getMessage());
                } catch (limiteSaqueException e) {
                    System.out.println(e.getMessage());
                }

                System.out.print("Pressione enter para voltar!");
                scanner.nextLine();
            }

            // Deposito
            if (optionX == "Deposito" && whichmenu == "Menu") {
                imprimeTitulo("Deposito");
                System.out.print("Digite o valor do deposito: ");
                String valor = scanner.nextLine();

                clienteAtual.Depositar(instanc.getModel(), contaAtual, Double.parseDouble(valor));
                System.out.println("Deposito feito com sucesso!");

            }

            // Transferencia
            if (optionX == "Transferencia" && whichmenu == "Menu") {
                imprimeTitulo("Transferencia");
                System.out.print("Valor a transferir: ");
                String valor = scanner.nextLine();
                System.out.print("ID do cliente que recebera a transferencia: ");
                String IDCliente = scanner.nextLine();

                String[] tipodecontas = { "Conta Corrente" };

                Ui selecaoConta = new Ui(tipodecontas);
                // verifica se destino tem poupanca
                Conta contaPoupanca = instanc.getConta(Integer.parseInt(IDCliente), IDBanco, "Conta Poupanca");

                if (contaPoupanca != null) {
                    selecaoConta.addMenuItem("Conta Poupanca");
                }
                selecaoConta.addMenuItem("Voltar");

                selecaoConta.setMenuIndexatual(0);
                String optionConta = selecaoConta.load("Selecione o tipo de conta que recebera a transferencia:\n",
                        footer);

                if (optionConta.equals("Voltar")) {
                    continue;
                }

                Cliente clienteDestino = instanc.getCliente(Integer.parseInt(IDCliente));
                Conta contaDestino = instanc.getConta(clienteDestino.getID(), IDBanco, optionConta);
                
                System.out.print("Senha de transacao: ");
                boolean condition = false;
                do {
                    int senha = Integer.parseInt(scanner.nextLine());
                    if(senha == clienteAtual.getSenhaTransac()){
                        condition = true;
                    }else{
                        System.out.print("Senha incorreta!\nTente novamente: ");
                    }
                } while (!condition);

                clienteAtual.Transferencia(instanc.getModel(), contaAtual, contaDestino, Double.parseDouble(valor));
                System.out.println("Transferencia feita com sucesso!");
            }

            // Saque Poupanca
            if (optionX == "Saque Poupanca" && whichmenu == "Menu") {
                // pegar poupanca
                Conta contaPoupanca = instanc.getConta(clienteAtual.getID(), IDBanco, "Conta Poupanca");

                imprimeTitulo("Saque Poupanca\n");
                System.out.print("Valor do saque: ");
                String valor = scanner.nextLine();
                
                System.out.print("Senha de transacao: ");

                boolean condition = false;
                do {
                    int senha = Integer.parseInt(scanner.nextLine());
                    if(senha == clienteAtual.getSenhaTransac()){
                        condition = true;
                    }else{
                        System.out.print("Senha incorreta!\nTente novamente: ");
                    }
                } while (!condition);

                try {
                    clienteAtual.Sacar(instanc.getModel(), contaPoupanca, Double.parseDouble(valor));
                    System.out.println("Saque feito com sucesso!");
                } catch (SaldoInsuficienteException e) {
                    System.out.println(e.getMessage());
                } catch (limiteSaqueException e) {
                    System.out.println(e.getMessage());
                }

                System.out.print("Pressione enter para voltar!");
                scanner.nextLine();

            }

            // Deposito Poupanca
            if (optionX == "Deposito Poupanca" && whichmenu == "Menu") {
                // pegar poupanca
                Conta contaPoupanca = instanc.getConta(clienteAtual.getID(), IDBanco, "Conta Poupanca");
                imprimeTitulo("Deposito em Poupanca");
                System.out.println("Valor do deposito: ");
                String valor = scanner.nextLine();

                clienteAtual.Depositar(instanc.getModel(), contaPoupanca, Double.parseDouble(valor));

                System.out.println("Deposito feito com sucesso!");
            }

            // Saldo Poupanca
            if (optionX == "Saldo Poupanca" && whichmenu == "Menu") {
                // pegar poupanca
                Conta contaPoupanca = instanc.getConta(clienteAtual.getID(), IDBanco, "Conta Poupanca");
                imprimeTitulo("Saldo Poupanca");
                System.out.println("Saldo: " + contaPoupanca.getSaldo());
                System.out.print("Pressione enter para voltar!");
                scanner.nextLine();

            }
        }
        scanner.close();
    }

}
