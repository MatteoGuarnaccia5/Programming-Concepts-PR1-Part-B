import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * An enemy (seahorse) which attacks the player.
 * 
 * @author Matteo Guarnaccia, William Brown, Yufan Kambang
 * @version 02/12/2023
 */
public class Enemy extends Actor {
    // Variables for the enemy 
    int health = 10;
    public boolean currentlyDamaging; 
    long now;
    long now2;
    public GreenfootImage enemyImage = getImage();
    public GreenfootImage playerDamageImage = new GreenfootImage("penguin-take-damage.png");
    player player; 

    public Enemy(player main_player)
    {
        // Scales the images of the seahorse
        enemyImage.scale(40,40);
        playerDamageImage.scale(35,50);
        player = main_player;
        currentlyDamaging = false;
        long now = new Date().getTime();
    }

    public void act()
    {
        // Instructs enemy to follow the player
        follow();
        long now2 = new Date().getTime();
        
        // Checks if damage should be dealt to user
        if(!this.equals(null) && !currentlyDamaging && (now2 > now +10000)){
            dealDamage();
            currentlyDamaging = false;
        }
        
        // Checks if enemy has no health left
        if(health <=0){
            getWorld().removeObject(this);
        }

    }

    public void follow()
    {
        // Instructs enemy to follow the player
        move(2);
        turnTowards(player.getX(), player.getY());
    }

    public void dealDamage(){
        // Checks if the player is near a seahorse, and hence whether damage should be dealt
        player player2 = (player) getOneIntersectingObject(player.class);
        if(getOneObjectAtOffset(0, 0, player.class) != null)
        {
            //add animation at a later date
            currentlyDamaging = true;
            MyWorld myWorld = (MyWorld) getWorld();
            HealthBar health = myWorld.getObjects(HealthBar.class).get(0);
            health.hitByEnemy();
            player.setImage(playerDamageImage);
            Greenfoot.delay(1);
            long now = new Date().getTime();
        }
    }
}   