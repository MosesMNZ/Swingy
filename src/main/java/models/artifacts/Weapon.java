package models.artifacts;

import models.artifacts.Artifact;

public class Weapon extends Artifact
{
	private int attack = 65;

	public Weapon( String type )
	{
		super( type );
	}

	public int getAttack()
	{
		return ( this.attack );
	}
}
