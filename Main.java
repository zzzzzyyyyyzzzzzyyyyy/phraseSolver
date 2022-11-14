import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        //Board board = new Board();
        //board.loadPhrase();

        Scanner read = new Scanner(System.in);

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Welcome to this word game. Guess the phrase when given empty spaces.");
        System.out.println("But first, what is your name?");

        String name = read.nextLine();

        System.out.println("Yay, ok " + name + ". Let us begin the game.");
        System.out.println("-------------------------------------------");

        Player player = new Player(name, 0);
        Board board = new Board(player);
    }
}
