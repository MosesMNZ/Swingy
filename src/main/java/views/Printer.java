package views;

import java.util.Scanner;

import controllers.GameController;
import models.filewriter.ReadFile;
import models.heroes.Hero;

public class Printer
{

	public static String printWelcome()
	{
		System.out.println( "#-----------------------------------#\n" );
		System.out.println( "############### SWINGY ##############\n" );
		System.out.println( "#      You are on Console Mode      #\n" );
		System.out.println( "#-----------------------------------#\n" );
		System.out.println( "#          Enter your name          #\n" );
		System.out.print( "-------------------------> " );

		String player = null;
		Scanner scanner = new Scanner( System.in );
		while ( scanner.hasNextLine() )
		{
			player = scanner.nextLine();
			player = player.trim();
			
			if ( player.length() > 0 )
			{
				String[] check = player.split( "\\s" );
				if ( check != null )
					player = String.join( "_", check );
				break;
			}
			else
				System.out.println( "Invalid input. Try again." );
		}
		
		return player;
	}
	
	public static void printMode( String mode )
	{
		System.out.println( "#-----------------------------------#\n" );
		System.out.println( "######### WELCOME TO SWINGY #########\n" );
		System.out.println( "#      You are on "+ mode.toUpperCase() + " Mode      #\n" );
		System.out.println( "#-----------------------------------#\n" );
		System.out.println( "#          Please Select:           #\n" );
		System.out.println( "#    1. To change your game mode    #\n" );
		System.out.println( "#    2. To proceed                  #\n" );
		System.out.print( "-----------------> " );
		Scanner scanner = new Scanner( System.in );
		while ( scanner.hasNextLine() )
		{
			String line = scanner.nextLine();

			if ( line.matches( "\\s*[1-2]\\s*" ) )
			{
				int modeInt = Integer.parseInt( line );

				if ( modeInt == 1 )
				{
					GUIView GUIView = new GUIView();
					GUIView.welcomeFrame();
					break;
				}
				else if ( modeInt == 2 )
				{
					CommandLine.begin();
					break;
				}
			}
			else
				System.out.println( "Invalid mode selection. Try again." );
		}

	}

	public static int printHeroMenu() 
	{
		System.out.println( "#-----------------------------------#\n" );
		System.out.println( "############ HERO OPTIONS ###########\n" );
		System.out.println( "#-----------------------------------#\n" );
		System.out.println( "#          1. Create a hero         #\n" );
		System.out.println( "#          2. Select a hero         #\n" );
		System.out.print( "--------------------------> " );

		int option = 0;
		Scanner scanner = new Scanner( System.in );
		while ( scanner.hasNextLine() )
		{
			String line = scanner.nextLine();

			if ( line.matches( "\\s*[1-2]\\s*" ) )
			{
				option = Integer.parseInt( line );
				break;	
			}
			else
				System.out.println( "Invalid input. Try again." );
		}
		
		return option;
	}

	public static int printHeroSelection()
	{
		System.out.println( "#-----------------------------------#\n" );
		System.out.println( "########## ARTIFACTS  TYPES #########\n" );
		System.out.println( "#-----------------------------------#\n" );
		System.out.println( "#             1. Warrior            #\n" );
		System.out.println( "#             2. Protector          #\n" );
		System.out.println( "#             3. Master             #\n" );
		System.out.print( "----------------------> " );

		int option = 0;
		Scanner scanner = new Scanner( System.in );
		while ( scanner.hasNextLine() )
		{
			String line = scanner.nextLine();

			if ( line.matches( "\\s*[1-3]\\s*" ) )
			{
				option = Integer.parseInt( line );
				break;	
			}
			else
				System.out.println("Invalid input. Try again.");
		}
		
		return option;
	}

	public static void printDirections()
	{
		System.out.println( "#-----------------------------------#\n" );
		System.out.println( "############  DIRECTIONS  ###########\n" );
		System.out.println( "#-----------------------------------#\n" );
		System.out.println( "#              1. North             #\n" );
		System.out.println( "#        3. East       4. West      #\n" );
		System.out.println( "#              2. South             #\n" );
		System.out.print( "----------------------> " );
	}

	public static void printFight()
	{

		System.out.println( "#-----------------------------------#\n" );
		System.out.println( "########### FIGHT  OPTIONS ##########\n" );
		System.out.println( "#-----------------------------------#\n" );
		System.out.println( "#              1. Fight             #\n" );
		System.out.println( "#              2. Run               #\n" );
		System.out.print( "---------------------> " );

	}

	public static void printDetails( long type, String player, Hero hero )
	{
		System.out.println( "#-----------------------------------#\n" );
		System.out.println( "############# HERO STATS ############\n" );
		System.out.println( "#-----------------------------------#\n" );
		if ( type == 1 )
		{
			System.out.println( "WARRIOR " + player + "\n" +
					"Level: " + hero.getHeroStats().getLevel() + "\n" +
					"Attack: " + hero.getHeroStats().getAttack() + "\n"  +
					"Defense: " + hero.getHeroStats().getDefense() + "\n" +
					"Hit Points: " + hero.getHeroStats().getHitPoints() + "\n\n" );
		}
		else if ( type == 2 )
		{
			System.out.println( "PROTECTOR " + player + "\n" +
					"Level: " + hero.getHeroStats().getLevel() + "\n" +
					"Attack: " + hero.getHeroStats().getAttack() + "\n"  +
					"Defense: " + hero.getHeroStats().getDefense() + "\n" +
					"Hit Points: " + hero.getHeroStats().getHitPoints() + "\n\n" );
		}
		else if ( type == 3 )
		{
			System.out.println( "MASTER " + player + "\n" +
					"Level: " + hero.getHeroStats().getLevel() + "\n" +
					"Attack: " + hero.getHeroStats().getAttack() + "\n"  +
					"Defense: " + hero.getHeroStats().getDefense() + "\n" +
					"Hit Points: " + hero.getHeroStats().getHitPoints() + "\n\n" );
		}
		System.out.println( "#-----------------------------------#" );
		System.out.println( "#         1. Start game             #" );
		System.out.println( "#         2. Quit game              #" );
		System.out.print( "--------------------------> " );

		int option = 0;
		Scanner scanner = new Scanner( System.in );
		while ( scanner.hasNextLine() )
		{
			String line = scanner.nextLine();

			if ( line.matches( "\\s*[1-2]\\s*" ) )
			{
				option = Integer.parseInt( line );
				if ( option == 1 )
				{
					GameController.run( hero );
				}
				else
				{
					System.out.println( "******** You quitted the game *******" );
					System.out.println( "############## GOODBYE ##############\n" );

					System.exit( 0 );
				}
				break;
			}
			else
				System.out.println( "Invalid input. Try again." );
		}

	}

}