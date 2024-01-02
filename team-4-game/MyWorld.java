import greenfoot.*;
import java.util.*;

public class MyWorld extends World {
    // Creates player and its healthbar
    public player main_player = new player(30, 30);
    HealthBar playerHealthBar = new HealthBar();
    round_counter round_counter = new round_counter(1);
    pb_label pb_label;

    // Spawn variables needed
    int count = 1;
    int round = 1;

    final int spawn_speed = 50;
    int spawn_cap = 3;
    int random_spawn;

    int spawn_cap_pb = 1;
    
    // Initialising variables for enemies to spawn
    Enemy enemy;
    enemy_2 enemy_2;
    
    // Initiate variables for the music and sounds
    GreenfootSound backgroundMusic = new GreenfootSound("background-music.mp3");
    GreenfootSound gameOverSound = new GreenfootSound("game-over.mp3");
       
    // Creating timers before games start and before rounds
    timer timer;
    BeforeRoundTimer beforeRoundTimer;
    DuringRoundTimer duringRoundTimer;
    intro intro;
    
    // Variables to identify what to do during act method
    Boolean startGame = false;
    Boolean startRound = false;
    Boolean spawnDone = false;
    int weaponCounter = 0;
    
    public MyWorld() {    
        // Creates an area for the game
        super(1000, 600, 1);
        
        // Begins the background music
        backgroundMusic.setVolume(10);
        backgroundMusic.playLoop();
        
        // Create and show the intro screen
        intro = new intro();
        addObject(intro, 500, 300);
    }
    
    public void createPlayer() {
        // Displays player, healthbar and a counter which shows the current round
        addObject(main_player, 500, 300);
        addObject(playerHealthBar, 55, 15);
        addObject(round_counter, 62, 590);
    }
    
    public void act() {
        // Checks if game has been started, and if timer has finished
        if (startGame == false) {
            if (intro.isDone == true) {
                // Removes intro if user presses the enter key
                removeObjects(getObjects(intro.class));
                
                // Checks if timer has been created, if not creates a timer
                if (timer == null) {
                    timer = new timer(5);
                    addObject(timer, 500, 300);
                }
                
                // Checks if timer has completed, if so begins the game
                if (timer.checkDone() == true) {
                    createPlayer();
                    startGame = true;
                    startRound = true;
                }
            }

        // Otherwise, checks if game has not yet been started
        } else {
            // If round needs to be started, variables need to be changed
            if (startRound == true) {
                if ((round == 1) || (beforeRoundTimer.checkDone())) {
                    startRound = false;
                    spawnDone = false;
                }
                
                // Add the timer to display during the round
                duringRoundTimer = new DuringRoundTimer(60 + round);
                addObject(duringRoundTimer, 953, 590);
            }
            
            // Spawns enemies if needed
            else if (spawnDone == false) {
                if (round % 5 == 0) {
                    specialRound();
                }
                else {
                    doRound();
                }
            }
            // Checks if all enemies have been killed, if so ends the round
            else {
                // If it is a special polar bear round, check if all enemies have been killed
                if (round % 5 == 0) {
                    if (getObjects(enemy_2.class).size() == 0) {
                        // Change game variables, add timer for next round
                        incrementStats(true);
                        beforeRoundTimer = new BeforeRoundTimer(5, round);
                        addObject(beforeRoundTimer, 500, 300);
                        
                        // Ensures the player and health items appear over the timer
                        setPaintOrder(player.class, ItemHealth.class, BeforeRoundTimer.class);
                        startRound = true;
                        
                        // Remove the timer and the label showing it is a polar bear round
                        removeObjects(getObjects(DuringRoundTimer.class));
                        removeObjects(getObjects(pb_label.class));
                    }
                    else if (duringRoundTimer.checkDone() == true) {
                        // If timer runs out, player loses and game is over
                        gameOver();
                    }
                }
                else {
                    if (getObjects(Enemy.class).size() == 0) {
                        // Change game variables, add timer for next round
                        incrementStats(false);
                        beforeRoundTimer = new BeforeRoundTimer(5, round);
                        addObject(beforeRoundTimer, 500, 300);
                        
                        // Ensures the player and health items appear over the timer
                        setPaintOrder(player.class, ItemHealth.class, BeforeRoundTimer.class);
                        startRound = true;
                        removeObjects(getObjects(DuringRoundTimer.class));
                        
                        // Add a label to show that it is a polar bear level
                        if (round % 5 == 0) {
                            pb_label = new pb_label();
                            addObject(pb_label, 500, 10);
                        }
                    }
                    else if (duringRoundTimer.checkDone() == true) {
                        // If timer runs out, player loses and game is over
                        gameOver();
                    }
                }
                // Increment the round counter by one
                round_counter.update_round(round);
            }
        }
    }
    
