import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class InstructionScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InstructionScreen extends World
{
    Food food = new Food();

    Segment head = new Segment(Color.RED);
    Segment body1 = new Segment(Color.RED);
    Segment prevLeader = body1;

    /**
     * Constructor for objects of class InstructionScreen.
     * 
     */
    public InstructionScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(32, 18, 30);

        Displays d = new Displays("Instructions", 40 ,Color.BLUE);
        Displays d1 = new Displays("This game is a representation of the" + "\nclassic game ScreenSnake in which the"
                + "\ngoal is to grow your snake by eating" + "\napples."
                + "\n"
                + "\nIf there is a colision between the snakes head"
                + "\nand any part of the snakes body, or the wall,"
                + "\nthe game is over and you have lost.", 20, Color.BLUE); 
        Displays d2 = new Displays("The game gets progressively harder with each"
                + "\nbaby you eat, but perservere and eventually" + "\nyou may make it onto the HighScore list!", 20, Color.BLUE);
        Displays d3 = new Displays("Back", 30, Color.RED);

        this.addObject(d,16,3);
        this.addObject(d1, 6, 8);
        this.addObject(d2, 25 ,7);
        this.addObject(d3, 6, 16);

        body1.leader = head;

        addObject(head,  15, 15);  //spawns player 1
        addObject(body1, 15, 16);
        head.changeX = 0;
        head.changeY = -1;

        setPaintOrder(Displays.class,Segment.class,Food.class);

    }
    public void act()
    {
        checkMouse();

        foodRemover();
        foodRemover();
        autoAI();
        colorChange();
        processCollision();

        java.util.List <Segment> snake = getObjects(Segment.class);
        for(Segment test : snake)
        {
            test.color = Color.RED;
        }

    }

    public void colorChange()
    {
        java.util.List <Segment> snake = getObjects(Segment.class);
        int snakeSize = snake.size();
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

    public void autoAI()
    {

        if(food.getX()<head.getX())
        {
            if(head.changeX != 1)
            {
                head.changeX = -1;
                head.changeY = 0;
                return;
            }

        }
        if(food.getX()>head.getX())
        {
            if(head.changeX != -1)
            {
                head.changeX = 1;
                head.changeY = 0;
                return;
            }

        }
        if(food.getY()>head.getY())
        {
            if(head.changeY != -1)
            {
                head.changeX = 0;
                head.changeY = 1;
                return;
            }

        }
        if(food.getY()<head.getY())
        {
            if(head.changeY != 1)
            {
                head.changeX = 0;
                head.changeY = -1;
                return;
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

                addObject(head,  15, 15);  //spawns player 1
                addObject(body1, 15, 16);
                head.changeX = 0;
                head.changeY = -1;
                prevLeader = body1;

            }

        }

    }

    public void foodRemover()
    {
        Random r = new Random();
        java.util.List <Food> prey = getObjects(Food.class);
        int numPrey = prey.size();

        if(head.getWorld() == null)
            return;
        if(food.getWorld() != null)
        {
            if(head.getX() == food.getX() && head.getY() == food.getY())
            {
                this.removeObject(food);

                Segment seg = new Segment(Color.GREEN);
                seg.leader = prevLeader;
                addObject (seg, prevLeader.getX(), prevLeader.getY());
                prevLeader = seg;
            }
        }

        if(numPrey==0)  //spawns food whenever there isn't any
        {
            int foodX;
            int foodY;

            foodX = r.nextInt(32);  
            foodY = r.nextInt(18);  

            addObject(food, foodX, foodY);
        }
    }

    public static void checkMouse()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse == null) return;

        if(mouse.getButton() == 1)
        {
            if(mouse.getX() == 6 && mouse.getY() == 16) 
            {
                Greenfoot.setWorld(new TitleScreen());
            }
        }
    }
}
