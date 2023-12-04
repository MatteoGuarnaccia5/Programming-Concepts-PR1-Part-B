import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A timer used between rounds, to ready the user before the next
 * 
 * @author Matteo Guarnaccia, William Brown, Yufan Kambang
 * @version 02/12/2023
 */
public class BeforeRoundTimer extends Actor {
    // Variables used in the timer
    int seconds;
    int timer;
    int round;
    Boolean complete = false;
    
    public BeforeRoundTimer(int seconds, int round) {
        // Constructor translating arguments into atributes
        this.seconds = seconds;
        this.round = round;
        timer = 55 * seconds;

    }

    public void act() {
        // Checks if timer is exactly on a second of time, if it does displays different image
        timer--;
        if (timer % 55 == 0) {
            updateImage(round);
        }
        
        // Checks if timer has finished
        if (timer < (-1)) {
            getWorld().removeObjects(getWorld().getObjects(BeforeRoundTimer.class));
            this.complete = true;
        }
    }
    
    public Boolean checkDone() {
        // Checks if the timer has finished
        return this.complete;
    }
    
    public void updateImage(int round) {
        // Updates the image when a second has passed
        setImage(new GreenfootImage("Time Before Round "+round+": "+timer/55, 50, Color.BLACK, Color.WHITE));
    }
}