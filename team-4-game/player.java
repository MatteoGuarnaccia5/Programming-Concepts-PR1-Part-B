import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A player which can move, kill enemies and also take damage. It can also pick up items.
 * 
 * @author Matteo Guarnaccia, William Brown, Yufan Kambang
 * @version 02/12/2023
 */
public class player extends Actor {
    // Variables for the player
    private int playerHealth = 100;
    private int playerShield = 0;
    public int frame = 1;
    
    // Images for the penguin
    public GreenfootImage attackImage = new GreenfootImage("penguin-attackV2.png");
    public GreenfootImage image = getImage();
    public String[] walkingImages = {"penguin-walking1.png","penguin-walking2.png", "penguin-walking3.png"};
    public int currentImage = 0;
    
    // Variables for the player size
    Integer width;
    Integer height;
    Integer speed = 3;
    
    // Keeping track of the player position
    int xPos;
    int yPos;
    
    public player(int width, int height) {
        // Converts arguments into attributes
        this.width = width;
        this.height = height;
        
        // Resizes image
        image.scale(35,50);
        attackImage.scale(50,35);
        setImage(image);
    }
    
    public void addedToWorld() {
        // Retrieves current positioning of the player
        xPos = getX();
        yPos = getY();
    }
    
    public void act() {
        // All of the methods which enable the player to function
        move();
        attack(2.5);
        animateWalking();
        regenHealth();
    }
    
    public void move() {
        // Finds x and y positions
        int x = getX();
        int y = getY();
        int halfWidth = width / 2;
        
        // Checks if keys are being pressed by the user, and moves if relevant        
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
    
    public void animateWalking() {
        // Uses different image to enable the player to appear as if it is walking
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
    
    
    public void attack(double damage) {
        // Checks if space bar is pressed, if so does damage to an enemy
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
    
    public void regenHealth() {
        // Regenerates health if the player collects an item
        ItemHealth healthRegen = (ItemHealth) getOneIntersectingObject(ItemHealth.class);
        if(healthRegen != null)
        {
            MyWorld myWorld = (MyWorld) getWorld();
            HealthBar health = myWorld.getObjects(HealthBar.class).get(0);
            health.health += 10;
            getWorld().removeObject(healthRegen);
        }
    }
}
