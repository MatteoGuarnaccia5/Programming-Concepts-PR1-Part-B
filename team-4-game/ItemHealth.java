import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An item which increases the user's health
 * 
 * @author Matteo Guarnaccia, William Brown, Yufan Kambang
 * @version 02/12/2023
 */
public class ItemHealth extends Actor {
    // Gets the image of the fish
    public GreenfootImage image = getImage();
    
    public ItemHealth() {
        // Resizes image
        image.scale(25,25);
    }
}
