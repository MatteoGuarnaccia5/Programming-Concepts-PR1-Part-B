import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class player extends Actor
{
    private int playerHealth = 100;
    private int playerShield = 0;
    /**
     * Act - do whatever the player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Integer width;
    Integer height;
    Integer speed = 2;
    
    public player(int width, int height)
    {
        this.width = width;
        this.height = height;
        GreenfootImage image = getImage();
        image.scale(35,50);
        setImage(image);
    }
    
    public void act()
    {
        move();
    }
    
    public void move()
    {
        int x = getX();
        int y = getY();
        int halfWidth = width / 2;
        
        if (Greenfoot.isKeyDown("down") && !isAtEdge())
        {
            y += speed;
        }
        
        if (Greenfoot.isKeyDown("up") && y > speed)
        {
            y -= speed;
        }
        
        if (Greenfoot.isKeyDown("right") && !isAtEdge())
        {
            x += speed;
        }
        
        if (Greenfoot.isKeyDown("left") && x > speed)
        {
            x -= speed;
        }
        setLocation(x, y);
    }
}
