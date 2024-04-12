/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package virtualpet;
import java.util.*;
import java.util.Scanner;
import java.util.Random;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;


/**
 *
 * @author michael.roy-diclemen
 */

public class VirtualPet {
    static int maxHealth = 0;
    static int maxFood = 0;
    static int maxEnergy =0;
    static int money = 0;
    
    public static int numberGuessingGame(){
        int score = 100; //this is the initial score that the user starts with
        Scanner scanner = new Scanner(System.in);
        Random r = new Random();
        System.out.println("Number Guessing Game: \n The objective is to guess the randomly generated number between 1 and 100.");
         int numberOfAttempts = 5;
         for (int attempts = 1; attempts <= numberOfAttempts; attempts++){
            int randomNumber = r.nextInt(100)+1; //1-100 (not inclusive of 100 but adding one will make the range up to 100)
            System.out.print("\nPlease guess a number between 1 and 100: ");
            int guess = scanner.nextInt();
            if (guess>randomNumber){ //when the number guessed is greater than the generated
                System.out.print("\nToo high");
            } //end of if guess
            else if (guess<randomNumber){ //when the number guessed is too low
                System.out.print("\nToo low");
            } //end of else if guess
            else{ //when the number guessed is the same
                System.out.print("\nCorrect");
                break; // if the guess is correct, then exit the loop
            } //end of else guess
            score -= 20; //minus 20 points for each attempt

            if (score <=0){
                System.out.println("\nYou have run out of guesses. The correct number is: " + randomNumber);
            } //end of if score 
         } //end of for numberOfAttempts

         System.out.println("\nYour final score was: " + score);
         return score*10; //one point is 10 dollars
    } //end of method for game 1



    public static String shuffle(String s){

        String shuffledString = "";
        Random rn = new Random(); // create a Random object
        while (s.length() != 0)
        {
            int index = rn.nextInt(s.length());
            char c = s.charAt(index);
            s = s.substring(0,index)+s.substring(index+1);
            shuffledString += c;
        }

        return shuffledString;
    }
   
   
    public static String replaceCharAt(String s, int pos, char c){ //replace a character at a specific position(idex) in a string
        return s.substring(0, pos) + c + s.substring(pos + 1);
    }
   
       
    public static int matchingGame(){
        final int NUM_PAIRS = 5;
        final int MAX_GUESS = 7; //number of guesses expires.
        final int EARNING_CORRECT_MATCH = 10; //player earns $10 for each correct match
        final int EARNING_INCORRECT_MATCH = -2; //player loses $2 for each incorrect match
        int earnedMoney = 0;
        // 1. Initialization
        // Generate a single string containing 10 pairs of letters
        Random rn = new Random(); // Create a Random object
        String orginalString =""; //the original string containing the 10 pairs of letters
        for (int i =0; i<NUM_PAIRS; i++){
           char letter = (char)(rn.nextInt(26)+97); //generated a random lower-case character
           orginalString = orginalString + letter + letter; //append the letter twice as a pair
        }
        String shuffledString = shuffle(orginalString);
        //System.out.println("Original String: " + orginalString);
        //System.out.println("Shuffled String: " + shuffledString);
        String revealedString = "**********"; //5 pairs of *
       
        // 2. Game play; and 3. Scoring
        System.out.println(revealedString);
        //create a Scanner object for input, use the ',' and \n as the delimiter between two positions (index) in the input
        Scanner keyboard = new Scanner(System.in).useDelimiter(Pattern.compile("[\\n,]"));
                                       
        int pos1=0; //the first position(index) in the string for the pair of letters to guess
        int pos2=0; //the second position(index) in the string for the pair of letters to guess
        int numGuess = 0; // total number of guesses
        int numCorrectGuess = 0; //number of correct guess, when it reaches 10, all pairs are matched(guessed) correctly
        System.out.print("Guess two indexes of characters to see if they match.");
        while ((numGuess<MAX_GUESS)&&(numCorrectGuess<NUM_PAIRS)){// 4. End Condition: all pairs are matched or the max number of guesses is reached
            boolean matched = true;
            while (matched){ //(loop) Keep asking player to guess until the letters to guess have not be successfully matched previously
                System.out.print("\nGuess: ");
                pos1 = keyboard.nextInt(); //get the first position
                pos2 = keyboard.nextInt(); //get the second position
                if ((pos1<revealedString.length())&& (pos2<revealedString.length())){
                    if ((revealedString.charAt(pos1)=='*') && (revealedString.charAt(pos2)=='*')) {//the letter (to guess) has not been successfully matched previuously
                        matched = false;
                    }
                    else {
                        System.out.println("The letters have already been successfully matched, please guess a new pair!");
                    }
                }
                else{
                    System.out.println("the position (index) is out of bound.");
                }
            }
            if (shuffledString.charAt(pos1) == shuffledString.charAt(pos2)){ //two positions match
                //reveal the two matched letters
                revealedString = replaceCharAt(revealedString, pos1, shuffledString.charAt(pos1)); //reveal the first matched letter
                revealedString = replaceCharAt(revealedString, pos2, shuffledString.charAt(pos2)); //reveal the second matched letter
                System.out.println(revealedString);
               // System.out.println("Shuffled String: " + shuffledString);
                numCorrectGuess += 1;
                earnedMoney += EARNING_CORRECT_MATCH; // player earn $10 for each correct match
                System.out.println("earning: " + earnedMoney);
            }
            else{
                System.out.println("Your guess doesn't match! \n");
                //System.out.println("Shuffled String: " + shuffledString);
                earnedMoney += EARNING_INCORRECT_MATCH; // player loses $2 for each incorrect match
                System.out.println("earning: " + earnedMoney);
            }
           
            numGuess += 1;
       
        } // end of while
       
        //display the player's score and the number of guesses made.
        System.out.println("The score: " + earnedMoney);
        System.out.println("Number of guesses made: " + numGuess);
       
        keyboard.close();
        return earnedMoney;
       
    }

