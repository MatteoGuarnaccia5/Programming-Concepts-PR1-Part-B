import greenfoot.*;

public class Bullet extends Actor {
    // Creates image for bullet, initialise direction variable
    public GreenfootImage image = getImage();
    int direction = 3;
    
    public Bullet(int bulletSpeed) {
        // Resizes image
        image.scale(15,15);
        direction = bulletSpeed;
    }
    
    public void act() {
        // Moves in the direction that the player is pointing towards
        move(direction);
        
        // Checks if damage should be dealt
        damage();
    }
    
    public void damage() {
        // Checks whether bullet is in contact with a seahorse
        Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);
        if(enemy != null) {
            enemy.health -= 10;
        }

        // Checks whether bullet is in contact with a polar bear
        enemy_2 enemy_2 = (enemy_2) getOneIntersectingObject(enemy_2.class);
        if (enemy_2 != null) {
            enemy_2.health -= 10;
        }
        
        // Checks whether bullet is at edge of world, and if so removes itself
        if(enemy != null || enemy_2 != null || isAtEdge()){
            getWorld().removeObject(this);
        }
    }
    
    public void move(int distance) {
        // Retrieves X and Y positions, and changes position
        int x = getX();
        int y = getY();
        x += distance;
        setLocation(x, y);
    }
}
