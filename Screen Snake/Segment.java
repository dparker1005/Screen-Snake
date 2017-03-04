import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import java.util.*;

/**
 * Write a description of class Segment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Segment extends Actor
{
    static int countSegments = 1;
    int idNum;

    greenfoot.Color color = greenfoot.Color.GREEN;
    Random r = new Random();
    
    //size
    int width = 20;
    int height= 20;

    int changeY = 0;
    int changeX = 0;

    int previousX;
    int previousY;

    int x;
    int y;

    Segment leader = null;
    
    GreenfootImage img = new GreenfootImage(width,height);
    public Segment(greenfoot.Color color)
    {
        idNum = countSegments++;

        img.setColor(color);
        img.fill();
        setImage(img);
    }

    /**
     * Act - do whatever the Segment wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // save x into previous x, and same for y
        World w = getWorld();
        
        img.setColor(color);
        img.fill();
        setImage(img);
        if ( leader == null )
        {
            // -------------------------------------
            // start Logic for segment without leader
            // -------------------------------------

            x = getX();
            y = getY();

            // save the locations x,y into previous
            previousX = x;
            previousY = y;

            x += changeX;
            y += changeY;
            setLocation(x, y);

            // -------------------------------------
            // end Logic for segment without leader
            // -------------------------------------
           
        }
        else  // you know that this segment indeed has a leader
        {
            // -------------------------------------
            // start Logic for segment with leader
            // -------------------------------------

            // remember/save this segments current x, y into previous
            x = getX();
            y = getY();

            // save the locations x,y into previous
            previousX = x;
            previousY = y;
            
            // get the leader's previous position x and y
            
            setLocation(leader.previousX , leader.previousY);
            
            // and now set the location of this segment to that x and y

            // -------------------------------------
            // end Logic for segment with leader
            // -------------------------------------
        }

        // output where this head currently is, AND where it was
        int leaderIdNum = -1;
        if ( leader != null )
            leaderIdNum = leader.idNum;
            
        //collision Detection
       
    }    
}
