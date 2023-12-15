import greenfoot.*;

public class intro extends Actor {
    
    public Boolean isDone = false;
    
    public intro() {
        // Sets and resizes image
        setImage("Intro.png");
        GreenfootImage intro = getImage();
        intro.scale(1000, 600);
    }
    
    public void act() {
        // Checks if enter key has been pressed
        if (Greenfoot.isKeyDown("enter")) {
            isDone = true;
        }
    }
}
