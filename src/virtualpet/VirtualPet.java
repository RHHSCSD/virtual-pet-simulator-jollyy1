/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package virtualpet;
import java.util.*;
import java.util.Scanner;
import java.util.Random;
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
 final String USERNAME = "snoopy";
 final String PASSWORD = "toto";
 int petNameOption = 0;
 String user = "";
 String passwordEntry = "";
 String petName = "";
 final String VOWELS = "aeiou";
 final String CONSONANTS = "bcdfghjklmnpqrstvwxyz";
 int nameLength = 0;
 String nameLetters = "";
 int petStats = 0;
 int maxHealth = 0;
 int maxFood = 0;
 int maxEnergy = 0;
 Random r = new Random();
 
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
user = scanner.next();
//if statement to check if the username is valid.
if(user.equals(USERNAME)){
    System.out.print("Password: "); //if the username is valid, give the password prompt
    passwordEntry = scanner.next();
    //if statement to check if the password is valid.
    if(passwordEntry.equals(PASSWORD)){
        System.out.println("You are allowed to enter!");
    }
    else{
        System.out.println("Invalid password."); //if password is not valid, exit the system
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
    case "1": //ensure that both the number and word "start" give the same option
        menu = "Start";
        System.out.println("Choose a pet from this list: \n Rabbit, Pegasus, Koala ");
        petChoice = scanner.next();
        System.out.println("The pet that you chose was: " + petChoice);
        System.out.print("It is time to name your pet. Please select 1 to type your pet's name or 2 to have the system generate a random name. ");
        petNameOption = scanner.nextInt();
        if(petNameOption == 1){ //if the user selected 1, prompt them to enter a name.
            System.out.print("Please enter a name for your pet: ");
            petName = scanner.next();
            System.out.print("Your pet, named " + petName + ", has been born!"); //output pet name
        }
        else if(petNameOption == 2){
           nameLength = (r.nextInt(5)+4); //generate a random name length between 4 and 8 characters. 0-5 without 5 being inclusive and then adding 4 to the number to give an output of between 4 and 8.
           for (int i = 0; i < nameLength; i = i + 1){
              if (i%2 == 0){ //if i is even, add a vowel
                  int randomIndexOne = (r.nextInt(VOWELS.length())); //deteremines the total length of the string
                  nameLetters = nameLetters + (VOWELS.charAt(randomIndexOne)); //concactenates the variable nameLetters with a random character determined by the random index from the previous line
              }
              else{ //if i is odd, add a consonant
                  int randomIndexTwo = (r.nextInt(CONSONANTS.length())); //repeat same process with vowels with consonants
                  nameLetters = nameLetters + (CONSONANTS.charAt(randomIndexTwo));    
              }
           }
           System.out.print("Your pet, named " + nameLetters + ", has been born!"); //output the name of the pet.

        }          
        //part 5 - give stats to the pet
        petStats = (r.nextInt(11)+10); //generate a random total length between 10 and 20
        //generate random values between 1 and 10 for each stat
        maxHealth = r.nextInt(10)+1;
        maxFood = r.nextInt(10)+1;
        maxEnergy = r.nextInt(10)+1;
        //make sure that the sum of all three do not exceed pet stats 
        if (maxHealth + maxFood + maxEnergy > petStats){
            maxHealth = Math.min(maxHealth, petStats - 2);
            maxFood = Math.min(maxFood, petStats - maxHealth - 1); //make sure that (at least) two points are available for the other two stats
            maxEnergy = Math.min(maxEnergy, petStats - maxHealth - maxFood); //make sure that (at least) one point is available for the other stat
        }//output results
        System.out.println("\nMAX HEALTH: " + maxHealth + ", MAX FOOD: " + maxFood + ", MAX ENERGY:  " + maxEnergy);
        
        break;
  
    case "intructions":
    case "2": 
        menu = "Instructions";
        break;
    //when the user inputs exit, or the number 3, exit the program and output an exit message.
    case "exit":
    case "3": 
        menu = "Exit";
        System.out.println("You have exited Furry Wonderland.");
        System.exit(0);
        break;
    default: 
        System.out.println("Invalid entry");
        System.exit(0);
        break;


        
    
    
}


    }
    
}
