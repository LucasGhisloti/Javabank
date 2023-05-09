package com.mycompany.javabank;


public class Instancias {
    private Models model;
    
    Instancias(){
        this.model = new Models();

        model.listaBanco.add(new Banco(1, "JavaBank", 1000.0, 3));

        model.listaCliente.add(new Cliente(1, "Arnaldo Sacomani", "54.986.782-89", 78963214, 9632));
        model.listaCliente.add(new Cliente(2, "Jorge Ben", "98.741.951-27", 12345678, 1234));
        model.listaCliente.add(new Cliente(3, "John McCarthy", "57.563.951-19", 78932165, 5637));
        model.listaCliente.add(new Cliente(4, "Ada Lovelace", "85.325.859-32", 84951623, 6295));
        model.listaCliente.add(new Cliente(5, "Alan Turing", "12.988.556-78", 26159483, 7562));
        model.listaCliente.add(new Cliente(6, "Orlando de Andrade Figueiredo", "96.556.001-51", 32654898, 4859));

        model.listaConta.add(new Conta(1, 1, "Conta Corrente", 30000.00, 2000.00));
        model.listaConta.add(new Conta(2, 1, "Conta Corrente", 900.00, 1500.00));
        model.listaConta.add(new Conta(3, 1, "Conta Corrente", 300000.00, 20000.00));
        model.listaConta.add(new Conta(4, 1, "Conta Corrente", 500000.00, 13000.00));
        model.listaConta.add(new Conta(5, 1, "Conta Corrente", 1500.00, 1000.00));
        model.listaConta.add(new Conta(6, 1, "Conta Corrente", 3000.00, 1200.00));
        model.listaConta.add(new Conta(6, 1, "Conta Poupanca", 7000.00, 7000.00));

        model.listaTransacao.add(new Transacao("2023-01-21", "13:31:02", 3, 2, 100.00, "Transferencia"));
        model.listaTransacao.add(new Transacao("2023-02-01", "08:11:23", 1, 1, 200.30, "Saque"));
        model.listaTransacao.add(new Transacao("2023-03-01", "10:31:02", 3, 3, 200.00, "Deposito"));
        model.listaTransacao.add(new Transacao("2023-04-25", "18:01:33", 2, 5, 50.00, "Transferencia"));
        model.listaTransacao.add(new Transacao("2023-04-30", "12:54:18", 7, 6, 2.50, "Transferencia"));
        model.listaTransacao.add(new Transacao("2023-05-01", "20:02:49", 3, 5, 59.35, "Transferencia"));
        model.listaTransacao.add(new Transacao("2023-05-05", "20:02:49", 6, 6, 59.35, "Deposito"));
        
        //model.listaConta.get(6).GerarExtrato(model, "2023-01-01", "2023-05-01");

        /*
        model.listaConta.forEach((t) -> {
            System.out.println(model.listaConta.indexOf(t)+" - "+t.getClienteID());
        });
        
        for(int i = 0; i< model.listaTransacao.size(); i++){
            System.out.println(model.listaTransacao.get(i).toString(model)+"\n\n");
        }
        System.out.println(trans.toString());
        
        System.out.println(cli.getID() + " " + cli.getNome());
        System.out.println(conta1.getSaldo() + " " + conta1.getLimiteSaque());
        if (cli.Sacar(conta1, 2000.01)){
            System.out.println(conta1.getSaldo());
        } else {
            System.out.println("Saque negado.");
        }*/
    }

    public Models getModel(){
        return this.model;
    }

}