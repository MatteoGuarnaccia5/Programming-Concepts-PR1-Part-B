import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
//import java.*

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    public player main_player = new player(30, 30);
    // spawn constants needed
    int count = 1;
    int spawn_speed = 50;
    int spawn_cap = 10;
    int random_spawn;
    timer timer;
    Boolean startGame = false;
    public MyWorld()
    {    
        super(1000, 600, 1);
        prepare();
        timer = new timer(5);
        addObject(timer, 500, 300);
    }
    
    private void prepare()
    {
        HealthBar playerHealthBar = new HealthBar();
        addObject(playerHealthBar, 55, 15);
    }
    
    public void createPlayer() {
        addObject(main_player, 300, 300);
    }
    
    public void act() {
        if (startGame == false) {
            if (timer.checkDone() == true) {
                createPlayer();
                startGame = true;
            }
        } else {
            spawnHealthItem();
            count++;
            if (count < spawn_cap*spawn_speed){
                spawn_enemy();
            }
        }
    }
        
    public void gameOver(){
        removeObjects(getObjects(Actor.class));
        GreenfootImage bg = getBackground();
        GreenfootImage txtImg = new GreenfootImage("GAME\nOVER", 80, Color.WHITE, Color.BLACK);
        bg.drawImage(txtImg, (bg.getWidth()-txtImg.getWidth())/2, (bg.getHeight()-txtImg.getHeight())/2);
        Greenfoot.stop(); 
    }
    
    
public void spawnHealthItem()
{
        if(Greenfoot.getRandomNumber(200) == 1)
        {
     
        int x = Greenfoot.getRandomNumber(1000);
        int y = Greenfoot.getRandomNumber(600);
     
        addObject(new ItemHealth(), x, y);
        }
}
public void spawn_enemy()
{
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
