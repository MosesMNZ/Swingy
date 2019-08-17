package controllers;

import java.util.*;

import views.*;
import filewriter.WriteFile;

public class Main 
{
	public static void main ( String[] args )
	{
		try
		{
			WriteFile.createFile();

			switch ( args[0] ) 
			{
               case "console":
					Printer.printMode( args[0] ); 
	                   break;
               case "gui":
					GUIView GUIView = new GUIView();
					GUIView.modeFrame();
					break;
               default:
					System.out.println( "Invalid mode argument. Try again." );
                   break;
           }
		}
		catch ( Exception e )
		{
			System.out.println( "Game mode selection error: " + e );
		}
		finally
		{
			WriteFile.closeFile();
		}
	}
}