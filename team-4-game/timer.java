import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class timer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class timer extends Actor
{
    /**
     * Act - do whatever the timer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int seconds;
    int timer;
    Boolean complete = false;
    
    public timer(int seconds) {
        this.seconds = seconds;
        timer = 55 * seconds;

    }

    public void act()
    {
        timer--;
        if (timer % 55 == 0) {
            updateImage();
        }
        if (timer < (-1)) {
            getWorld().removeObjects(getWorld().getObjects(timer.class));
            this.complete = true;
        }
    }
    
    public Boolean checkDone() {
        return this.complete;
    }
    
    public void updateImage() {
        setImage(new GreenfootImage("Time Before Start: "+timer/55, 50, Color.BLACK, Color.WHITE));
    }
}
