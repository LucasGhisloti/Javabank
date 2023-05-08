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
import java.util.logging.Level;
import java.util.logging.Logger;

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

class DepositoLimiteException extends Exception {
    public DepositoLimiteException(String message) {
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
        Util util = new Util();
        String[] menuLoginItems = { "Login", "Nova Conta", "Sair" };
        String[] menuItems = { "Extrato", "Saque", "Deposito", "Transferencia" };
        String optionX = "";
        String whichmenu = "Login";
        Ui login = new Ui(menuLoginItems);
        Ui menu = new Ui(menuItems);
        Cliente clienteAtual = new Cliente(0, "", "", 0, 0);
        Banco javabankObj = instanc.getModel().getBancobyName("JavaBank");
        final int IDBanco = javabankObj.getID();
        Conta contaAtual = new Conta(0, 1, "", 0, 0);
        final String footer = "[" + menu.getFooter() + "]: ";

        while (!"Sair".equals(optionX)) {
            login.setMenuIndexatual(0);
            if ("Login".equals(whichmenu)) {
                login.setMenuIndexatual(0);

                optionX = login.load("\nJavaBank: seja bem-vindo!\n", footer);
            } else if ("Menu".equals(whichmenu)) {
                menu.setMenuIndexatual(0);
                String header = "\n" + clienteAtual.getNome() + " [ID: " + clienteAtual.getID() + "]"
                            + "\nSaldo atual: " + contaAtual.getSaldo() + "\n";
                // verifica se tem poupanca
                Conta contaPoupanca = instanc.getModel()
                        .getConta(clienteAtual.getID(), IDBanco, "Conta Poupanca");
                if (contaPoupanca != null) {
                   header += "Saldo atual da Poupanca: "+contaPoupanca.getSaldo()+"\n";
                }
                optionX = menu.load(header, footer);
            }
            // Menu Inicial -----------------------------------------------------

            // Login
            if ("Login".equals(optionX) && "Login".equals(whichmenu)) {
                // limpar tela
                imprimeTitulo("Login");

                clienteAtual = instanc.getModel().getCliente(
                               util.entradaCliente("Numero do cliente", instanc.getModel()));
                
                util.entradaSenha("Senha", clienteAtual, "Login");

                // System.out.println("ID Banco: ");
                // IDBanco = scanner.nextLine();

                // pesquisar conta nas instancias
                contaAtual = instanc.getModel()
                        .getConta(clienteAtual.getID(), IDBanco, "Conta Corrente");
                
                clienteAtual.setQtdDepositoCC(instanc.getModel().contaDepositoHoje(contaAtual.getID()));

                // verifica se tem poupanca
                Conta contaPoupanca = instanc.getModel()
                        .getConta(clienteAtual.getID(), IDBanco, "Conta Poupanca");

                if (contaPoupanca != null) {
                    clienteAtual.setQtdDepositoPoupanca(instanc.getModel().contaDepositoHoje(contaPoupanca.getID()));
                    menu.addMenuItem("Saque Poupanca");
                    menu.addMenuItem("Deposito Poupanca");
                    menu.addMenuItem("Saldo Poupanca");

                }
                if (!"Sair".equals(
                        new ArrayList<>(Arrays.asList(
                                menu.getMenuItems())).get(menu.getMenuItems().length - 1))) {
                    menu.addMenuItem("Sair");
                }


                whichmenu = "Menu";

            }

            // Nova Conta
            if ("Nova Conta".equals(optionX) && "Login".equals(whichmenu)) {

                Cliente cliente = new Cliente(
                        instanc.getModel().getIDdeNovoCliente(), "", "", 0, 0);
                Conta conta = new Conta(0, 1, "", 0, 0);
                // limpar tela
                imprimeTitulo("Nova conta");

                cliente.setNome(util.entradaNomeEDoc("Seu nome"));
                cliente.setDocumento(util.entradaNomeEDoc("Seu documento (CPF/CNPJ)"));
                
                //limpar tela
                
                cliente.setSenhaLogin(util.entradaSenhaNovaConta("Login"));

                cliente.setSenhaTransac(util.entradaSenhaNovaConta("Transacao"));
                //configura conta
                conta.setTipo("Conta Corrente");
                conta.setSaldo(0);
                conta.setBancoID(IDBanco);
                conta.setClienteID(cliente.getID());
                conta.setLimiteSaque(javabankObj.getLimiteInicialDeSaque());



                //pergunta se quer conta poupanca
                System.out.print("Deseja criar uma conta poupanca? [s/n]: ");
                String option = scanner.nextLine();
                


                Conta contaPoupanca = new Conta(0, 1, "", 0, 0);
                if(option.equals("s")){
                   
                    contaPoupanca.setTipo("Conta Poupanca");
                    contaPoupanca.setSaldo(0);
                    contaPoupanca.setBancoID(IDBanco);
                    contaPoupanca.setClienteID(cliente.getID());
                    contaPoupanca.setLimiteSaque(javabankObj.getLimiteInicialDeSaque());
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
            if ("Extrato".equals(optionX) && "Menu".equals(whichmenu)) {

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

            if ("Saque".equals(optionX) && "Menu".equals(whichmenu)) {
                imprimeTitulo("Saque");
                System.out.println("Voce pode sacar ate "+ contaAtual.getLimiteSaque());
                
                double valor = util.entradaValor("Valor do saque");
                util.entradaSenha("Senha de transacao", clienteAtual, "Transacao");

                try {
                    clienteAtual.Sacar(instanc.getModel(), contaAtual, valor);
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

            if ("Deposito".equals(optionX) && "Menu".equals(whichmenu)) {
                imprimeTitulo("Deposito");
                System.out.println("Depositos restantes permitidos: "
                        +(javabankObj.getQtdLimiteDeposito() - clienteAtual.getQtdDepositoCC()));
                
                double valor = util.entradaValor("Valor do deposito");
                
                try {
                    clienteAtual.Depositar(instanc.getModel(), contaAtual, valor);
                    System.out.println("Deposito feito com sucesso!");
                } catch (DepositoLimiteException e) {
                    System.out.println(e.getMessage());
                }

            }

            // Transferencia

            if ("Transferencia".equals(optionX) && "Menu".equals(whichmenu)) {
                imprimeTitulo("Transferencia");
                double valor = util.entradaValor("Valor a transferir");
                int IDCliente = util.entradaCliente("ID do cliente que recebera a transferencia", instanc.getModel());
                
                Cliente clienteDestino = instanc.getModel().getCliente(IDCliente);

                System.out.println("Voce esta transferindo para: " + clienteDestino.getNome());

                String[] tipodecontas = { "Conta Corrente" };

                Ui selecaoConta = new Ui(tipodecontas);
                // verifica se destino tem poupanca
                Conta contaPoupanca = instanc.getModel()
                        .getConta(IDCliente, IDBanco, "Conta Poupanca");

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

                Conta contaDestino = instanc.getModel()
                        .getConta(clienteDestino.getID(), IDBanco, optionConta);
                
                util.entradaSenha("Senha de transacao", clienteAtual, "Transacao");

                try {
                    clienteAtual.Transferencia(instanc.getModel(), contaAtual, contaDestino, valor);
                    System.out.println("Transferencia feita com sucesso!");
                } catch (SaldoInsuficienteException e) {
                   System.out.println(e.getMessage());
                }
            }

            // Saque Poupanca
            if ("Saque Poupanca".equals(optionX) && "Menu".equals(whichmenu)) {
                // pegar poupanca
                Conta contaPoupanca = instanc.getModel()
                        .getConta(clienteAtual.getID(), IDBanco, "Conta Poupanca");

                imprimeTitulo("Saque Poupanca");
                System.out.println("Voce pode sacar ate "+ contaPoupanca.getLimiteSaque());

                double valor = util.entradaValor("Valor do saque");

                util.entradaSenha("Senha de transacao", clienteAtual, "Transacao");

                try {
                    clienteAtual.Sacar(instanc.getModel(), contaPoupanca, valor);
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
            if ("Deposito Poupanca".equals(optionX) && "Menu".equals(whichmenu)) {
                // pegar poupanca
                Conta contaPoupanca = instanc.getModel()
                        .getConta(clienteAtual.getID(), IDBanco, "Conta Poupanca");
                imprimeTitulo("Deposito em Poupanca");
                System.out.println("Depositos restantes permitidos: "
                                   +(javabankObj.getQtdLimiteDeposito() - clienteAtual.getQtdDepositoPoupanca()));

                double valor = util.entradaValor("Valor do deposito");
  
                try {
                    clienteAtual.Depositar(instanc.getModel(), contaPoupanca, valor);
                    System.out.println("Deposito feito com sucesso!");
                } catch (DepositoLimiteException e) {
                    System.out.println(e.getMessage());
                }
            }

            // Saldo Poupanca
            if ("Saldo Poupanca".equals(optionX) && "Menu".equals(whichmenu)) {
                // pegar poupanca
                Conta contaPoupanca = instanc.getModel()
                        .getConta(clienteAtual.getID(), IDBanco, "Conta Poupanca");
                imprimeTitulo("Saldo Poupanca");
                System.out.println("Saldo: " + contaPoupanca.getSaldo());
                System.out.print("Pressione enter para voltar!");
                scanner.nextLine();

            }
        }
        scanner.close();
    }

}
