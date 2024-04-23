/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package virtualpet;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;


/**
 *
 * @author jolly yan
 */

public class VirtualPet {
    static int[] currentStats = new int[3];
    static int[] maxStats = new int[3];
    static int[] actions = new int[3];
    static int money = 100; //give $100 to start
    static String petChoice = "";
    //static final String USERNAME = "snoopy";
    //static final String PASSWORD = "toto";
    static int petNameOption = 0;
    static String user = "";
    static String passwordEntry = "";
    static String petName = "";
    static final String VOWELS = "aeiou";
    static final String CONSONANTS = "bcdfghjklmnpqrstvwxyz";
    static int nameLength = 0;
    static String nameLetters = "";
    static final int MAX_TRIES = 3;
    static Scanner scanner = new Scanner(System.in);
    
    public static void startGame(){
        System.out.println("Choose a pet from this list: \nRabbit, Pegasus, Koala ");
//        Scanner scanner = new Scanner(System.in);
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
            Random r = new Random();
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
           } //end of for
           System.out.print("Your pet, named " + nameLetters + ", has been born!"); //output the name of the pet.
        }

        //pet stats 
        petStatistics();
        
    }
    
    //ASCII art and welcome message
    public static void welcomeMessage(){
    System.out.println(" __    __");
    System.out.println("/ \\..// \\");
    System.out.println("  ( oo )");
    System.out.println("  \\__/ ");
    System.out.println("Welcome to Furry Wonderland!");
    }

    //login system
    public static boolean loginSystem(){
        boolean loginOK = false;
        user = JOptionPane.showInputDialog("Username: "); 
        File f = new File(user + ".txt");
        Scanner input = null;
        String password = "";
        if (f.exists()){ //existing user
            try{
                input = new Scanner(f);
                password = input.nextLine();
            }
            catch(IOException e){
                System.out.println("There is an issue with reading the file.");
            }
            if(input!=null){input.close();}
            
            int i =0;
            while ((!loginOK) && (i < MAX_TRIES)){
                passwordEntry = JOptionPane.showInputDialog("Password: "); 
                if (passwordEntry.equals(password)){
                        loginOK = true;
                }
                else{
                   System.out.println("The password is wrong. try again"); 
                }
            } //end of while
        }
        else { //file doesn't exit, new user
            passwordEntry = JOptionPane.showInputDialog("Password: "); 
            loginOK= true;
        }
        return loginOK;
    }

    public static void mainMenu(){   
        //Menu
        boolean exit = false; 
 //       Scanner scanner = new Scanner(System.in);
        while (!exit){ //keep looping as long as exit isn't selected
            System.out.println("\nMENU");
            System.out.println("1. Start");
            System.out.println("2. Instructions");
            System.out.println("3. Exit");
            System.out.println("Please select an option: ");
            String menuOption = scanner.next();
            System.out.println("Menu Option is: " + menuOption);
            //switch statement used to find out which option the user wants to use (using both the words and number corresponding with the option)
            switch (menuOption.toLowerCase()){
                //allow the user to choose from the given list
                case "start": 
                case "1": //ensure that both the number and word "start" give the same option
                    startGame();
                    //display menu
                    System.out.println("1. Play/Interaction");
                    System.out.println("2. Instructions");
                    System.out.println("3. Exit");
                    System.out.print("Please select an option: ");
                    String displayOption = scanner.next();
                    switch (displayOption.toLowerCase()){
                        case "1":
                        case "play":
                            money += numberGuessingGame();
                            money += matchingGame();
                            System.out.print("The total money earned is: " + money);
                            petStatistics(); // initialize the pet stats
                            interactions();
                            displayActions();
                            PrintWriter output = null;
                            try{
                                FileWriter petSimulator = new FileWriter(user + ".txt");
                                output = new PrintWriter(petSimulator);
                                output.println(passwordEntry);
                                output.println(petChoice);
                                for (int i = 0; i < 3; i++){
                                    output.println(maxStats[i]);
                                    output.println(currentStats[i]);
                                }
                                output.println(money);
                            } 
                            catch (IOException e){
                                System.out.println("The file doesn't exist");
                            }
                            if (output!=null){output.close();}
                            break;
                    } //end of switch displayOption

                    break;

                case "instructions":
                case "2": 
                    printInstructions();
                    break;
                //when the user inputs exit, or the number 3, exit the program and output an exit message.
                case "exit":
                case "3": 
                    //menu = "Exit";

                    System.out.println("You have exited Furry Wonderland.");
                    exit = true;
                    break;
                default: 
                    System.out.println("Invalid entry");
                    break;
            } //end of switch(menuoption)
        } // end of while exit
    } //end of mainMenu method
       
    public static void printInstructions(){
        System.out.println("Instructions: Welcome to the virtual pet simulator where you can play games and interact with you pet.");
        System.out.println("\nYou can play games to earn money and use that money to buy food, grooming sessions and toys.");
        System.out.println("\nYou can name your pet and also see its stats.");
    }
    
    public static int numberGuessingGame(){
        int score = 100; //this is the initial score that the user starts with
//        Scanner scanner = new Scanner(System.in);
        Random r = new Random();
        System.out.println("Number Guessing Game: \n The objective is to guess the randomly generated number between 1 and 100.");
        int numberOfAttempts = 5;
        int randomNumber = r.nextInt(100)+1; //1-100 (not inclusive of 100 but adding one will make the range up to 100)
        for (int attempts = 1; attempts <= numberOfAttempts; attempts++){
            System.out.print("Please guess a number between 1 and 100: ");
            int guess = scanner.nextInt();
            if (guess>randomNumber){ //when the number guessed is greater than the generated
                System.out.println("\nToo high");
            } //end of if guess
            else if (guess<randomNumber){ //when the number guessed is too low
                System.out.println("\nToo low");
            } //end of else if guess
            else{ //when the number guessed is the same
                System.out.println("\nCorrect");
                break; // if the guess is correct, then exit the loop
            } //end of else guess
            score -= 20; //minus 20 points for each attempt

            if (score <=0){
                System.out.println("\nYou have run out of guesses. The correct number is: " + randomNumber);
            } //end of if score 
        } //end of for numberOfAttempts
        
        if (score<0){ //the minimum score is 0
            score = 0;
        }
        System.out.println("Your final score was: " + score);
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
        final int EARNING_CORRECT_MATCH = 10; //player earns 10 points for each correct match
        final int EARNING_INCORRECT_MATCH = 1; //player loses 1 point for each incorrect match
        int score = 0;
        System.out.println("\nMatching Game: \n The objective is to guess two indexes of characters to see if they match.\n use the ',' as the delimiter.");
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
        Scanner scannerSpecialDelimiter = new Scanner(System.in).useDelimiter(Pattern.compile("[\\n,\\s+]")); //delimiter \n, comma, one or more whitespaces 
                                       
        int pos1=0; //the first position(index) in the string for the pair of letters to guess
        int pos2=0; //the second position(index) in the string for the pair of letters to guess
        int numGuess = 0; // total number of guesses
        int numCorrectGuess = 0; //number of correct guess, when it reaches 10, all pairs are matched(guessed) correctly
        while ((numGuess<MAX_GUESS)&&(numCorrectGuess<NUM_PAIRS)){// 4. End Condition: all pairs are matched or the max number of guesses is reached
            boolean matched = true;
            while (matched){ //(loop) Keep asking player to guess until the letters to guess have not be successfully matched previously
                System.out.print("\nGuess: ");
                //use the ',' and \n as the delimiter between two positions (index) in the input
                pos1 = scannerSpecialDelimiter.nextInt(); //get the first position
                pos2 = scannerSpecialDelimiter.nextInt(); //get the second position
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
               //System.out.println("Shuffled String: " + shuffledString);
                numCorrectGuess += 1;
                score += EARNING_CORRECT_MATCH; // player earn 10 ponts for each correct match
                System.out.println("points earned: " + score);
            }
            else{
                System.out.println("Your guess doesn't match!");
                System.out.println(revealedString);
                //System.out.println("Shuffled String: " + shuffledString);
                score -= EARNING_INCORRECT_MATCH; // player loses 1 point for each incorrect match
                System.out.println("points lost: " + score);
            }
           
            numGuess += 1;
       
        } // end of while
       
        if (score<0){ //the minimum score is 0
            score = 0;
        }
        //display the player's score and the number of guesses made.
        System.out.println("The final score was: " + score);
        System.out.println("Number of guesses made: " + numGuess);
        return score*10; //one point is 10 dollars
       
    }
    public static void interactions(){
        System.out.println("Interact with your pet!");
        boolean exit = false;
        while (!exit){
        System.out.println();
        System.out.println("1. Feed your pet: ");
        System.out.println("2. Groom your pet: ");
        System.out.println("3. Play with your pet: ");
        System.out.println("4. Exit");
        int interact = scanner.nextInt();
        switch (interact){ 
            case 1: 
                feedPet();
                break;          
            case 2:
                groomPet();
                break;
            case 3: 
                playingPet();
                break;
            case 4: 
                exit = true;
                break;
            default:
                System.out.println("Bad choice!");    
                break;
        }
        System.out.println("The money left: " + money);
    }
     System.out.println("\nCurrent HEALTH: " + currentStats[0] + 
", current FOOD: " + currentStats[1] + ", current ENERGY: " + currentStats[2]); 
    }

    public static void playingPet(){
        //playing with your pet
        System.out.println("Buy your pet a toy!");
        if (money>0){
            money = money - 1; //subtract money used to buy the toy
            if (currentStats[2] < maxStats[2]){
                currentStats[2] = currentStats[2] + 1; //increase the energy by 1 by buying pet a new toy
                actions[0]++;
                System.out.println("Energy increased to " + currentStats[2]); 
            }
            else {
               System.out.println("The pet has the maximum energy. You do not have to play.");
            }
        }
        else{
            System.out.println("There is not enough money to buy a toy.");
        }
    } //end of playingPet method
    
    
    public static void feedPet(){
        //feeding pet
        System.out.println("Feed your pet!");
        if (money>0){
            money = money - 1;
            if (currentStats[1] < maxStats[1] - 1){
                currentStats[1] = currentStats[1] + 2; // increase the food given to pet
                actions[1]++;
                System.out.println("Food increased to " + currentStats[1] + ". They are less hungry now.");
            }
            else{
                System.out.println("The pet has the maximum food. You do not have to feed.");  
            }
        }
        else{
            System.out.println("There is not enough money to feed your pet.");
        }
    } //end of feedPet method

    public static void groomPet(){
        //grooming pet
        System.out.println("Groom your pet!");
        if (money>0){
            money = money - 1;
            if (currentStats[0]< maxStats[0]-1){
                currentStats[0] = currentStats[0] + 2; //increase the max health
                actions[2]++;
                System.out.println("Health increased! Max health increased to: " + currentStats[0]);
            }
            else{
                System.out.println("The pet reaches max health. You do not have to groom.");
            }
        }
        else{
            System.out.println("There is not enough money to groom your pet.");
        }
    } //end of groomPet
    
    public static void displayActions(){
        System.out.println("\nSummary of actions of today: ");
        System.out.println("\nToday you have played with your pet: " + actions[0] + " times");
        System.out.println("\nYou have fed your pet: " + actions[1] + " times");
        System.out.println("\nYou have groomed your pet: " + actions[2] + " times");
        
        if(actions[0] >= 5){
            System.out.println("Congratulations, you have earned the eager player achievement");
            
        }
        if(actions[1] >= 5){
            System.out.println("Congratulations, you have earned the wholehearted eater achievement");
            
        }
        if(actions[2] >= 5){
            System.out.println("Congratulations, you have earned the furry mentor achievement");
        }
    }
    
    public static void petStatistics(){
        Random r = new Random();
        int petStats = (r.nextInt(11)+10); //generate a random total length between 10 and 20
        //turn the pet stats into an array
        //generate random values between 1 and 10 for each stat
        //maxHealth
        maxStats[0]= r.nextInt(10)+1;
        //maxFood
        maxStats[1]= r.nextInt(10)+1;
        //maxEnergy 
        maxStats[2] = r.nextInt(10)+1;
        
        //make sure that the sum of all three do not exceed pet stats 
        if (maxStats[0] + maxStats[1] + maxStats[2]> petStats){
            maxStats[0] = Math.min(maxStats[0], petStats - 2);
            maxStats[1] = Math.min(maxStats[1], petStats - maxStats[0] - 1); //make sure that (at least) two points are available for the other two stats
            maxStats[2] = Math.min(maxStats[2], petStats - maxStats[0] - maxStats[1]); //make sure that (at least) one point is available for the other stat
        }//output results
        System.out.println("\nMAX HEALTH: " + maxStats[0] + ", MAX FOOD: " + maxStats[1] + ", MAX ENERGY:  " + maxStats[2]); 
    }
   
    
    public static void main(String[] args) {
        welcomeMessage();
        if (loginSystem()){
            mainMenu();
        }
        else{
            System.out.println("There are no more login attempts.");
        }

        scanner.close();
    } //end of main
    
} //end of class
