import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    player player;
    public Enemy(player main_player)
    {
        player = main_player;
    }
    public void act()
    {
        // Add your action code here.
        //move_around();
        follow();
    }
    public void move_around()
    {
        move(1);
        turnTowards(getWorld().getWidth()/2, getWorld().getHeight()/2);
    }
    public void follow()
    {
        move(1);
        turnTowards(player.getX(), player.getY());
    }
}   
