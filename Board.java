import java.util.Scanner;
import java.io.File;
public class Board{

  private String phrase;
  private String findPhrase;
  private String lettersGuessed;
  private int who;
  private boolean correct;

  public Board(Player player, Player player2){
    // variable initialization
    phrase = loadPhrase();
    findPhrase = emptyPhrase(phrase);
    lettersGuessed = "";
    int points = 0;
    String letter = "";
    correct = false;
    // stringbuilder so i only replace a specific section of the string with something else
    // multiple times
    StringBuilder finalPhrase = new StringBuilder(findPhrase);
    // still need a string value for comparing, since string and stringbuilder isn't the same thing
    String strFinalPhrase = String.valueOf(finalPhrase);
    // keeps track of which plasyer
    who = 1;
    //String finalPhrase = findPhrase;
    System.out.println(finalPhrase);

    // loop that keeps running until the the whole phrase is guessed
    while(!strFinalPhrase.equals(phrase)){
      points = Spinner.randomNum();
      System.out.println("");
      if(who == 1){
        System.out.println("It is " + player.getName() + "'s turn.");
      }
      else{
        System.out.println("It is " + player2.getName() + "'s turn.");
      }
      System.out.println("\nPoints worth for each letter this round: " + points);
      // getLetter returns a valid, not-already-guessed, letter
      letter = getLetter(lettersGuessed, player, player2, correct);
      lettersGuessed += letter;
      // one function for printing the letters guessed correctly
      printLettersGuessed(lettersGuessed);
 
      // loops throught the actual phrase and checks when the letter in the phrase
      // is equal to the letter that the player guessed
      // then it replaces that spot in the empty stringbuiler with the actual letter
      for(int i = 0; i < phrase.length(); i++){
        if(phrase.substring(i,i+1).equals(letter)){
          finalPhrase = finalPhrase.replace(i, i+1, letter);
          if(who == 1){
            player.addToScore(points);
            correct = true;
            who = 1;
          }
          else{
            player2.addToScore(points);
            correct = false;
            who = 2;
          }
        }
      }      

      System.out.println("Current score for " + player.getName() + ": " + player.getScore());
      System.out.println("Current score for " + player2.getName() + ": " + player2.getScore());
      System.out.println("Current Phrase Solved: " + finalPhrase);

      // another checker for if the stringbuilder, which is a string here
      // is equal to the final phrase
      // breaks out of loop if there is nothing more to guess
      strFinalPhrase = String.valueOf(finalPhrase);
      if(strFinalPhrase.equals(phrase)){ 
        break;
      }

      if(who == 1 && correct == false){
        who = 2;
      }
      else if(who == 2 && correct == false){
        who = 1;
      }
      
    }
    
    // final print statmeents for end of game
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("Congratulations! You did it.");
    System.out.println(player.getName() + "'s final score is: " + player.getScore());
    System.out.println(player2.getName() + "'s final score is: " + player2.getScore());
    if(player.getScore() > player2.getScore()){
      System.out.println(player.getName() + " won!");
    }
    else if(player.getScore() < player2.getScore()){
      System.out.println(player2.getName() + " won!");
    }
    else{
      System.out.println("A tie!");
    }

  }

  // returns the actual phrase when asked
  public String getPhrase(){
    return phrase;
  }
  // creates the phrase, taken from the txt
  private String loadPhrase(){
    String tempPhrase = "";
    
    int numOfLines = 0;
    
    try{
      
      Scanner sc = new Scanner(new File("/workspace/phraseSolver/phrases.txt"));
      while (sc.hasNextLine()){
        tempPhrase = sc.nextLine().trim();
        numOfLines++;
      }
    } catch(Exception e) { System.out.println("Error reading or parsing phrases.txt"); }
    
    int randomInt = (int) ((Math.random() * numOfLines) + 1);
    
    try{
      int count = 0;
      Scanner sc = new Scanner(new File("/workspace/phraseSolver/phrases.txt"));
      while (sc.hasNextLine()){
        count++;
        String temp = sc.nextLine().trim();
        if (count == randomInt){
          tempPhrase = temp;
        }
      }
    } catch (Exception e) { System.out.println("Error reading or parsing phrases.txt"); }

  //int index = (int)(Math.random() * negAdjectives.size());
  //tempPhrase = negAdjectives.get(index);

  //System.out.println(tempPhrase);
  return tempPhrase;
  }

  // returns the empty number of lines string
  public String getFindPhrase(){
    return findPhrase;
  }

  // goes through actual phrase and makes another string that replaces all the lettes with 
  // the ◡ symbol
  private String emptyPhrase(String phrase){
    
    String ep = "";

    for(int i = 0; i < phrase.length(); i++){
      if(phrase.substring(i,i+1).equals(" ")){
        ep += " ";
      }
      else{
        ep += "◡";
      }
    }

    return ep;
  }

  //gets a valid letter from user and returns it
  public String getLetter(String lettersGuessed, Player player, Player player2, boolean correct){
    // variable initialization
    Scanner read = new Scanner(System.in);
    String all = "abcdefghijklmnopqrstuvwxyz";
    String letter = "";

    System.out.println("");

    if(who == 1){
      System.out.println("What letter would you like to guess " + player.getName() + "?");
    }
    else{
      System.out.println("What letter would you like to guess " + player2.getName() + "?");
    }
    System.out.println("(If you enter a letter you already used, or a something that isn't a letter, you will lose points.)");
    // keeps loopign through, switching between players, until a valid answer is given
    //(input is a letter in the alphabet)(letter has not already been guessed)
    while((all.indexOf(letter) == -1) || (lettersGuessed.indexOf(letter) != -1)){
      letter = read.nextLine().toLowerCase();
      if((all.indexOf(letter) == -1) || (lettersGuessed.indexOf(letter) != -1)){
        if(who == 1){
          player.subtractScore();
        }
        else{
          player2.subtractScore();
        } 
        correct = false;
      }
    }
    letter = letter.substring(0,1);

    return letter;
  }

  // a function that prints out the guessed letters in a good format
  // just didn't need to print this whole section multiple times
  public static void printLettersGuessed(String lettersGuessed){
    System.out.println("-------------------------------------------");
    System.out.print("Letters Guessed: ");
    for(int i = 0; i < lettersGuessed.length(); i++){
      System.out.print(lettersGuessed.substring(i,i+1));
      if(i < lettersGuessed.length()-1){
        System.out.print(", ");
      }
    }
    System.out.println("");
  }

}
