import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

import java.io.*;
/**
 * Write a description of class SnakeDen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnakeDen extends World
{
    Food food = new Food();
    Segment head = new Segment(Color.GREEN);
    Segment body1 = new Segment(Color.GREEN);
    Segment prevLeader = body1;

    int snakeSize = 0;   //Cheaty button

    boolean gameOver = false;

    int index;

    static int speed;

    static int finalScore;

    static String gamemode;
    /**
     * Constructor for objects of class SnakeDen.
     * 
     */
    public SnakeDen(String modeIn)
    {    
        super(32, 18, 30);  //board dimentions

        body1.leader = head;

        addObject(head,  15, 15);  //spawns player 1
        addObject(body1, 15, 16);
        head.changeX = 0;
        head.changeY = -1;

        setPaintOrder(Segment.class, Food.class);

        gamemode = modeIn;

        Greenfoot.setSpeed(40);
        speed = 40;

    }

    public void act()
    {
        processKeyboard();
        foodRemover();
        processCollision();

        colorChange();

        deathCheck();

    }

    public void colorChange()
    {
        java.util.List <Segment> snake = getObjects(Segment.class);
        Random r = new Random();
        if(snakeSize >= 10)
        {
            for(Segment test : snake)
            {
                test.color = Color.RED;
            }
        }
        if(snakeSize >= 20)
        {
            for(Segment test : snake)
            {
                test.color = Color.ORANGE;
            }
        }
        if(snakeSize >= 30)
        {
            for(Segment test : snake)
            {
                test.color = Color.WHITE;
            }
        }
        if(snakeSize >= 40)
        {
            for(Segment test : snake)
            {
                test.color = Color.YELLOW;
            }
        }
        if(snakeSize >= 50)
        {
            for(Segment test : snake)
            {
                test.color = Color.BLUE;
            }
        }
        if(snakeSize >= 75)
        {
            for(Segment test : snake)
            {
                test.color = Color.MAGENTA;
            }
        }
        if(snakeSize >= 100)
        {
            for(Segment test : snake)
            {
                int color = r.nextInt(6);
                if(color == 0)
                {
                    test.color = Color.GREEN;
                }
                if(color == 1)
                {
                    test.color = Color.RED;
                }
                if(color == 2)
                {
                    test.color = Color.MAGENTA;
                }
                if(color == 3)
                {
                    test.color = Color.WHITE;
                }
                if(color == 4)
                {
                    test.color = Color.BLUE;
                }
                if(color == 5)
                {
                    test.color = Color.ORANGE;
                }
                if(color == 6)
                {
                    test.color = Color.YELLOW;
                }
            }
        }
    }

    public void deathCheck()
    {
        java.util.List <Segment> snake = getObjects(Segment.class);
        int snakeNum = snake.size();

        MouseInfo mouse = Greenfoot.getMouseInfo();   
        if ( mouse == null ) 
        {
            return;
        }


        if(snakeNum == 0 && gameOver == false)
        {
            finalScore = snakeSize;
            Displays scoreScreen = new Displays("Your Score: "+snakeSize,150,Color.WHITE);
        
            addObject(scoreScreen,16,9);
        

            gameOver = true;
        }

        if(snakeNum == 0)
        {
            if(mouse.getButton() == 1 )
            {
                Greenfoot.setWorld(new Leaderboard());
            }
        }
    }

    public void processCollision()
    {
        java.util.List <Segment> snake = getObjects(Segment.class);
        int n = 0;

        for(Segment test : snake)
        {
            if(test.getX()==head.getX() && test.getY()==head.getY())
            {
                n++;
            }
        }

        if (n>=2)
        {
            for(Segment test : snake)
            {
                removeObject(test);

            }

        }
    }

    public void foodRemover()
    {
        Random r = new Random();
        java.util.List <Food> prey = getObjects(Food.class);
        int numPrey = prey.size();

        if(numPrey==0)  //spawns food whenever there isn't any
        {
            int foodX;
            int foodY;

            foodX = r.nextInt(32);  
            foodY = r.nextInt(18);  

            addObject(food, foodX, foodY);
        }
        if(head.getWorld() == null)
            return;

        if(food.getX()==head.getX() && food.getY() == head.getY())  //collision detection
        {
            removeObject(food);
            //spawns a new body square
            Segment seg = new Segment(Color.GREEN);
            seg.leader = prevLeader;
            addObject (seg, prevLeader.getX(), prevLeader.getY());
            prevLeader = seg;
            snakeSize++;
            if(gamemode == "speed" || gamemode == "awesome")
            {
                speed = speed+1;
                Greenfoot.setSpeed(speed);
            }
            if(gamemode == "wall" || gamemode == "awesome")
            {
                Segment wall = new Segment(Color.GREEN);
                wall.leader = null;
                addObject (wall, body1.getX(), body1.getY());
            }
        }

    }

    public void processKeyboard()
    {
        String key = Greenfoot.getKey();

        if(Greenfoot.isKeyDown("right"))
        {
            if(head.changeX == -1 && head.changeY == 0)
            {return;}
            else
            {
                head.changeX = 1;
                head.changeY = 0; 
                return;
            }
        }
        if(Greenfoot.isKeyDown("left"))
        {
            if(head.changeX == 1 && head.changeY == 0)
            {return;}
            else
            {
                head.changeX = -1;
                head.changeY = 0;
                return;
            }
        }
        if(Greenfoot.isKeyDown("up"))
        {
            if(head.changeX == 0 && head.changeY == 1)
            {return;}
            else
            {
                head.changeY = -1;
                head.changeX = 0;
                return;
            }
        }
        if(Greenfoot.isKeyDown("down"))
        {
            if(head.changeX == 0 && head.changeY == -1)
            {return;}
            else
            {
                head.changeY = 1;
                head.changeX = 0;
                return;
            }
        }
        if(Greenfoot.isKeyDown("space"))
        {
            if(gamemode == "wall" || gamemode == "awesome")
            {
                Random r = new Random();
                int x = r.nextInt(31);
                int y = r.nextInt(17);

                head.setLocation(x,y);
            }
        }
    }
}
