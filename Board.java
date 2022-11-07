import java.util.Scanner;
import java.io.File;
public class Board{

  private String phrase;
  private String findPhrase;

  public Board(){
    phrase = loadPhrase();
    findPhrase = emptyPhrase(phrase);
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

  System.out.println(tempPhrase);
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
    
    System.out.println(ep);
    return ep;
  }



}
