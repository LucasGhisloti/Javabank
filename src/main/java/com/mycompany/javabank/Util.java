/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javabank;

import java.util.Scanner;

/**
 *
 * @author Lucas Ghisloti
 */
public class Util{
    private Scanner scanner = new Scanner(System.in);
    private boolean condition;
    
    public int entradaCliente(String msg, Models model){
        int IDCliente = 0;
        
        do{
            System.out.print(msg+": ");
            String tmp = scanner.nextLine();
            if(!tmp.isEmpty()){
                IDCliente = Integer.parseInt(tmp);
                if(IDCliente < 0){ 
                    IDCliente = 0;
                    msg = "Id inexistente!\nTente novamente";
                }else if(IDCliente > (model.listaCliente.size())){
                    IDCliente = 0;
                    msg = "Id inexistente!\no maior id e: "+(model.listaCliente.size())+"\n"+
                                     "Tente novamente";
                }
            }
        }while(IDCliente == 0);
        
        return IDCliente;
    }
    
    public void entradaSenha(String msg, Cliente cliente, String qualSenha){
        int senha;
        condition = false;
        
        do {
            System.out.print(msg+": ");
            String tmp = scanner.nextLine();
            if(!tmp.isEmpty()){ 
                if(!tmp.matches("[0-9]*"))
                    msg = "Senha incorreta!\nTente novamente";
                else{
                    senha = Integer.parseInt(tmp); 
                    if(senha == cliente.getSenhaTransac() && qualSenha.equals("Transacao")) condition = true;
                    else if(senha == cliente.getSenhaLogin() && qualSenha.equals("Login")) condition = true;
                    else msg = "Senha incorreta!\nTente novamente";
                }
            }
        } while (!condition);
    }
    
    public int entradaSenhaNovaConta(String tipoSenha){
        condition = false;
        String senha, senha2;
        
        String msg = "Senha de "+tipoSenha;
        do{
            System.out.print(msg+": ");
            senha = scanner.nextLine();
            
            if(!senha.matches("[0-9]*"))
                msg = "A senha deve conter apenas numeros!\nTente novamente";
            else{
                if(senha.isEmpty())
                    msg = "É necessário inserir uma senha!\nTente novamente";
                else if(tipoSenha.equals("Login")){
                    if(senha.length() < 8 || senha.length() > 8)
                        msg = "A senha de "+tipoSenha+" deve conter 8 numeros!\nTente novamente";
                    else
                        condition = true;
                }else if(tipoSenha.equals("Transacao")){
                    if(senha.length() < 4 || senha.length() > 4)
                        msg = "A senha de "+tipoSenha+" deve conter 4 numeros!\nTente novamente";
                    else
                        condition = true;
                }else condition = true;
            }
        }while (!condition);
        
        condition = false;
        msg = "Confirme sua senha";
        do {
            System.out.print(msg+": ");
            senha2 = scanner.nextLine();
            if(!senha2.isEmpty()){
                if(senha.equals(senha2)){
                    condition = true;
                }else{
                    msg = "Senhas não conferem!\nTente Novamente";
                }
            }
        } while (!condition);
        
        return Integer.parseInt(senha);
    }
    
    public double entradaValor(String msg){
        double valor;
        condition = false;
        
        do {
            System.out.print(msg+": ");
            valor = Double.parseDouble(scanner.nextLine());
            if(valor <= 0){
                msg = "Valor inválido!\nTente Novamente";
            }else condition = true;
        } while (!condition);
        
        return valor;
    }
    
    public String entradaNomeEDoc(String msg){
        String tmp;
        condition = false;

        do {
            System.out.print(msg+": ");
            tmp = scanner.nextLine();
            if(!tmp.isEmpty())
                condition = true;
        } while (!condition);
        
        return tmp;
    }

    public void splash(){
         System.out.println(
            "    ___________\n" +
            "   <___________> ___\n" +
            "   |    | |    |/ _ \\                     Bem-vindo ao\n" +
            "   |   / __)   | | | |     __   __   _  _   __   ____   __   __ _  __ _ \n" +
            "   |   \\__ \\   | |_| |   _(  ) / _\\ / )( \\ / _\\ (  _ \\ / _\\ (  ( \\(  / )\n" +
            " __|   (   /   |\\___/   / \\) \\/    \\\\ \\/ //    \\ ) _ (/    \\/    / )  ( \n" +
            "/   \\___|_|___/   \\     \\____/\\_/\\_/ \\__/ \\_/\\_/(____/\\_/\\_/\\_)__)(__\\_)\n" +
            "\\_________________/"
         );
     }
}
