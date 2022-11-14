public class Spinner {
    public static int randomNum(){
        // this function is just method to reuturn a random nubmer for the points
        // the player will earn per each correct letter.
        //                           (max - min)+ min
        return (int)(Math.random() * (750 - 50) + 50);
    }
}
