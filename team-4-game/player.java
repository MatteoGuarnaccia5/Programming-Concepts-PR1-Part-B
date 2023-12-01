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
    
    public int frame = 1;
    
    public GreenfootImage attackImage = new GreenfootImage("penguin-attackV2.png");
    public GreenfootImage image = getImage();
    public String[] walkingImages = {"penguin-walking1.png","penguin-walking2.png", "penguin-walking3.png"};
    public int currentImage = 0;
    /**
     * Act - do whatever the player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Integer width;
    Integer height;
    Integer speed = 3;
    int xPos;
    int yPos;
    
    public player(int width, int height)
    {
        this.width = width;
        this.height = height;
        
        image.scale(35,50);
        attackImage.scale(50,35);
        setImage(image);
    }
    
    public void addedToWorld()
    {
        xPos = getX();
        yPos = getY();
    }
    
    public void act()
    {
        move();
        attack(2.5);//actual attack damage is double the number entered. Current enemy health is 10
        animateWalking();
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
    
    public void animateWalking(){
        if(getX()!=xPos || getY()!=yPos)
        {
            currentImage ++;
            if(currentImage >= walkingImages.length)
            {
            currentImage = 0;
            }
        
            GreenfootImage newImage = new GreenfootImage(walkingImages[currentImage]);
            newImage.scale(35,50);
        
            if(getX()<xPos)
            {
                newImage.mirrorHorizontally();
            }
        setImage(newImage);
        }
        
        xPos = getX();
        yPos = getY();
    }
    
    
    public void attack(double damage)
    {
        if(Greenfoot.isKeyDown("space"))
        {
            setImage(attackImage);
            Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);
            if(enemy != null)
            {
                enemy.health -=damage;
                
            }
            Greenfoot.delay(4);
        }
        setImage(image);
    }
}
