package models.artifacts;

import models.artifacts.Artifact;

public class Helm extends Artifact 
{
	private int hitpoints = 75;

	public Helm( String type )
	{
		super( type );
	}

	public int getHitPoints()
	{
		return ( this.hitpoints );
	}
}
