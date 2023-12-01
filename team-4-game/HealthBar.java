import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends Actor
{
    int health = 100;
    int healthBarWidth = 100;
    int healthBarHeight = 20;
    int pixelsPerHealthPoint = (int)healthBarWidth/health;
    
    /**
     * Act - do whatever the HealthBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public HealthBar(){
        update();
    }
    public void act()
    {
        // Add your action code here.
        update();
        
        //this will need to be changed to when a player is hit. keep health decrease the same
        
    }
    
    public void update(){
        setImage(new GreenfootImage(healthBarWidth + 2, healthBarHeight +2));
        GreenfootImage myImage = getImage();
        myImage.setColor(Color.BLACK);
        myImage.drawRect(0,0,healthBarWidth + 1,healthBarHeight + 1);
        myImage.setColor(Color.GREEN);
        myImage.fillRect(1,1 , health *pixelsPerHealthPoint, healthBarHeight);
        
        if(health <= 0){
            ((MyWorld)getWorld()).gameOver();
        }
    }
    
    public void hitByEnemy()
    {
       health -= 1.5;
    }
}
