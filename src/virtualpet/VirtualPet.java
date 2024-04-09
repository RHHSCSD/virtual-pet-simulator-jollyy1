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
final int MAX_GUESSES = 10; //max 10 guesses


    /**
     * @param args the command line arguments
     */
public static void numberGuessingGame(){
    Scanner scanner = new Scanner(System.in);
    Random r = new Random();
    System.out.println("Number Guessing Game: \n The objective is to guess the randomly generated numbe tbetween 1 and 100.");
     int numberOfAttempts = 5;
     for (int attempts = 1; attempts <= numberOfAttempts; attempts++){
        int randomNumber = r.nextInt(100)+1; //1-100 (not inclusive of 100 but adding one will make the range up to 100)
        int score = 100; //this is the initial score that the user starts with
        System.out.print("Please guess a number between 1 and 100: ");
        int guess = scanner.nextInt();
        if (guess>randomNumber){ //when the number guessed is greater than the generated
            System.out.print("Too high");
} //end of if guess
        else if (guess<randomNumber){ //when the number guessed is too low
            System.out.print("Too low");
        } //end of else if guess
        else{ //when the number guessed is the same
            System.out.print("Correct");
            break; // if the guess is correct, then exit the loop
     } //end of else guess
        score -= 20; //minus 20 points for each attempt

     if (score <=0){
         System.out.println("You have run out of guesses.");
     } //end of if score 
     System.out.println("Your final score was: " + score);

             } //end of for numberOfAttempts
} //end of method for game 1

public static void matchingGame(){
    System.out.println("Matching Game: \n The objective of this game is to find the pairs in the string.");
    String letters = letters(); //generate the random letters
    String revealedLetters = hideLetters(letters); //hide the letters
    int score = 0;
    int guesses = 0;
    
    while (guesses < MAX_GUESSES && !matchedPairs(revealed)){
        System.out.
        
    } //end of while matched pairs
} //end of method for game 2
public static void main(String[] args) {    
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
    int maxTries = 3;
    int randomNumber = 0;
 
    // TODO code application logic here
    //ASCII art and welcome message
    System.out.println(" __    __");
    System.out.println("/ \\..// \\");
    System.out.println("  ( oo )");
    System.out.println("  \\__/ ");
    System.out.println("Welcome to Furry Wonderland!");

    //login system
    System.out.println("Please login to the system.");
    int allow = 1;
    int i = 1;
    while (allow == 1 && i <= maxTries){
        System.out.print("User: ");
        user = scanner.next();
        //if statement to check if the username is valid.
        if(user.equals(USERNAME)){
            System.out.print("Password: "); //if the username is valid, give the password prompt
            passwordEntry = scanner.next();
            //if statement to check if the password is valid.
            if(passwordEntry.equals(PASSWORD)){
                System.out.println("You are allowed to enter!");
                allow = 0;
            }
            else{
                System.out.println("Invalid password."); //if password is not valid, exit the system
            }
        }
        else{
            System.out.println("Invalid username.");
        }
        i++;
    } //end of for 
if (allow == 0){ //run when the valid password and username   
    //Menu
    int exit = 1; 
    while (exit != 0){ //keep looping as long as exit isn't selected
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
               for (i = 0; i < nameLength; i = i + 1){
                  if (i%2 == 0){ //if i is even, add a vowel
                      int randomIndexOne = (r.nextInt(VOWELS.length())); //deteremines the total length of the string
                      nameLetters = nameLetters + (VOWELS.charAt(randomIndexOne)); //concactenates the variable nameLetters with a random character determined by the random index from the previous line
                  }
                  else{ //if i is odd, add a consonant
                      int randomIndexTwo = (r.nextInt(CONSONANTS.length())); //repeat same process with vowels with consonants
                      nameLetters = nameLetters + (CONSONANTS.charAt(randomIndexTwo));    
                  }
               } //end of for
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
            //second display menu
            exit = 0;
            System.out.println("1. Play/Interaction");
            System.out.println("2. Instructions");
            System.out.println("3. Exit");
            System.out.print("Please select an option: ");
            String displayOption = scanner.next();
            switch (displayOption){
                case "1":
                case "play":
                    numberGuessingGame();
            } //end of switch displayOption

        case "intructions":
        case "2": 
            menu = "Instructions";
            break;
        //when the user inputs exit, or the number 3, exit the program and output an exit message.
        case "exit":
        case "3": 
            menu = "Exit";
            exit = 0; 
            System.out.println("You have exited Furry Wonderland.");
            System.exit(0);
            break;
        default: 
            System.out.println("Invalid entry");
            System.exit(0);
            break;
    } //end of switch
    } // end of while exit
} //end of if allow
} //end of main
} //end of class
