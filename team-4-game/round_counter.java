import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class round_counter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class round_counter extends Actor {
    int round;
    
    public round_counter(int round) {
        setImage(new GreenfootImage("Current Round: "+round, 20, Color.BLACK, Color.WHITE));
    }
    
    public void act() {
    }
    
    public void update_round(int round) {
        setImage(new GreenfootImage("Current Round: "+round, 20, Color.BLACK, Color.WHITE));
    }
}
