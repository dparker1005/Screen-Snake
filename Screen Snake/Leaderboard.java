import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.io.*;
import javax.swing.*;
import java.util.*;
/**
 * Write a description of class Leaderboard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Leaderboard extends World
{
    static int place;
    static String name;
    static int score;

    int x=1;

    int indexScore = 0;
    int indexName = 0;
    int indexWriteScore;

    static String gamemode;

    static int prevScore = 0;

    int s = 0; //used for reading file

    PlayerProfile[] allScores = new PlayerProfile[11];  // the 11th slot is for the current player
    // who is assumed NOT to beat any records

    /**
     * Constructor for objects of class Leaderboard.
     * 
     */
    public Leaderboard()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(32, 18, 30);

        for(int j = 0; j<allScores.length;j++)
        {
            allScores[j] = new PlayerProfile();
        }

        if(SnakeDen.finalScore!=0)
        {
            prevScore = SnakeDen.finalScore;
            gamemode = SnakeDen.gamemode;

            readFile();
            getPlayerInfo();
            sortScores();
            writeFile();
            int s = 0;
            readFile();

            addHeaders();

            Displays placeHeader = new Displays("PLACE ",30,Color.BLACK);
            addObject(placeHeader,8,1);

            Displays nameHeader = new Displays("NAME ",30,Color.BLACK);
            addObject(nameHeader,16,1);

            Displays scoreHeader = new Displays("SCORE ",30,Color.BLACK);
            addObject(scoreHeader,24,1);

            Displays d3 = new Displays("Back", 30, Color.BLACK);
            addObject(d3, 6, 16);

            Displays d4 = new Displays("Reset\nHighscores", 30, Color.BLACK);
            addObject(d4, 24, 16);

        }
        else
        {

            readFile();
            sortScores();
            addHeaders();

            Displays placeHeader = new Displays("PLACE ",30,Color.BLACK);
            addObject(placeHeader,8,1);

            Displays nameHeader = new Displays("NAME ",30,Color.BLACK);
            addObject(nameHeader,16,1);

            Displays scoreHeader = new Displays("SCORE ",30,Color.BLACK);
            addObject(scoreHeader,24,1);

            Displays d3 = new Displays("Back", 30, Color.BLACK);
            addObject(d3, 6, 16);

            Displays d4 = new Displays("Reset\nHighscores", 30, Color.BLACK);
            addObject(d4, 24, 16);

        }

    }

    public void act()
    {
        checkMouse();
    }

    public  void sortScores()
    {
        //        int n,m;

        for(int index = 0; index<allScores.length-1; index++)
        {
            //            m = index;
            for(int scan = index + 1;scan<allScores.length;scan++)
            {
                if(allScores[scan].score > allScores[index].score)
                {
                    PlayerProfile save = allScores[index];
                    allScores[index] = allScores[scan];
                    allScores[scan] = save;
                }
            }
        }
    }

    public  void getPlayerInfo()
    {

        for(int y = 0; y<10; y++ )
        {
            if (prevScore >= allScores[y].score )
            {
                allScores[10].score = prevScore;

                String playerName = JOptionPane.showInputDialog(null,"Enter Your Name\nNo commas.");

                while(playerName.indexOf(",") >= 0)
                {
                    playerName = JOptionPane.showInputDialog(null,"You entered a forbidden character.\nNo commas.");                    
                }    

                if (playerName == null)
                {
                    playerName = "Unknown";
                }

                allScores[10].gamemode = gamemode;
                allScores[10].name = playerName;

                prevScore = 0;
                SnakeDen.finalScore = 0;

                return;
            }

        }
    }

    public  void checkMouse()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse == null) return;

        if(mouse.getButton() == 1)
        {
            if(mouse.getX() == 6 && mouse.getY() == 16) 
            {
                Greenfoot.setWorld(new TitleScreen());
            }

            if(mouse.getX() == 24 && mouse.getY() == 16) 
            {
                String areYouSure = JOptionPane.showInputDialog(null,"Are you sure?");
                if(areYouSure == null)
                {
                    Greenfoot.setWorld(new ResetScreen());
                }
                else if(areYouSure.equalsIgnoreCase("yes"))
                {
                    for(int q = 0; q<11; q ++)
                    {
                        allScores[q].score = 0;
                        allScores[q].name = "No Highscore Yet";
                        allScores[q].gamemode = "none";
                    }
                    writeFile();
                    Greenfoot.setWorld(new TitleScreen());
                }
                else 
                {
                    Greenfoot.setWorld(new ResetScreen());
                }
            }
        }
    }

    public void readFile()
    {
        try 
        {
            x=x+2;

            // (1) Construct a FileReader object    
            FileReader file = new FileReader( "leaderboard.csv" );

            // (2) Construct a BufferedReader object, passing it the FileReader as input
            BufferedReader buffer = new BufferedReader( file );

            // (3) Call the readLine() method until it returns null (end of file!)
            String line = buffer.readLine();  // skip first row of header labels
            line = buffer.readLine();  // read first row of actual data
            while ( s<allScores.length && line != null)
            {

                String[] fields = line.split( "," );  // break line up into 3 parts

                place = Integer.parseInt(fields[0]);  // surname always in first column
                allScores[s].name =  fields[1];     // name always in 2nd column
                allScores[s].score = Integer.parseInt(fields[2]);
                if(fields[3] != null)
                {
                    allScores[s].gamemode = fields[3];
                }
                else {allScores [s].gamemode = "";}

                s++;

                // Note that year is read in string format.
                // We need to convert that text into numeric form--
                // otherwise, we can't do math on it later!
                // System.out.println( String.format( "Just parsed balance of %s and bet of %s", balance, bet ) );
                line = buffer.readLine();  // we have to call again inside loop! to read rows of data after first row...
            }
            buffer.close();  // (4) Close the BufferedReader
            file.close();       // (5) Close the FileReader
        }
        catch ( FileNotFoundException ex )
        {
            System.out.println( "EXCEPTION in LEADERBOARD.readFile >> " + ex.getMessage() );
        }
        catch ( IOException ex )
        {
            System.out.println( "EXCEPTION in LEADERBOARD.readFile >> " + ex.getMessage() );
        }

    }

    public  void writeFile()
    {

        try 
        {
            FileWriter file = new FileWriter( "leaderboard.csv" );

            // (1) Construct a FileReader object    
            // (2) Construct a BufferedReader object, passing it the FileReader as input
            PrintWriter buffer = new PrintWriter( file );
            // (3) Call the readLine() method until it returns null (end of file!)
            buffer.println("Place,Name,Score,gamemode");

            for(int x = 0; x<allScores.length;x++)
            {

                buffer.println(x+","+allScores[x].name+","+allScores[x].score+","+allScores[x].gamemode);

            }

            buffer.close();  // (4) Close the BufferedReader
            file.close();       // (5) Close the FileReader
        }
        catch ( FileNotFoundException ex )
        {
            System.out.println( "EXCEPTION in LEADERBOARD.readFile >> " + ex.getMessage() );
        }
        catch ( IOException ex )
        {
            System.out.println( "EXCEPTION in LEADERBOARD.readFile >> " + ex.getMessage() );
        }
    }

    public void addHeaders()
    {
        Color color;
        for(int x = 1; x<10;x++)
        {
            if(allScores[x-1].gamemode == null)
            {
                return;
            }
            if(allScores[x-1].gamemode.equalsIgnoreCase("normal"))
            {
                color = Color.BLACK;
            }
            else if(allScores[x-1].gamemode.equalsIgnoreCase("wall"))
            {
                color = Color.RED;
            }
            else if(allScores[x-1].gamemode.equalsIgnoreCase("speed"))
            {
                color = Color.BLUE;
            }
            else if(allScores[x-1].gamemode.equalsIgnoreCase("awesome"))
            {
                color = Color.MAGENTA;
            }
            else
            {
                color = Color.BLACK;
            }

            Displays placeSet = new Displays(""+x,30,color);
            addObject(placeSet,8,x+2);

            Displays nameSet = new Displays(""+allScores[x-1].name,30,color);
            addObject(nameSet,16,x+2);

            Displays scoreSet = new Displays(""+allScores[x-1].score,30,color);
            addObject(scoreSet,24,x+2);

        }
    }
}
