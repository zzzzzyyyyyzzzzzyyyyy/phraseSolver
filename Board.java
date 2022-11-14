import java.util.Scanner;
import java.io.File;
public class Board{

  private String phrase;
  private String findPhrase;
  private String lettersGuessed;

  public Board(Player player){
    phrase = loadPhrase();
    findPhrase = emptyPhrase(phrase);
    lettersGuessed = "";
    int points = 0;
    String letter = "";
    StringBuilder finalPhrase = new StringBuilder(findPhrase);
    String strFinalPhrase = String.valueOf(finalPhrase);
    //String finalPhrase = findPhrase;
    System.out.println(finalPhrase);

    while(!strFinalPhrase.equals(phrase)){
      //System.out.println(findPhrase);
      letter = getLetter(lettersGuessed, player);
      lettersGuessed += letter;
      printLettersGuessed(lettersGuessed);
      points = Spinner.randomNum();
      

      //if(phrase.indexOf(letter) == -1){
      //  lettersGuessed += letter;
      //}
      //else{
 
      for(int i = 0; i < phrase.length(); i++){
        if(phrase.substring(i,i+1).equals(letter)){
          finalPhrase = finalPhrase.replace(i, i+1, letter);
        }
      }
      //  lettersGuessed += letter;
      //}

      System.out.println("Current Phrase Solved: " + finalPhrase);

      strFinalPhrase = String.valueOf(finalPhrase);

      //System.out.println(strFinalPhrase.equals(phrase));
      //System.out.println("phrase: " + phrase + " , finalPhrase: " + finalPhrase);
      if(strFinalPhrase.equals(phrase)){ 
        break;
      }
      
    }
    
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("Congratulations! You did it. ok bye bye now.");

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
    
    //System.out.println(ep);
    return ep;
  }

  //gets a valid letter from user and returns it
  public String getLetter(String lettersGuessed, Player player){
    Scanner read = new Scanner(System.in);
    String all = "abcdefghijklmnopqrstuvwxyz";
    String letter = "";
    /* 
    System.out.println("-------------------------------------------");
    System.out.print("Letters Guessed: ");
    for(int i = 0; i < lettersGuessed.length(); i++){
      System.out.print(lettersGuessed.substring(i,i+1));
      if(i < lettersGuessed.length()-1){
        System.out.print(", ");
      }
    }
    */
    //printLettersGuessed(lettersGuessed);
    System.out.println("");
    //while((all.indexOf(letter) != -1) || (lettersGuessed.indexOf(letter) == -1)){ 
    System.out.println("What letter would you like to guess " + player.getName() + "?");
    while((all.indexOf(letter) == -1) || (lettersGuessed.indexOf(letter) != -1)){
      letter = read.nextLine().toLowerCase();
    }
    letter = letter.substring(0,1);
      //System.out.println(all.indexOf(letter));
      //System.out.println(all.indexOf(letter) == -1);
      
    //}
    return letter;
  }

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
