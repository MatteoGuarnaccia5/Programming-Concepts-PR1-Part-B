import greenfoot.*;

public class Bullet extends Actor {
    public GreenfootImage image = getImage();
    int direction = 3;
    
    public Bullet(int bulletSpeed) {
        // Resizes image
        image.scale(15,15);
        direction = bulletSpeed;
    }
    
    public void act() {
        move(direction);
        damage();
    }
    
    public void damage(){
        Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);
        if(enemy != null) {
            enemy.health -= 10;
        }

        enemy_2 enemy_2 = (enemy_2) getOneIntersectingObject(enemy_2.class);
        if (enemy_2 != null) {
            enemy_2.health -= 10;
        }
        
        if(enemy != null || enemy_2 != null || isAtEdge()){
            getWorld().removeObject(this);
        }
        
    }
    
    public void move(int distance)
    {
        int x = getX();
        int y = getY();
        
        x += distance;
       
        setLocation(x, y);
    }
}
