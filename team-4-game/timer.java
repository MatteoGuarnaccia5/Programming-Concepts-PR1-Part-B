import greenfoot.*;

public class timer extends Actor {
    // Variables for timer to function
    int seconds;
    int timer;
    Boolean complete = false;
    
    public timer(int seconds) {
        // Converts argument into attribute
        this.seconds = seconds;
        timer = 55 * seconds;
    }

    public void act() {
        //If timer has reached a new second, update the timer
        timer--;
        if (timer % 55 == 0) {
            updateImage();
        }
        
        // If timer has ended, mark as complete
        if (timer < (-1)) {
            getWorld().removeObjects(getWorld().getObjects(timer.class));
            this.complete = true;
        }
    }
    
    public Boolean checkDone() {
        // Checks whether the timer has been completed
        return this.complete;
    }
    
    public void updateImage() {
        // Updates the image depending on how many seconds before the start of the game
        setImage(new GreenfootImage("Time Before Start: "+timer/55, 50, Color.BLACK, Color.WHITE));
    }
}
