package views;

import controllers.GameController;
import models.filewriter.ReadFile;
import models.heroes.Hero;

import java.util.Scanner;

public class CommandLine
{

	public static void begin() 
	{
		String player;
		int type, createHero, option = 0;
		Hero hero = new Hero();
		
		try
		{
			createHero = Printer.printHeroMenu();
			if ( createHero == 1 )
			{
				player = Printer.printWelcome();
				type = Printer.printHeroSelection();
				hero = GameView.createHero( player, type );
				Printer.printDetails( type, player, hero );
			}
			else if ( createHero == 2 )
			{
				ReadFile.getDBHeroes();
				Scanner scanner = new Scanner( System.in );
				while ( scanner.hasNextLine() )
				{
					String line = scanner.nextLine();
					int linesCount = ReadFile.getLinesCount();
					if ( ReadFile.isNumber( line ) == true )
					{
						try
						{
							int index = Integer.parseInt( line );
							if ( index > 0 && index <= linesCount )
							{
								option = index;
								break;
							}
						}
						catch( Exception e )
						{
							System.out.println( "Invalid input. Try again." );
						}
					}
					else
						System.out.println( "Invalid input. Try again." );
				}
				hero = GameView.DBHero( ReadFile.getHero( option ) );
				GameController.run( hero );
			}
		}
		catch ( Exception e )
		{
			System.out.println( e.getMessage() );
		}
	}
}