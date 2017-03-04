import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Displays here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Displays extends Actor
{

    // save the text so you can reference it later (outside of the Constructor method)
    // as a property

    Displays(String text,int size,greenfoot.Color color)
    {
        greenfoot.GreenfootImage output;
        output = new greenfoot.GreenfootImage(text, size, color, new Color(0,0,0,0)); 
        this.setImage(output);
    }


    /**
     * Act - do whatever the Displays wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
