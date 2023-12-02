import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
//import java.*

/**
 * The world in which the game is set. Spawns utems, healthbar, a player and enemies and what happens when game is finished.
 * 
 * @author Matteo Guarnaccia, William Brown, Yufan Kambang
 * @version 02/12/2023
 */
public class MyWorld extends World {
    // Creates player and its healthbar
    public player main_player = new player(30, 30);
    HealthBar playerHealthBar = new HealthBar();

    // Spawn variables needed
    int count = 1;
    int spawn_speed = 50;
    int spawn_cap = 6;
    int random_spawn;
    int round = 1;
    
    // Creating timers before games start and before rounds
    timer timer;
    BeforeRoundTimer beforeRoundTimer;
    
    // Variables to identify what to do during act method
    Boolean startGame = false;
    Boolean startRound = false;
    Boolean spawnDone = false;
    
    public MyWorld() {    
        // Creates an area for the game
        super(1000, 600, 1);
        
        // Creating and displaying timer for game start
        timer = new timer(5);
        addObject(timer, 500, 300);
    }
    
    public void createPlayer() {
        // Displays player and healthbar
        addObject(main_player, 300, 300);
        addObject(playerHealthBar, 55, 15);
    }
    
    public void act() {
        // Checks if game has been started, and if timer has finished
        if (startGame == false) {
            if (timer.checkDone() == true) {
                createPlayer();
                startGame = true;
                startRound = true;
            }
        
        // Otherwise, checks if game has not yet been started
        } else {
            
            // If round needs to be started, variables need to be changed
            if (startRound == true) {
                if (round == 1) {
                    startRound = false;
                    spawnDone = false;
                }
                else if (beforeRoundTimer.checkDone() == true) {
                    startRound = false;
                    spawnDone = false;
                }
            }
            
            // Spawns enemies if needed
            else if (spawnDone == false) {
                doRound();
            }
            
            // Checks if all enemies have been killed, if so ends the round
            else if (getObjects(Enemy.class).size() == 0) {
                incrementStats();
                beforeRoundTimer = new BeforeRoundTimer(5, round);
                addObject(beforeRoundTimer, 500, 300);
                startRound = true;
            }
        }
    }
    
    public void incrementStats() {
        // Changes the spawn variables as round has been completed
        round += 1;                
        count = 1;
        spawn_cap += 1;
        spawn_speed += 1;
    }
    
    public void doRound() {
        // Spawns items
        spawnHealthItem();
        
        // Checks if enough enemies have been spawned, if not then spawns enemy
        if (count <= spawn_cap * spawn_speed){
            spawn_enemy();
            count++;
        }
        else {
            spawnDone = true;
        }
    }
    
    public void gameOver(){
        // Called once healthbar is 0, dispays game over screen and how many rounds the user completed
        removeObjects(getObjects(Actor.class));
        GreenfootImage bg = getBackground();
        GreenfootImage txtImg = new GreenfootImage("GAME OVER", 80, Color.WHITE, Color.BLACK);
        bg.drawImage(txtImg, (bg.getWidth()-txtImg.getWidth())/2, (bg.getHeight()-txtImg.getHeight()-200)/2);
        GreenfootImage roundsComp = new GreenfootImage("You completed "+(round-1)+" rounds!", 80, Color.WHITE, Color.BLACK);
        bg.drawImage(roundsComp, (bg.getWidth()-roundsComp.getWidth())/2, (bg.getHeight()-roundsComp.getHeight()+200)/2);
        Greenfoot.stop(); 
    }
    
    public void spawnHealthItem() {
        // Randomly spawns in a health item
        if(Greenfoot.getRandomNumber(200) == 1) {
            int x = Greenfoot.getRandomNumber(1000);
            int y = Greenfoot.getRandomNumber(600);
            addObject(new ItemHealth(), x, y);
        }
    }
    
    public void spawn_enemy() {
        // Checks if enemy needs to be spawned, enemy spawns in random location
        if(count % spawn_speed == 0) {
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
