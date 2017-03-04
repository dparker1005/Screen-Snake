import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.*;
/**
 * Write a description of class ExpandingCircle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExpandingCircle extends World
{
    World dest = null;

    int circleX, circleY, circleWidth, circleHeight;  // c for circle, so circle x, circle y, circle width, circle height

    // NOTE: the maximum width is 32 cells * 30 pixels per cell, for total pixel across screen.
    //       And similarly, for height...
    static final int MAX_WIDTH = 32 * 30;
    static final int MAX_HEIGHT = 18 * 30;

    /**
     * Constructor for objects of class ExpandingCircle.
     * 
     */
    public ExpandingCircle( World whereTo, GreenfootImage background )
    {    
        // Create a new world with 32x18 cells with a cell size of 30x30 pixels.
        super(32, 18, 30);
        dest = whereTo;

        circleWidth = 25;
        circleHeight = 25;

        circleX = MAX_WIDTH/2 - circleWidth/2;
        circleY = MAX_HEIGHT/2 - circleHeight/2;

        setBackground( background );
    }

    public void act()
    {
        GreenfootImage bg = getBackground();
        Random r = new Random();
        int color = r.nextInt(6);
        Color setColor = new Color(0,0,0);
        if(color == 0)
        {
            setColor = Color.GREEN;
        }
        if(color == 1)
        {
            setColor = Color.RED;
        }
        if(color == 2)
        {
            setColor = Color.MAGENTA;
        }
        if(color == 3)
        {
            setColor = Color.WHITE;
        }
        if(color == 4)
        {
            setColor = Color.BLUE;
        }
        if(color == 5)
        {
            setColor = Color.ORANGE;
        }
        if(color == 6)
        {
            setColor = Color.YELLOW;
        }
        bg.setColor( setColor );
        bg.fillOval( circleX, circleY, circleWidth, circleHeight );

        circleWidth += 100;
        circleHeight += 50;
        circleX = MAX_WIDTH/2 - circleWidth/2;
        circleY = MAX_HEIGHT/2 - circleHeight/2;
        //System.out.println( String.format( "Circle specs: (%d, %d, %dx%d)", circleX, circleY, circleWidth, circleHeight ) );

        if ( circleWidth >= MAX_WIDTH )
            Greenfoot.setWorld( dest );
    }
}
