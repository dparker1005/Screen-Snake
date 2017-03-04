import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.*;

/**
 * Write a description of class ChooseGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ChooseGame extends World
{
    static Displays walls = new Displays(" Click 2 for Wall Game ",70,Color.RED);
    static Displays header = new Displays(" Click 1 for Normal Game ",70,Color.BLUE);
    static Displays header1 = new Displays(" Click 3 for Speed Game ",70,Color.GREEN);
    static Displays header2 = new Displays(" Click 4 for Challenge Mode ",70,Color.WHITE);
    /**
     * Constructor for objects of class ChooseGame.
     * 
     */
    public ChooseGame()
    {    

        super(11, 6, 90); 

        addObject(header,5,1);
        addObject(header1,5,3);
        addObject(header2,5,4);

        addObject(walls,5,2);
    }

    public void act()
    {
        processKeyboard();
    }


    public void processKeyboard()
    {
        if(Greenfoot.isKeyDown("1"))
        {
            Greenfoot.setWorld(new SnakeDen("normal"));            
        }
        if(Greenfoot.isKeyDown("2"))
        {
            Greenfoot.setWorld(new SnakeDen("wall"));            
        }
        if(Greenfoot.isKeyDown("3"))
        {
            Greenfoot.setWorld(new SnakeDen("speed"));            
        }
        if(Greenfoot.isKeyDown("4"))
        {
            Greenfoot.setWorld(new SnakeDen("awesome"));            
        }
    }
}
