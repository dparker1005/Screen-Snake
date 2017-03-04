import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StupidScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ResetScreen extends World
{

    /**
     * Constructor for objects of class StupidScreen.
     * 
     */
    public ResetScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(32, 18, 30);
    }
    
    public void act()
    {
        Greenfoot.setWorld(new Leaderboard());
    }
}
