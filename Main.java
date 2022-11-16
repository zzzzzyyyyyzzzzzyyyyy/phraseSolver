import java.util.Scanner;

public class Main {
    // Introduction
    public static void main(String[] args){
        
        // clears screen
        System.out.print("\033[H\033[2J");  
        System.out.flush();  

        // thing for reading user input
        Scanner read = new Scanner(System.in);

        // introduces game and asks for player's name to use in future
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Welcome to this word game. Guess the phrase when given empty spaces.");
        System.out.println("But first, what is your name, player 1?");

        String name = read.nextLine();
        Player player = new Player(name, 0);

        System.out.println("What if you name, player 2?");

        name = read.nextLine();
        Player player2 = new Player(name, 0);

        System.out.println("Yay, ok " + player.getName() + " and " + player2.getName() + ". Let us begin the game.");
        System.out.println("-------------------------------------------");

        // where nearly all the game logic is
        Board board = new Board(player, player2);

        System.out.println("\nWould you like to replay? (yes/any character,y/any character)");
        String replay = read.nextLine();
        while(replay.equals("yes") || replay.equals("y")){
            board = new Board(player, player2);
            System.out.println("\nWould you like to replay? (yes/any character,y/any character)");
            replay = read.nextLine();
        }

        //System.exit(0);

    }
}
