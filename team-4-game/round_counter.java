import greenfoot.*;

public class round_counter extends Actor {
    // Initialises variable to store current round
    int round;
    
    public round_counter(int round) {
        setImage(new GreenfootImage("Current Round: "+round, 20, Color.BLACK, Color.PINK));
    }
    
    public void update_round(int round) {
        setImage(new GreenfootImage("Current Round: "+round, 20, Color.BLACK, Color.PINK));
    }
}
