import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    
    int health = 10;
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        //this will need to be changed to be varibale depending on level
        //also need to be dependent on 'attack' from player not key press
        if(Greenfoot.isKeyDown("Q")){
            health -= 5;
        }
        if(health <=0){
            getWorld().removeObject(this);
        }
    }
    
    
}
