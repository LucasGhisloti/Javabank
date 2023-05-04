package com.mycompany.javabank;
import java.util.Scanner;
public class Ui {
     // Atributos
     private String[] menuItems;
     private int menuIndexatual;
     private String menuSelector;
     private int countItems;
    
     final private String upChar = "w";
     final private String downChar = "s";
     final private String selectChar = "x";

     // Construtor
     public Ui() {
         this.menuItems = new String[0];
         this.menuIndexatual = 0;
         this.menuSelector = "> ";
         this.countItems = 0;
         
     }
     
     public Ui(String[] menuItems) {
         this.menuItems = menuItems;
         this.menuIndexatual = 0;
         this.menuSelector = "> ";
         this.countItems = menuItems.length;
     }


     public String load(String header,String footer){
        String line="";
        String option="";
        while(option==""){
            
            Scanner scanner = new Scanner(System.in);
            //limpar tela
            System.out.print("\033[H\033[2J");
            System.out.print(header);
            //System.out.println(line);
            
            this.printMenu();
            //ler tecla do teclado
            System.out.print(footer);
            line =  scanner.nextLine();

            if(line.equals(this.upChar)){
                this.upMenu();
            }else if(line.equals(this.downChar)){
                this.downMenu();
            }else if(line.equals(this.selectChar)){
                option = this.getMenuItems()[this.getMenuIndexatual()];
                
            }

            

        }
        return option;
     }

     public String getFooter(){
         return "Down:" + this.downChar
                 + " Up:" + this.upChar
                 + " Select:" + this.selectChar;
     }

     public String load(){
        String line="";
        String option="";
        while(option==""){
            Scanner scanner = new Scanner(System.in);
            //limpar tela
            System.out.print("\033[H\033[2J");
            System.out.println(line);
            
            this.printMenu();
            //ler tecla do teclado
            line =  scanner.nextLine();

            if(line.equals(this.upChar)){
                this.upMenu();
            }else if(line.equals(this.downChar)){
                this.downMenu();
            }else if(line.equals(this.selectChar)){
                option = this.getMenuItems()[this.getMenuIndexatual()];
                
            }

            

        }
        return option;
     }
     
     // Métodos
     public String[] getMenuItems() {
         return this.menuItems;
     }
     
     public int getMenuIndexatual() {
         return this.menuIndexatual;
     }
     
     public String getMenuSelector() {
         return this.menuSelector;
     }
     
     public int getCountItems() {
         return this.countItems;
     }
     
     public void downMenu() {
         if (this.menuIndexatual < this.countItems - 1) {
             this.menuIndexatual++;
         }
     }
     
     public void upMenu() {
         if (this.menuIndexatual > 0) {
             this.menuIndexatual--;
         }
     }
     
     public void printMenu() {
         for (int i = 0; i < this.countItems; i++) {
             if (i == this.menuIndexatual) {
                 System.out.println(this.menuSelector + this.menuItems[i]);
             } else {
                 System.out.println("  " + this.menuItems[i]);
             }
         }
     }
     
     public void setMenuItems(String[] menuItems) {
         this.menuItems = menuItems;
         this.countItems = menuItems.length;
     }
     
     public void setMenuIndexatual(int menuIndexatual) {
         this.menuIndexatual = menuIndexatual;
     }
     
     public void setMenuSelector(String menuSelector) {
         this.menuSelector = menuSelector;
     }
     
     public void addMenuItem(String menuItem) {
         String[] newMenuItems = new String[this.countItems + 1];
         for (int i = 0; i < this.countItems; i++) {
             newMenuItems[i] = this.menuItems[i];
         }
         newMenuItems[this.countItems] = menuItem;
         this.menuItems = newMenuItems;
         this.countItems++;
 
     }
 
     
}
