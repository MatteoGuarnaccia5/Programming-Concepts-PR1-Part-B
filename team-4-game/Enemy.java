import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class Enemy extends Actor {
    // Variables for the enemy 
    int health = 10;
    public boolean currentlyDamaging; 
    long now;
    long now2;
    
    // Initialise images
    public GreenfootImage enemyImage = getImage();
    public GreenfootImage playerDamageImage = new GreenfootImage("penguin-take-damage.png");
    player player; 
    
    // Initialise sounds needed
    public GreenfootSound damageSound = new GreenfootSound("take-damage.mp3");
    public GreenfootSound deathSound = new GreenfootSound("enemy-death.mp3");

    public Enemy(player main_player)
    {
        // Scales the images of the seahorse
        enemyImage.scale(40,40);
        playerDamageImage.scale(35,50);
        player = main_player;
        
        // Sets currentlyDamaging to false, and collects current time
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
            deathSound.play();
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
        if(getOneObjectAtOffset(0, 0, player.class) != null) {
            currentlyDamaging = true;
            
            // Plays the sound for damage
            damageSound.play();
            
            // Retrieves and changes player health
            MyWorld myWorld = (MyWorld) getWorld();
            HealthBar health = myWorld.getObjects(HealthBar.class).get(0);
            health.hitByEnemy();
            player.setImage(playerDamageImage);
            Greenfoot.delay(1);
            long now = new Date().getTime();
        }
    }
}   