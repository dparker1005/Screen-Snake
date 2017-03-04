import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.*;

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{
    Random r = new Random();

    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        super(32, 18, 30); 
        //24 4
        Displays header = new Displays("SCREEN SNAKE ",150,Color.BLUE);
        addObject(header,16,3);

        Displays instruct = new Displays("Instructions",40,Color.BLACK);
        addObject(instruct,8,15);

        Displays startGame = new Displays("Start Game",40,Color.BLACK);
        addObject(startGame,16,8);

        Displays leaders = new Displays("Leaderboard",40,Color.BLACK);
        addObject(leaders, 24,15);

        Greenfoot.setSpeed(40);

    }

    public void act()
    {
        checkClick();
    }


    
    public  void checkClick()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();   
        if ( mouse == null ) 
        {
            return;
        }// abandon/exit method now!!

        // now process the event
        // i.e.
        // get button clicked ... 1 is left
        if(mouse.getButton() == 1 )
        {
            if(mouse.getX() == 8 && mouse.getY() == 15 ||
            mouse.getX() == 7 && mouse.getY() == 15||
            mouse.getX() == 9 && mouse.getY() == 15)
            {
                GreenfootImage copyBg = new GreenfootImage( getBackground() );
                Greenfoot.setWorld(new ExpandingCircle(new InstructionScreen(), copyBg));
            }
        } 
        if(mouse.getButton() == 1)
        {
            if(mouse.getX() == 16 && mouse.getY() == 8||
            mouse.getX() == 15 && mouse.getY() == 8||
            mouse.getX() == 17 && mouse.getY() == 8)
            {
                GreenfootImage copyBg = new GreenfootImage( getBackground() );
                Greenfoot.setWorld(new ExpandingCircle(new ChooseGame(), copyBg));
            }
        }  
        if(mouse.getButton() == 1)
        {
            if(mouse.getX() == 24 && mouse.getY() == 15||
            mouse.getX() == 23 && mouse.getY() == 15||
            mouse.getX() == 25 && mouse.getY() == 15)
            {
                GreenfootImage copyBg = new GreenfootImage( getBackground() );
                Greenfoot.setWorld(new ExpandingCircle(new Leaderboard(), copyBg));
            }
        }  
    }
    

}