    public static double playingPet(){
        //playing with your pet
        System.out.println("Buy your pet a toy!");
        money = money - 1; //subtract money used to buy the toy
        maxEnergy = maxEnergy + 1; //increase the energy by 1 by buying pet a new toy
        System.out.println("Energy increased to " + maxEnergy); 
        return maxEnergy;
    } //end of playingPet method
    
    public static double feedPet(){
        //feeding pet
        System.out.println("Feed your pet!");
        money = money - 1;
        maxFood = maxFood + 2; // increase the food given to pet
        System.out.println("Food increased to " + maxFood + ". They are less hungry now.");
        return maxEnergy;
    } //end of feedPet method

    public static double groomPet(){
        //grooming pet
        System.out.println("Groom your pet!");
        money = money - 1;
        maxHealth = maxHealth + 2; //increase the max health 
        System.out.println("Health increased! Max health increased to: " + maxHealth);
        return maxHealth;
    
    } //end of groomPet
    
    
    public static void petStatistics(){
        Random r = new Random();
        int petStats = (r.nextInt(11)+10); //generate a random total length between 10 and 20
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
    }

//public class InputDialogBox {
    
    public static void main(String[] args) {  
        Scanner scanner = new Scanner(System.in);
        Random r = new Random();
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
        int allow = 0;
        int i = 1;
        while (allow == 0 && i <= maxTries){
        user = JOptionPane.showInputDialog("Username: ");
            //if statement to check if the username is valid.
            if(user.equals(USERNAME)){
                passwordEntry = JOptionPane.showInputDialog("Password: "); //if the username is valid, give the password prompt
                //if statement to check if the password is valid.
                if(passwordEntry.equals(PASSWORD)){
                    System.out.println("You are allowed to enter!");
                    allow = 1;
                }
                else{
                    System.out.println("Invalid password."); //if password is not valid, exit the system
                }
            }
            else{
                System.out.println("Invalid username.");
            }
            i++;
        } //end of while
        
        if (allow == 1){ //run when the valid password and username   
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
                        System.out.println("Choose a pet from this list: \nRabbit, Pegasus, Koala ");
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
                        petStatistics(); 
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
                                money += numberGuessingGame();
                                money += matchingGame();
                                System.out.print("The total money earned is: " + money);
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
                } //end of switch(menuoption)
            } // end of while exit
        } //end of if allow
        
    } //end of main

} //end of class
