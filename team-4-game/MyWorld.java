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
    // spawn constants needed
    int count = 1;
    int spawn_speed = 50;
    int spawn_cap = 10;
    int random_spawn;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1);
        addObject(main_player, getWidth()/3, getHeight()/3);
        HealthBar playerHealthBar = new HealthBar();
        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        //Enemy enemy = new Enemy(main_player);
        //addObject(enemy,398,182);
        //player player = new player(30, 30);
        //addObject(player,137,187);
        HealthBar playerHealthBar = new HealthBar();
        addObject(playerHealthBar, 55, 15);
    }
    public void act()
    {
        count++;
        if (count < spawn_cap*spawn_speed){
            spawn_enemy();
        }
    }
    public void gameOver(){
            removeObjects(getObjects(Actor.class));
            GreenfootImage bg = getBackground();
            GreenfootImage txtImg = new GreenfootImage("GAME\nOVER", 80, Color.WHITE, Color.BLACK);
            bg.drawImage(txtImg, (bg.getWidth()-txtImg.getWidth())/2, (bg.getHeight()-txtImg.getHeight())/2);
            Greenfoot.stop(); 
    }
    public void spawn_enemy(){
        if(count % spawn_speed == 0)
        {
            random_spawn = Greenfoot.getRandomNumber(8);
            switch(random_spawn){
                case 0 : addObject(new Enemy(main_player), 0, 0); break;
                case 1 : addObject(new Enemy(main_player), getWidth()/2, 0); break;
                case 2 : addObject(new Enemy(main_player), getWidth(), 0); break;
                case 3 : addObject(new Enemy(main_player), getWidth(), getHeight()/2); break;
                case 4 : addObject(new Enemy(main_player), getWidth(), getHeight() ); break;
                case 5 : addObject(new Enemy(main_player), getWidth()/2, getHeight()); break;
                case 6 : addObject(new Enemy(main_player), 0, getHeight()); break;
                case 7 : addObject(new Enemy(main_player), 0, getHeight()/2); break;
            }
        }
    }
}
