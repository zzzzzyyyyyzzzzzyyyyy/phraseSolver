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
    /* 
    public void setname(String n){
        name = n;
    }
    */

    public int getScore(){
        return score;
    }
    public int addToScore(int add){
        return score += add;
    }

}
