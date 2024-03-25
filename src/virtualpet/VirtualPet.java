/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package virtualpet;
import java.util.*;
import java.util.Scanner;
/**
 *
 * @author michael.roy-diclemen
 */

public class VirtualPet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
 //Variables and constants
 String menuOption = "";
 String menu = "";
 String petChoice = "";
 String username = "";
 String password = "";
 
        // TODO code application logic here
//ASCII art and welcome message
System.out.println(" __    __");
System.out.println("/ \\..// \\");
System.out.println("  ( oo )");
System.out.println("  \\__/ ");
System.out.println("Welcome to Furry Wonderland!");

//login system
System.out.println("Please login to the system.");
System.out.print("User: ");
username = scanner.next();
//if statement to check if the username is valid.
if(username.equals("snoopy")){
    System.out.print("Password: ");
    password = scanner.next();
    //if statement to check if the password is valid.
    if(password.equals("toto")){
        System.out.println("You are allowed to enter!");
    }
    else{
        System.out.println("Invalid password.");
        System.exit(0);
    }
}
else{
    System.out.println("Invalid username.");
    System.exit(0);
}
//Menu
System.out.println("MENU");
System.out.println("1. Start");
System.out.println("2. Instructions");
System.out.println("3. Exit");
System.out.print("Please select an option: ");
menuOption = scanner.next();
//switch statement used to find out which option the user wants to use (using both the words and number corresponding with the option)
switch (menuOption){
    //allow the user to choose from the given list
    case "start": 
        menu = "Start";
        System.out.println("Choose a pet from this list: \n Rabbit, Pegasus, Koala ");
        petChoice = scanner.next();
        System.out.println("The pet that you chose was: " + petChoice);
        break;
    case "intructions":
        menu = "Instructions";
        break;
    //when the user inputs exit, or the number 3, exit the program and output an exit message.
    case "exit":
        menu = "Exit";
        System.out.println("You have exited Furry Wonderland.");
        System.exit(0);
        break;
    case "1":
        //Ensure that both the number and word "start" give the same option
        menu = "Start";
        System.out.println("Choose a pet from this list: \nRabbit, Pegasus, Koala ");
        petChoice = scanner.next();
        System.out.println("The pet that you chose was: " + petChoice);
        break;
    case "2":
        menu = "Instructions";
        break;
    case "3":
        menu = "Exit";
        System.out.println("You have exited Furry Wonderland.");
        System.exit(0);
        break; 
    default: 
        System.out.println("Invalid entry");
        break;


        
    
    
}


    }
    
}
