package controllers;

import models.artifacts.Armor;
import models.artifacts.Helm;
import models.artifacts.Weapon;
import models.enemies.Enemy;
import models.filewriter.ReadFile;
import models.heroes.Hero;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class ConsoleMap 
{
    private static ArrayList<Enemy> enemyArray = new ArrayList<Enemy>();
    private static ArrayList<Enemy> tempArray = new ArrayList<Enemy>();
    private static int mapy;
    private static int mapx;
    private static int upgrade = 0;
    private int[][] map;
    private int heroLevel, size, enemies, xpos, ypos, oldx, oldy, level;
    private Hero hero = new Hero();
    private Enemy enemy = new Enemy();
    private boolean set = false;

    public ConsoleMap(){}

    public ConsoleMap( Hero hero )
    {
        this.hero = hero;  
    }

    public void setConsoleMapSize()
    {
        size = ( hero.getHeroStats().getLevel() - 1 ) * 5 + 10 - ( hero.getHeroStats().getLevel() % 2 );
        mapx = size;
        mapy = size;
        map = new int[size][size];
    }

    public void setEnemies()
    {
        this.enemies = hero.getHeroStats().getLevel() * 8;
    }

    public void setHeroPos()
    {
		int x = 0, y = 0;

        if ( ( size % 2 ) == 1 )
        {
			x = ( int )( size / 2 );
			y = ( int )( size / 2 );
		}
        else if ( ( size % 2 ) == 0 )
        {
			x = ( size / 2 );
			y = ( size / 2 );
		}
        this.xpos = x;
        this.ypos = y;
    }

    public void updateHeroPos( int xpos, int ypos )
    {
        this.oldx = this.xpos;
        this.oldy = this.ypos;
        this.xpos += xpos;
        if ( this.xpos < 0 )
        {
            upgradeXP( 1 );
        }      
        else if ( this.xpos >= this.size )
        {
            upgradeXP( 1 );
        }
            
        this.ypos += ypos;
        if ( this.ypos < 0 )
        {
            upgradeXP( 1 );
        }
        else if ( this.ypos >= this.size )
        {
            upgradeXP( 1 );
        }  
    }

    public int getConsoleMapSize()
    {
        return ( this.size );
    }

    public void ConsoleMap()
    {

        if ( set == false )
        {
            setConsoleMapSize();
            setHeroPos();
            setEnemies();
            
            if ( tempArray.isEmpty() )
                createEnemy();
            else
                enemyArray.addAll( tempArray );
            set = true;
        }

        /* initialize map array to zeros */
        for ( int y = 0; y < size; y++ )
        {
            for ( int x = 0; x < size; x++ )
            {
                map[y][x] = 0;
            }
        }

        /* initialization for enemy */
        for ( Enemy enemy : enemyArray )
        {
            map[enemy.getEnemyPosY()][enemy.getEnemyPosX()] = enemy.getTypeID();
        }

        /* initialization for hero */
         map[this.ypos][this.xpos] = 4;

         /* check if hero  has crossed paths with enemy */
         for ( Enemy enemy : enemyArray )
         {
            boolean t = crossedEnemy( this.ypos, this.xpos, enemy.getEnemyPosY(), enemy.getEnemyPosX() );
            if ( t == true )
            break;
        }

        System.out.println( "Level: " + hero.getHeroStats().getLevel() + " | " +
            "Power: " + hero.getHeroStats().getPower() + " | " + 
            "Hitpoints: " + hero.getHeroStats().getHitPoints() + " | " +
            "Experience: " + hero.getHeroStats().getXPoints() + "\n\n" );
         
        for ( int y = 0; y < mapy; y++ )
        {
            for ( int x = 0; x < mapx; x++ )
            {
                switch ( map[y][x] )
                {
                    case 0:
                        System.out.print( "|...|" );
                        break;
                    case 1:
                        System.out.print( "|.x.|" );
                        break;
                    case 2:
                        System.out.print( "|.x.|" );
                        break;
                    case 3:
                        System.out.print( "|.x.|" );
                        break;
                    default:
                        System.out.print( "|.o.|" );
                        break;
                }
            }
            System.out.println();
        }
    }

    public static void registerEnemy( Enemy enemy )
    {
        if ( enemyArray.contains( enemy ) )
            return;
        enemyArray.add( enemy );
    }

//    public static void removeEnemy( Enemy enemy )
//    {
//        if ( enemyArray.contains( enemy ) )
//            enemyArray.remove( enemy );
//    }

    public void createEnemy()
    {
        for ( int i = 0; i < this.enemies; i++ )
        {
            Random random = new Random();
            int eposx = random.nextInt( size );
            int eposy = random.nextInt( size );
            while ( eposy == this.ypos || eposx == this.xpos )
            {
                eposx = random.nextInt( size );
                eposy = random.nextInt( size );
            }
            enemy = PlayersGenerator.newEnemy( hero );
            enemy.setEnemyPos( eposx, eposy );
            registerEnemy( enemy );
        }
    }

    public Enemy getCrossedEnemy()
    {
        for ( int i = 0; i < enemyArray.size(); i++ )
        {
            if ( enemyArray.get(i).getEnemyPosY() == this.ypos && enemyArray.get(i).getEnemyPosX() == this.xpos )
                return ( enemyArray.get( i ) );
        }
        return ( null );
    }

    public void upgradeXP( int type )
    {
        if ( type == 1 )
        {
            int xp;
            if ( hero.getHeroStats().getXPoints() < 2450 )
            {
                xp = 2450;
                hero.getHeroStats().setXPoints( xp );
            }
            else if ( hero.getHeroStats().getXPoints() < 4800 )
            {
                xp = 4800;
                hero.getHeroStats().setXPoints( xp );
            }   
            else if ( hero.getHeroStats().getXPoints() < 8050 )
            {
                xp = 8050;
                hero.getHeroStats().setXPoints( xp );
            }
            else if ( hero.getHeroStats().getXPoints() < 12200 )
            {
                xp = 12200;
                hero.getHeroStats().setXPoints( xp );
            }
            else if ( hero.getHeroStats().getXPoints() < 12201 )
            {
                System.out.println( "************ Game Comleted **********" );
                System.out.println( "################ GOODBYE ############" );
                System.exit( 0 );
            }  

            hasWon();
        }
        else if ( type == 2 )
        {
            hero.getHeroStats().setXPoints( hero.getHeroStats().getXPoints() + enemy.getPower() );
            ReadFile.updateFile( hero );
            hasWon();
        }
    }

    public void hasWon()
    {
        if ( hero.getHeroStats().getXPoints() < 2450 && hero.getHeroStats().getXPoints() > 1000 )
        {
            this.level = 1;
        }
        else if ( hero.getHeroStats().getXPoints() == 2450 )
        {
            this.level = 2;
        }
        else if ( hero.getHeroStats().getXPoints()  > 2450 && hero.getHeroStats().getXPoints() < 4800 )
        {
            this.level = 2;
        }
        else if ( hero.getHeroStats().getXPoints() == 4800 )
        {
            this.level = 3;
        }
        else if ( hero.getHeroStats().getXPoints()  > 4800 && hero.getHeroStats().getXPoints() < 8050 )
        {
            this.level = 3;
        }  
        else if ( hero.getHeroStats().getXPoints() == 8050 )
        {
            this.level = 4;
        }
        else if ( hero.getHeroStats().getXPoints()  > 8050 && hero.getHeroStats().getXPoints() < 12200 )
        {
            this.level = 4;
        }
        else if ( hero.getHeroStats().getXPoints() == 12200 )
        {
            this.level = 5;
        }

        if ( this.level > hero.getHeroStats().getLevel() )
        {
            hero.getHeroStats().setLevel( this.level ); 
            ReadFile.updateFile ( hero );
            System.out.println( "#-----------------------------------#\n" );
            System.out.println( "###            Congrats           ###\n" );
            System.out.println( "#-----------------------------------#\n" );

            System.out.println( "#-----------------------------------#" );
            System.out.println( "#       1. Move to next level       #" );
            System.out.println( "#       2. Quit game                #" );
            System.out.print( "--------------------------> " );

            Scanner scanner = new Scanner( System.in );

            while ( scanner.hasNextLine() )
            {
                String line = scanner.nextLine();

                if ( line.matches( "\\s*[1-2]\\s*" ) )
                {
                    int option = Integer.parseInt( line );
                    if ( option == 1 )
                    {
                        enemyArray.removeAll( enemyArray );
                        GameController.run( hero );
                        System.out.println( "Continue your Game." );
                    }
                    else if ( option == 2 )
                    {
					System.out.println( "******** You quitted the game *******" );
					System.out.println( "############## GOODBYE ##############\n" );
                        System.exit( 0 );
                    }
                }
                else
                System.out.println( "Invalid input. Try again." );
            }
        }
        else if ( this.level == hero.getHeroStats().getLevel() )
        {
            enemyArray.removeAll( enemyArray );    
        }
    }

    public boolean crossedEnemy( int hy, int hx, int ey, int ex )
    {
        if ( ( hx == ex ) && ( hy == ey ) )
        {
            System.out.println( "Ooops!!!" );
            System.out.println( "You have crossed paths with an enemy\n" );

            System.out.println( "#-----------------------------------#" );
            System.out.println( "#       1. Run from the enemy       #" );
            System.out.println( "#       2. Fight the enemy          #" );
            System.out.print( "--------------------------> " );

            Scanner scanner = new Scanner( System.in );
            while ( scanner.hasNextLine() )
            {
                String line = scanner.nextLine();
                if ( line.matches( "\\s*[1-2]\\s*" ) )
                {
                    int choice = Integer.parseInt( line );
                    if ( choice == 1 )
                    {
                        System.out.println( "#-----------------------------------#\n" );
                        System.out.println( "###  You have run from the enemy! ###\n" );
                        System.out.println( "#-----------------------------------#\n" );
                        GameController.run(hero);

                    }
                    else if ( choice == 2 )
                    {
                        System.out.println( "#-----------------------------------#\n" );
                        System.out.println( "###        The battle is on       ###\n" );
                        System.out.println( "#-----------------------------------#\n" );
                        Enemy crossed = getCrossedEnemy();
                        int won = GameController.fight( hero, crossed );
                        if ( won == 1 )
                        {
                            won( crossed );
                            return ( true );
                        
                        }
                        else
                        {    
                            lost();
                            break;
                        }           
                    }
                    else
                        System.out.println( "Ooops! Invalid input. Try again.\n" );
                }
                else
                System.out.println( "Ooops! Invalid input. Try again.\n" );
            }
        }
        return ( false );
    }

    public void won( Enemy crossed )
    {
        enemyArray.remove( crossed );
        upgradeXP( 2 );
        System.out.println( "#-----------------------------------#\n" );
        System.out.println( "###       You won the battle      ###\n" );
        System.out.println( "#-----------------------------------#\n" );
        if ( GameController.inLuck() == true )
        {
            System.out.println( "Available enemy artifact: " + crossed.getArtifact().getType() + "\n" );
            System.out.println( "#-----------------------------------#" );
            System.out.println( "#     1. Pick up enemy artifact     #" );
            System.out.println( "#     2. Continue your Game         #" );
            System.out.print( "--------------------------> " );

            Scanner scanner = new Scanner( System.in );
            while ( scanner.hasNextLine() )
            {
                String line = scanner.nextLine();
                if ( line.matches( "\\s*[1-2]\\s*" ) )
                {
                    int option = Integer.parseInt( line );
                    if  ( option == 1 )
                    {
                        String type = enemy.getArtifact().getType();
                        if ( type.equals( "Weapon" ) )
                        {
                            Weapon weapon = new Weapon( "Weapon" );
                            hero.setArtifact( weapon );
                            hero.getHeroStats().setAttack( 65 );
                            ReadFile.updateFile( hero );
                            break;
                        }
                        else if ( type.equals( "Armor" ) )
                        {
                            Armor armor = new Armor( "Armor" );
                            hero.setArtifact( armor );
                            hero.getHeroStats().setDefense( 55 );
                            ReadFile.updateFile( hero );
                            break;
                        }
                        else if ( type.equals( "Helm" ) )
                        {
                            Helm helm = new Helm( "Helm" );
                            hero.setArtifact( helm );
                            hero.getHeroStats().setHitPoints( 75 );
                            ReadFile.updateFile( hero );
                            break;
                        }
                    }
                    else if ( option == 2 )
                    {
                        upgradeXP( 2 );
                    }
                }
                else
                    System.out.println( "Invalid input. Try again" );
            }
        }
        else
        {
            upgradeXP( 2 );
            System.out.println( "#-----------------------------------#\n" );
            System.out.println( "###       500 XPoints gained      ###\n" );
            System.out.println( "#-----------------------------------#\n" );
            try
            {
                Thread.sleep( 3000 );
            }
            catch( InterruptedException e )
            {
                System.exit( 0 );
            }
            GameController.run( hero );
        } 
    }

    public void lost()
    {
        System.out.println( "#-----------------------------------#\n" );
        System.out.println( "###      You lost the battle      ###\n" );
        System.out.println( "#-----------------------------------#\n" );
        System.exit( 0 );
    }
}