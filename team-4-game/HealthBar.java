import greenfoot.*;

public class HealthBar extends Actor {
    // Variables for the health bar
    int health = 100;
    int healthBarWidth = 100;
    int healthBarHeight = 20;
    int pixelsPerHealthPoint = (int)healthBarWidth/health;
    
    public HealthBar() {
        update();
    }
    
    public void act() {
        update();
    }
    
    public void update() {
        // Checks the current health for the player, and changes the healthbar accordingly
        setImage(new GreenfootImage(healthBarWidth + 2, healthBarHeight +2));
        GreenfootImage myImage = getImage();
        myImage.setColor(Color.BLACK);
        myImage.drawRect(0,0,healthBarWidth + 1,healthBarHeight + 1);
        myImage.setColor(Color.GREEN);
        myImage.fillRect(1,1 , health *pixelsPerHealthPoint, healthBarHeight);
        
        // Checks if health is 0
        if(health <= 0) {
            ((MyWorld)getWorld()).gameOver();
        }
    }
    
    public void hitByEnemy() {
        // If damage is done, take away some health 
        health -= 1.5;
    }
}
