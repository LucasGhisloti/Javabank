/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.javabank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
    static Util util = new Util();
    static int IDBanco;
    static Banco javabankObj;
    
    static void imprimeTitulo(String titulo){
        System.out.println("\n\033[H\033[2J"
                + "<< " + titulo + " >>");
    }
    
    static void novaConta(Instancias instanc, Scanner scanner){        
        Cliente cliente;
        cliente = new Cliente(
                instanc.getModel().getIDdeNovoCliente(), "", "", 0, 0);
        Conta conta = new Conta(0, 1, "", 0, 0);

        imprimeTitulo("Nova conta");

        cliente.setNome(util.entradaNomeEDoc("Seu nome"));
        cliente.setDocumento(util
                .entradaNomeEDoc("Seu documento (CPF/CNPJ)"));
        cliente.setSenhaLogin(util.entradaSenhaNovaConta("Login"));
        cliente.setSenhaTransac(util.entradaSenhaNovaConta("Transacao"));
        conta.setTipo("Conta Corrente");
        conta.setSaldo(0);
        conta.setBancoID(IDBanco);
        conta.setClienteID(cliente.getID());
        conta.setLimiteSaque(javabankObj.getLimiteInicialDeSaque());

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
        if(contaPoupanca != null) instanc.getModel().listaConta.add(contaPoupanca);

        System.out.print("\nConta criada com sucesso!\n"
                + "GRAVE SEUS DADOS E NÃO PERCA!\n"
                + "- Seu ID é ["+cliente.getID()+"].\n"
                + "Pressione enter para continuar...");
        scanner.nextLine();
    }
    
    static void extrato(Instancias instanc, Conta contaAtual, Scanner scanner){
        imprimeTitulo("Extrato");
        LocalDate data = LocalDate.now();

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        data = LocalDate.parse(data.toString(), dateFormat);
        LocalDate data_minus60 = LocalDate.parse(
                data.minusDays(300).toString(), dateFormat);

        contaAtual.GerarExtrato(instanc.getModel(),
                data_minus60.toString(), data.toString());

        System.out.print("Pressione enter para voltar!");
        scanner.nextLine();
    }
    
    static void saque(Conta contaAtual, Cliente clienteAtual,
            Instancias instanc, Scanner scanner){
        imprimeTitulo("Saque");
        System.out.println("Voce pode sacar ate "+ contaAtual.getLimiteSaque());

        double valor = util.entradaValor("Valor do saque");
        util.entradaSenha("Senha de transacao", clienteAtual, "Transacao");

        try {
            clienteAtual.Sacar(instanc.getModel(), contaAtual, valor);
            System.out.println("\nSaque feito com sucesso!");
        } catch (SaldoInsuficienteException | limiteSaqueException e) {
            System.out.println(e.getMessage());
        }

        System.out.print("Pressione enter para voltar!");
        scanner.nextLine();
    }
    
    static void deposito(Cliente clienteAtual, Conta contaAtual,
            Instancias instanc){
        imprimeTitulo("Deposito");
        System.out.println("Depositos restantes permitidos: "
                +(javabankObj.getQtdLimiteDeposito()
                        - clienteAtual.getQtdDepositoCC()));

        double valor = util.entradaValor("Valor do deposito");

        try {
            clienteAtual.Depositar(instanc.getModel(), contaAtual, valor);
            System.out.println("Deposito feito com sucesso!");
        } catch (DepositoLimiteException e) {
            System.out.println(e.getMessage());
        }
    }
    
    static void transferencia(Instancias instanc, String footer,
            Cliente clienteAtual, Conta contaAtual){
        imprimeTitulo("Transferencia");
        double valor = util.entradaValor("Valor a transferir");
        int IDCliente = util.entradaCliente(
                "ID do cliente que recebera a transferencia",
                instanc.getModel());

        Cliente clienteDestino = instanc.getModel().getCliente(IDCliente);

        System.out.println("Voce esta transferindo para: "
                + clienteDestino.getNome());

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
        String optionConta = selecaoConta.load(
                "Selecione o tipo de conta que recebera a transferencia:\n",
                footer);

        if (optionConta.equals("Voltar")) return;

        Conta contaDestino = instanc.getModel()
                .getConta(clienteDestino.getID(), IDBanco, optionConta);

        util.entradaSenha("Senha de transacao", clienteAtual, "Transacao");

        try {
            clienteAtual.Transferencia(instanc.getModel(), contaAtual, 
                    contaDestino, valor);
            System.out.println("Transferencia feita com sucesso!");
        } catch (SaldoInsuficienteException e) {
           System.out.println(e.getMessage());
        }
    }
    
    static void depositoPoupanca(Instancias instanc, Cliente clienteAtual){
        Conta contaPoupanca = instanc.getModel()
                .getConta(clienteAtual.getID(), IDBanco, "Conta Poupanca");
        imprimeTitulo("Deposito em Poupanca");
        System.out.println("Depositos restantes permitidos: "
                           +(javabankObj.getQtdLimiteDeposito() 
                                   - clienteAtual.getQtdDepositoPoupanca()));

        double valor = util.entradaValor("Valor do deposito");

        try {
            clienteAtual.Depositar(instanc.getModel(), contaPoupanca, valor);
            System.out.println("Deposito feito com sucesso!");
        } catch (DepositoLimiteException e) {
            System.out.println(e.getMessage());
        }
    }
    
    static void saquePoupanca(Instancias instanc, Cliente clienteAtual, Scanner scanner){
        Conta contaPoupanca = instanc.getModel()
                .getConta(clienteAtual.getID(), IDBanco, "Conta Poupanca");

        imprimeTitulo("Saque Poupanca");
        System.out.println("Voce pode sacar ate "+ contaPoupanca.getLimiteSaque());

        double valor = util.entradaValor("Valor do saque");

        util.entradaSenha("Senha de transacao", clienteAtual, "Transacao");

        try {
            clienteAtual.Sacar(instanc.getModel(), contaPoupanca, valor);
            System.out.println("Saque feito com sucesso!");
        } catch (SaldoInsuficienteException | limiteSaqueException e) {
            System.out.println(e.getMessage());
        }

        System.out.print("Pressione enter para voltar!");
        scanner.nextLine();
    }
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Instancias instanc = new Instancias();
            javabankObj = instanc.getModel().getBancobyName("JavaBank");
            IDBanco = javabankObj.getID();
            
            String[] menuLoginItems = { "Login", "Nova Conta", "Sair" };
            String[] menuItems = { "Extrato", "Saque", "Deposito", "Transferencia" };
            String optionX = "";
            String whichmenu = "Login";
            Ui login = new Ui(menuLoginItems);
            Ui menu = new Ui(menuItems);
            Cliente clienteAtual = new Cliente(0, "", "", 0, 0);
            
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
                
                if ("Login".equals(whichmenu)){
                    if ("Login".equals(optionX)) {
                        imprimeTitulo("Login");

                        clienteAtual = instanc.getModel().getCliente(
                                util.entradaCliente("Numero do cliente", 
                                        instanc.getModel()));

                        util.entradaSenha("Senha", clienteAtual, "Login");

                        // pesquisar conta nas instancias
                        contaAtual = instanc.getModel()
                                .getConta(clienteAtual.getID(), IDBanco, 
                                        "Conta Corrente");
                        clienteAtual.setQtdDepositoCC(instanc.getModel()
                                .contaDepositoHoje(contaAtual.getID()));

                        Conta contaPoupanca = instanc.getModel()
                                .getConta(clienteAtual.getID(), IDBanco,
                                        "Conta Poupanca");
                        if (contaPoupanca != null) {
                            clienteAtual.setQtdDepositoPoupanca(
                                    instanc.getModel()
                                            .contaDepositoHoje(
                                                    contaPoupanca.getID()));
                            menu.addMenuItem("Saque Poupanca");
                            menu.addMenuItem("Deposito Poupanca");
                        }
                        if (!"Sair".equals(
                                new ArrayList<>(Arrays
                                        .asList(menu.getMenuItems()))
                                        .get(menu.getMenuItems().length - 1))) {
                            menu.addMenuItem("Sair");
                        }

                        whichmenu = "Menu";
                    }
                    if ("Nova Conta".equals(optionX)) {
                        novaConta(instanc, scanner);
                    }
                }

                if ("Menu".equals(whichmenu)){
                    if ("Extrato".equals(optionX))
                        extrato(instanc, contaAtual, scanner);
                    if ("Saque".equals(optionX))
                        saque(contaAtual, clienteAtual, instanc, scanner);
                    if ("Deposito".equals(optionX))
                        deposito(clienteAtual, contaAtual, instanc);
                    if ("Transferencia".equals(optionX))
                        transferencia(instanc, footer, clienteAtual, contaAtual);
                    if ("Saque Poupanca".equals(optionX))
                        saquePoupanca(instanc, clienteAtual, scanner);
                    if ("Deposito Poupanca".equals(optionX))
                        depositoPoupanca(instanc, clienteAtual);
                }
            }
        }
    }

}
