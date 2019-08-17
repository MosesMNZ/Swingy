package controllers;

import java.util.Random;

import models.HeroStats;
import models.enemies.Ghost;
import models.enemies.Psycho;
import models.enemies.Scarface;
import models.enemies.Enemy;
import models.heroes.Master;
import models.heroes.Hero;
import models.heroes.Protector;
import models.heroes.Warrior;
import controllers.Artifacts;
import models.artifacts.Artifact;
import models.artifacts.Armor;
import models.artifacts.Helm;
import models.artifacts.Weapon;

public class PlayersGenerator 
{

    public Artifact createArtifact( String artifact )
    {
        return ( null );
    }

    public static Hero newPlayer( String hero, String player, HeroStats heroStats, Artifact artifact )
    {
        
        if ( hero.equals( "Warrior" ) )
        {
            return ( new Warrior( player, heroStats, artifact ) );
        } 
        else if ( hero.equals( "Protector" ) )
        {
            return ( new Protector( player, heroStats, artifact ) );
        }
        else if ( hero.equals( "Master" ) )
        {
            return ( new Master( player, heroStats, artifact ) );
        }
        else
            return ( null );
    }

    public static Enemy newEnemy( Hero hero )
    {
        Random random = new Random();
        int enemy = random.nextInt( 3 ) + 1;
        String artifact = Artifacts.randomArtifact();
        int level = 0, attack = 0, defense = 0, hitpoints = 0, xpoints = 0;

        if ( enemy == 1 )
        {
            if ( artifact.equals( "WEAPON" ) )
            {
                Weapon weapon = new Weapon( "Weapon" );
                level = hero.getHeroStats().getLevel();
                attack = 100 + weapon.getAttack();
                defense = 100;
                hitpoints = 100;
                xpoints = 0;
                return ( new Scarface( level, attack, defense, hitpoints, xpoints, weapon ) );
            }
            else if ( artifact.equals( "ARMOR" ) )
            {
                Armor armor = new Armor( "Armor" );
                level = hero.getHeroStats().getLevel();
                attack = 100;
                defense = 100 + armor.getDefense();
                hitpoints = 100;
                xpoints = 0;
                return ( new Scarface(level, attack, defense, hitpoints, xpoints, armor ) );
            }
            else if ( artifact.equals( "HELM" ) )
            {
                Helm helm = new Helm( "Helm" );
                level = hero.getHeroStats().getLevel();
                attack = 100 + helm.getHitPoints();
                defense = 100;
                hitpoints = 100;
                xpoints = 0;
                return ( new Scarface( level, attack, defense, hitpoints, xpoints, helm ) );
            }
        }
        else if ( enemy == 2 )
        {
            if ( artifact.equals( "WEAPON" ) )
            {
                Weapon weapon = new Weapon( "Weapon" );
                level = hero.getHeroStats().getLevel();
                attack = 100 + weapon.getAttack();
                defense = 100;
                hitpoints = 100;
                xpoints = 0;
                return ( new Psycho( level, attack, defense, hitpoints, xpoints, weapon ) );
            }
            else if ( artifact.equals( "ARMOR" ) )
            {
                Armor armor = new Armor( "Armor" );
                level = hero.getHeroStats().getLevel();
                attack = 100;
                defense = 100 + armor.getDefense();
                hitpoints = 100;
                xpoints = 0;
                return ( new Psycho( level, attack, defense, hitpoints, xpoints, armor ) );
            }
            else if ( artifact.equals( "HELM" ) )
            {
                Helm helm = new Helm( "Helm" );
                level = hero.getHeroStats().getLevel();
                attack = 100 + helm.getHitPoints();
                defense = 100;
                hitpoints = 100;
                xpoints = 0;
                return ( new Psycho( level, attack, defense, hitpoints, xpoints, helm ) );
            } 
        }
        else if ( enemy == 3 )
        {
            if ( artifact.equals( "WEAPON" ) )
            {
                Weapon weapon = new Weapon( "Weapon" );
                level = hero.getHeroStats().getLevel();
                attack = 100 + weapon.getAttack();
                defense = 100;
                hitpoints = 100;
                xpoints = 0;
                return ( new Ghost( level, attack, defense, hitpoints, xpoints, weapon ) );
            }
            else if ( artifact.equals( "ARMOR" ) )
            {
                Armor armor = new Armor( "Armor" );
                level = hero.getHeroStats().getLevel();
                attack = 100;
                defense = 100 + armor.getDefense();
                hitpoints = 100;
                xpoints = 0;
                return ( new Ghost( level, attack, defense, hitpoints, xpoints, armor ) );
            }
            else if ( artifact.equals( "HELM" ) )
            {
                Helm helm = new Helm( "Helm" );
                level = hero.getHeroStats().getLevel();
                attack = 100 + helm.getHitPoints();
                defense = 100;
                hitpoints = 100;
                xpoints = 0;
                return ( new Ghost( level, attack, defense, hitpoints, xpoints, helm ) );
            }
        }
        return ( null );
    }
}
