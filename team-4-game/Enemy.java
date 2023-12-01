import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    
    int health = 10;
    public boolean currentlyDamaging; 
    long now;
    long now2;
    public GreenfootImage enemyImage = getImage();
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    player player; 
    public Enemy(player main_player)
    {
        enemyImage.scale(40,40);
        player = main_player;
        currentlyDamaging = false;
        long now = new Date().getTime();
    }
    public void act()
    {
        // Add your action code here.
        //move_around();
        follow();
        long now2 = new Date().getTime();
        
        if(!this.equals(null) && !currentlyDamaging && (now2 > now +10000)){
            dealDamage();
            currentlyDamaging = false;
        }
        
        if(health <=0){
            getWorld().removeObject(this);
        }
        
    }
    public void move_around()
    {
        move(1);
        turnTowards(getWorld().getWidth()/2, getWorld().getHeight()/2);
    }
    public void follow()
    {
        move(2);
        turnTowards(player.getX(), player.getY());
    }
    
    public void dealDamage(){
        player player2 = (player) getOneIntersectingObject(player.class);
        if(getOneObjectAtOffset(0, 0, player.class) != null)
        {
            //add animation at a later date
            currentlyDamaging = true;
            MyWorld myWorld = (MyWorld) getWorld();
            HealthBar health = myWorld.getObjects(HealthBar.class).get(0);
            health.hitByEnemy();
            Greenfoot.delay(1);
            long now = new Date().getTime();
        }
    }
    
}   
