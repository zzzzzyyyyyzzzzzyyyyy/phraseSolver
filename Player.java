public class Player {
    private String name;
    private int score;

    public Player(String n, int s){
        name = n;
        score = s;
    }

    public String getName(){
        return name;
    }

    // gets the score to print out
    public int getScore(){
        return score;
    }
    // adds the random value (that initially comes from spinner) to the player's current points
    public int addToScore(int add){
        return score += add;
    }
    // subtracts 100 points for every dumb input
    public int subtractScore(){
        return score -= 100;
    }

}
