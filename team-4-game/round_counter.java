import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Displays the round the user is currently playing.
 * 
 * Matteo Guarnaccia, William Brown, Yufan Kambang
 * 11/12/2023
 */

public class round_counter extends Actor {
    int round;
    
    public round_counter(int round) {
        setImage(new GreenfootImage("Current Round: "+round, 20, Color.BLACK, Color.WHITE));
    }
    
    public void update_round(int round) {
        setImage(new GreenfootImage("Current Round: "+round, 20, Color.BLACK, Color.WHITE));
    }
}
