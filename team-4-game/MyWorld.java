import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public player main_player = new player(30, 30);
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1);
        addObject(main_player, getWidth()/3, getHeight()/3);
        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Enemy enemy = new Enemy(main_player);
        addObject(enemy,398,182);
        HealthBar playerHealthBar = new HealthBar();
        addObject(playerHealthBar, 55, 15);
    }
    
    public void gameOver(){
            removeObjects(getObjects(Actor.class));
            GreenfootImage bg = getBackground();
            GreenfootImage txtImg = new GreenfootImage("GAME\nOVER", 80, Color.WHITE, Color.BLACK);
            bg.drawImage(txtImg, (bg.getWidth()-txtImg.getWidth())/2, (bg.getHeight()-txtImg.getHeight())/2);
            Greenfoot.stop(); 
    }
    public void spawn_enemy(){}
}
