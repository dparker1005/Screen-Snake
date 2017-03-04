import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.*;

/**
 * Write a description of class WipeScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WipeScreen extends World
{

    World dest = null;

    /**
     * Constructor for objects of class WipeScreen.
     * 
     */
    public WipeScreen( World whereTo )
    {    
        super(32, 18, 30);   // resize to your ...
        dest = whereTo;
    }
    
    public void act()
    {
        GreenfootImage square = new GreenfootImage(100,100);    
        square.setColor(Color.BLACK);
        square.fill();
        
        Food[] squares = new Food[576];
        for(int x=0; x<576; x++)
        {
            squares[x] = new Food();
            squares[x].setImage(square);
        }
        int q = 0;
        
        for(int y = 0; y<18; y++)
        {
            for(int x = 0; x<30; x++)
            {
                addObject(squares[q], 50+(x*100), 50+(y*100));
                this.repaint();
                //Greenfoot.playSound("sounds/BabyCry.mp3");
                long now = System.currentTimeMillis();
                long lapse = timeLapse(now);
                while(lapse <=50)
                {
                    lapse = timeLapse(now);
                }
            }
        }
        
        // do your animation and WHEN AND ONLY WHEN it is complete
        Greenfoot.setWorld( dest );
        
        
    }
    
        static long timeLapse(long time)
    {
        long nower = System.currentTimeMillis();
        long elapseder = nower-time;
        return elapseder;
    }
}
