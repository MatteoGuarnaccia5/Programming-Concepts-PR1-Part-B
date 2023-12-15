import greenfoot.*;

public class DuringRoundTimer extends Actor {
    // Variables used in the timer
    int seconds;
    int timer;
    Boolean complete = false;
    
    public DuringRoundTimer(int seconds) {
        // Constructor translating arguments into atributes
        this.seconds = seconds;
        timer = 55 * seconds;
    }

    public void act() {
        // Checks if timer is exactly on a second of time, if it does displays different image
        timer--;
        if (timer % 55 == 0) {
            updateImage();
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
    
    public void updateImage() {
        // Updates the image when a second has passed
        setImage(new GreenfootImage("Time Left: "+timer/55, 20, Color.BLACK, Color.WHITE));
    }
}