    public void incrementStats(Boolean polar_bear) {
        // Changes the spawn variables as round has been completed
        round++;                
        count = 1;
        
        if (polar_bear == true) {
            spawn_cap_pb ++;
        }
        else {
            spawn_cap ++;
        }
    }
        
    public void specialRound() {
        // Increases liklihood of a health item spawning
        for (int i = 0; i < 4; i++) {
            spawnHealthItem();
        }
        
        // Spawns enemy if there hasn't been enough spawned yet
        if (count <= spawn_cap_pb * spawn_speed) {
            spawn_enemy_2();
            count++;
        } else {
            spawnDone = true;
        }
    }
    
    public void doRound() {
        // Spawns items
        spawnHealthItem();
        spawnWeaponItem();
        
        // Checks if enough enemies have been spawned, if not then spawns enemy
        if (count <= spawn_cap * spawn_speed) {
            spawn_enemy();
            count++;
        }
        else {
            spawnDone = true;
        }
    }
    
    public void spawnHealthItem() {
        // Randomly spawns in a health item
        if(Greenfoot.getRandomNumber(150) == 1) {
            int x = Greenfoot.getRandomNumber(1000);
            int y = Greenfoot.getRandomNumber(600);
            addObject(new ItemHealth(), x, y);
        }
    }
    
    public void spawnWeaponItem(){
        // Randomly spawns in a weapon item
        if(Greenfoot.getRandomNumber(200) == 1 && weaponCounter == 0){
            int x = Greenfoot.getRandomNumber(1000);
            int y = Greenfoot.getRandomNumber(600);
            addObject(new ItemWeapon(), x, y);
            weaponCounter = 1;
        }
    }
    
    public void spawn_enemy() {
        // Checks if enemy needs to be spawned, enemy spawns in random location
        if(count % spawn_speed == 0) {
            random_spawn = Greenfoot.getRandomNumber(8);
            enemy = new Enemy(main_player);
            switch(random_spawn) {
                case 0 : addObject(enemy, 0, 0); break;
                case 1 : addObject(enemy, getWidth()/2, 0); break;
                case 2 : addObject(enemy, getWidth(), 0); break;
                case 3 : addObject(enemy, getWidth(), getHeight()/2); break;
                case 4 : addObject(enemy, getWidth(), getHeight() ); break;
                case 5 : addObject(enemy, getWidth()/2, getHeight()); break;
                case 6 : addObject(enemy, 0, getHeight()); break;
                case 7 : addObject(enemy, 0, getHeight()/2); break;
            }
        }
    }
    
    public void spawn_enemy_2() {
        // Checks if enemy needs to be spawned, enemy spawns in random location
        if(count % spawn_speed == 0) {
            random_spawn = Greenfoot.getRandomNumber(8);
            enemy_2 = new enemy_2(main_player);
            switch(random_spawn){
                case 0 : addObject(enemy_2, 0, 0); break;
                case 1 : addObject(enemy_2, getWidth()/2, 0); break;
                case 2 : addObject(enemy_2, getWidth(), 0); break;
                case 3 : addObject(enemy_2, getWidth(), getHeight()/2); break;
                case 4 : addObject(enemy_2, getWidth(), getHeight() ); break;
                case 5 : addObject(enemy_2, getWidth()/2, getHeight()); break;
                case 6 : addObject(enemy_2, 0, getHeight()); break;
                case 7 : addObject(enemy_2, 0, getHeight()/2); break;
            }
        }
    }
    
    public void gameOver() {
        // Called once healthbar is 0 or timer has run out
        // Stops music and begins the game over sound
        backgroundMusic.stop();
        gameOverSound.play();
        
        // Removes all the objects displayed on the screen
        removeObjects(getObjects(Actor.class));
        
        // Creates a game over screen and displays it
        GreenfootImage bg = getBackground();
        GreenfootImage txtImg = new GreenfootImage("GAME OVER", 80, Color.WHITE, Color.BLACK);
        bg.drawImage(txtImg, (bg.getWidth()-txtImg.getWidth())/2, (bg.getHeight()-txtImg.getHeight()-200)/2);
        GreenfootImage roundsComp = new GreenfootImage("You completed "+(round-1)+" rounds!", 80, Color.WHITE, Color.BLACK);
        bg.drawImage(roundsComp, (bg.getWidth()-roundsComp.getWidth())/2, (bg.getHeight()-roundsComp.getHeight()+200)/2);
        Greenfoot.stop(); 
    }
}
