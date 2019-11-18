package com.captainmarvel.Controller;

import java.util.Scanner;
import com.captainmarvel.*;

public class Controller
{
    private Player user;
    private Console view;
    private Scanner input;
    private Monster enemy1;

    public Controller()
    {
        user = new Player();
        view = new Console();
        input = new Scanner(System.in);
        mainEnemy = null;

        Item.readItems();
        Rooms.readRoomXML();
        Puzzle.readPuzzleXML();
        Monster.generateMonsters();
    }

    //Methods print commands for game
	public void helpCommand()
	{
		System.out.println("\n****Commands****\n"
				+ "\nGo + Direction (Ex. North, South, East, OR West)"
				+ "\nDrop + Item Name"
				+ "\nGet + Item Name"
				+ "\nExamine (Examines Puzzle)"
				+ "\nSolve (Solve Puzzle)"
				+ "\nHint (Hint for Puzzle)"
				+ "\nAttack (Attack Monster)"
				+ "\nSave (Save Game)"
				+ "\nQuit (Quit Game)"
				+ "\nHep (Help Commands)");
	}			
	
    //Method that navigates game
	public void game(String value)
	{		
		while (true) 
		{ 	
			value = value.toLowerCase();
			String[] command = value.split(" ");
			String in = command[0];
			
			switch(command[0])
			{
			case "get":
				if (command.length >= 2) 
				{
					String temp = "";
					for (int i = 1; i < command.length; i++) 
					{
						temp += command[i] + " ";
					}
					temp = temp.trim();
					//ADD METHOD
				} 
				break;
			case "drop":
				if (command.length >= 2) 
				{
					String temp = "";
					for (int i = 1; i < command.length; i++) 
					{
						temp += command[i] + " ";
					}
					temp = temp.trim();
					//ADD METHOD
				} 
				break;
			case "go":
				if (command.length >= 2) 
				{
					String temp = "";
					for (int i = 1; i < command.length; i++) 
					{
						temp += command[i] + " ";
					}
					temp = temp.trim();
					//ADD METHOD
				} 
				break;
			case "inventory":
				//ADD METHOD
				break;
			case "examine":
				if (command.length >= 2) 
				{
					String temp = "";
					for (int i = 1; i < command.length; i++) 
					{
						temp += command[i] + " ";
					}
					temp = temp.trim();
					//ADD METHOD
				} 
				break;
			case "hint":
				if (command.length >= 2) 
				{
					String temp = "";
					for (int i = 1; i < command.length; i++) 
					{
						temp += command[i] + " ";
					}
					temp = temp.trim();
					//ADD METHOD
				} 
				break;
			case "solve":
				if (command.length >= 2) 
				{
					String temp = "";
					for (int i = 1; i < command.length; i++) 
					{
						temp += command[i] + " ";
					}
					temp = temp.trim();
					//ADD METHOD
				} 
				break;
			case "attack":
				if (command.length >= 2) 
				{
					String temp = "";
					for (int i = 1; i < command.length; i++) 
					{
						temp += command[i] + " ";
					}
					temp = temp.trim();
					//ADD METHOD
				}
				break;
			case "save":
				if (command.length >= 2) 
				{
					//ADD METHOD
				}
				break;
			case "quit":
				System.out.println("Game Has Ended!");
				System.exit(0);
				break;
			case "help":
				helpCommand();
				break;
			default:
				System.out.println("Sorry, wrong input.");
				break;
			}
		}
	}
    
    public void preBattlePhase()
    {
        int randomMonster = 0;

        if(/*!user.getCurrentRoom().hasboard()*/) //Needs to check to see if the current room does not have a board
        {
            int interuptedMax = Monster.allMonsters.get("M0" + (Monster.allMonsters.length - 2);
            int interupted = (int) (Math.random() * interuptedMax);

            randomMonster = (int) (Math.random() * (Monster.allMonsters.get("M0" + (Monster.allMonsters.length - 3).getMaxEncounter));

            int i = 0;
            while(i < Monster.allMonsters.length - 1 || mainEnemy != null)
            {
                Monster temp = Monster.allMonsters.get("MO" + i);
                if(randomMonster >= temp.getMinEncounter() || randomMonster <= temp.getMaxEncounter)
                    mainEnemy = temp;
                else
                    i++;
            }
            if(interupted >= Monster.allMonsters.get("MO" +(Monster.allMonsters.length - 2)).getMinEncounterValue() ||
                    interupted <= Monster.allMonsters.get("MO" +(Monster.allMonsters.length - 2)).getMaxEncounterValue())
            {
                Monster secondaryEnemy = Monsters.allMonsters.get("MO" +(Monster.allMonsters.length - 1));

                int mainEnemyInt = (int) (Math.random() * (20 + 1) - 1);
                int secondaryEnemyInt = (int) (Math.random() * (20 + 1) - 1);
                int userInt = (int) (Math.random() * (20 + 1) - 1);

                battlePhaseInterrupted(mainEnemy, mainEnemyInt, secondaryEnemy, secondaryEnemyInt, userInt);
            }
            else
            {
                int mainEnemyInt = (int) (Math.random() * (20 + 1) - 1);
                int userInt = (int) (Math.random() * (20 + 1) - 1);

                battlePhase(mainEnemy, mainEnemyInt, userInt);

            }
        }
        else
        {
            randomMonster = (int) (Math.random() * (Monster.allMonsters.get("M0" + (Monster.allMonsters.length - 2).getMaxEncounter));
            int i = 0;
            while(i < Monster.allMonsters.length - 1 || enemy1 != null)
            {
                Monster temp = Monster.allMonsters.get("MO" + i);
                if(randomMonster >= temp.getMinEncounter() || randomMonster <= temp.getMaxEncounter)
                    enemy1 = temp;
                else
                    i++;
            }

            int enemy1Int = (int) (Math.random() * (20 + 1) - 1);
            int playerInt = (int) (Math.random() * (20 + 1) - 1);

            battlePhase(enemy1, enemy1Int, playerInt);
        }

    }

    public void battlePhaseInterrupted(Monster enemy1, int enemy1Int, Monster enemy2, int enemy2Int, int playerInt)
    {
        int enemy1Odds = (int) (Math.random() * (20 + 1) - 1);
        int enemy2Odds = (int) (Math.random() * (20 + 1) - 1);
        int userOdds = (int) (Math.random() * (20 + 1) - 1);
        view.print("----------------------------------------------------------------------------------------------------");
        view.print("Your Current Stress: " + user.getPlayerCurrentStress());
        view.print("Enemy One: " + enemy1.getMonsterName());
        view.print("Enemy Two: " + enemy2.getMonsterName());
        view.print("----------------------------------------------------------------------------------------------------");
        try
        {
            if(enemy1Int > enemy2Int || enemy1Int > playerInt)
            {
                if(enemy1Odds > userOdds)
                    enemyAttack(enemy1);
                else
                    enemyMisses(enemy1);
                if(enemy2Int > playerInt)
                {
                    if(enemy2Odds > userOdds)
                        enemyAttack(enemy2);
                    else
                        enemyMisses(enemy2);
                }
                else
                    playerInterruptedBattle(enemy1, enemy1Odds, enemy2, enemy2Odds);
            }
            else if(enemy2Int > enemy1Int || enemy2Int > playerInt)
            {
                if (enemy2Odds > userOdds)
                    enemyAttack(enemy2);
                else
                    enemyMisses(enemy2);
                if (enemy1Int > playerInt)
                {
                    if (enemy1Odds > userOdds)
                        enemyAttack(enemy1);
                    else
                        enemyMisses(enemy1);
                }
                else
                {
                    playerInterruptedBattle(enemy1, enemy1Odds, enemy2, enemy2Odds);
                }
            else
                {
                    playerInterruptedBattle(enemy1, enemy1Odds, enemy2, enemy2Odds);

                    if(enemy1Int > enemy2Int)
                    {
                        if(enemy1Odds > userOdds)
                            enemyAttack(enemy1);
                        else
                            enemyMisses(enemy1);
                    }
                    else
                    {
                        if(enemy2Odds > userOdds)
                            enemyAttack(enemy2);
                        else
                            enemyMisses(enemy2);
                    }
                }

            }
        }
        catch(MonsterDeathException e1)
        {
            if(enemy1.getMonsterHealth() <= 0)
            {
                view.print(enemy1.getMonsterName() + " has been defeated!");
                battlePhase(enemy2, enemy2Int, playerInt);
            }
            else
            {
                view.print(enemy2.getMonsterName() + " has been defeated!");
                battlePhase(enemy1, enemy1Int, playerInt);
            }
        }
        catch(PlayDeathException e2)
        {
            //Controller method for loading or starting a new game
        }

        battlePhaseInterrupted(enemy1, enemy1Int, enemy2, enemy2Int, playerInt);
    }

    public void battlePhase(Monster enemy, int enemyInt, int playerInt)
    {
        int enemyOdds = (int) (Math.random() * (20 + 1) - 1);
        int userOdds = (int) (Math.random() * (20 + 1) - 1);
        view.print("----------------------------------------------------------------------------------------------------");
        view.print("Your Current Stress: " + user.getPlayerCurrentStress());
        view.print("Enemy One: " + enemy1.getMonsterName());
        view.print("----------------------------------------------------------------------------------------------------");
        try
        {
            if(playerInt > enemyInt)
            {
                playerBattle(enemy, enemyOdds)
            }
            else
            {
                if(enemyOdds > userOdds)
                    enemyAttack(enemy);
                else
                    enemyMisses(enemy);
            }

        }
        catch(MonsterDeathException e1)
        {

            view.print(enemy1.getMonsterName() + " has been defeated!");
            //Controller method for returning to room interaction

        }
        catch(PlayDeathException e2)
        {
            //Controller method for loading or starting a new game
        }
        battlePhase(enemy1, enemy1Int, playerInt);
    }

    public void enemyAttack(Monster enemy)
    {
        view.print(enemy.getName() + " attacks!\n" + enemy.getMonsterAttackPhrase() + "\nYou receive " + enemy.attck() + " points of damage!");
        user.receiveDamage(enemy.attack());
    }

    public void enemyMisses(Monster enemy)
    {
        view.print(enemy.getName() + " attacks!\n" + enemy.getMonsterAttackPhrase() + "\nThe attack misses!");
    }

    public void playerBattle(Monster enemy, int emenyOdds)
    {
        view.print("What will you do?");
        String userDecision = input.nextLine().toUpperCase();

        if(userDecision.contains("ATTACK"))
        {
            if(userOdds > enemyOdds)
            {
                view.print("You attack " + enemy.getMonsterName() + "! \nYou deal " + user.playerAttack() + " points of damage!");
                enemy1.receiveDamage(user.playerAttack());
            }
            else
                view.print(enemy1.getMonsterName() + " dodges your attack!");
        }
        else if(userDecision.contains("USE ITEM"))
        {
            //controller method for items
            
        }
        else if(userDecision.containts("IGNORE"))
        {
            //controller method for room interactions
        }
        else
            view.print("That monster does not exist! The enemy gets closer and prepares for an all out assault!");
    }

    public void playerInterruptedBattle(Monster enemy1, int enemy1Odds, Monster enemy2, int enemy2Odds)
    {
        view.print("What will you do?");
        String userDecision = input.nextLine().toUpperCase();

        if(userDecision.contains("ATTACK"))
        {
            view.print("Who will you attack?");
            userDecision = input.nextLine().toUpperCase();
            if(userDecision.contains(enemy1.getMonsterName().toUpperCase()))
            {
                if(userOdds > enemy1Odds)
                {
                    view.print("You attack " + enemy1.getMonsterName() + "! \nYou deal " + user.playerAttack() + " points of damage!");
                    enemy1.receiveDamage(user.playerAttack());
                }
                else
                    view.print(enemy1.getMonsterName() + " dodges your attack!");
            }
            else if(userDecision.contains(enemy2.getMonsterName().toUpperCase()))
            {
                if(userOdds > enemy2Odds)
                {
                    view.print("You attack " + enemy2.getMonsterName() + "! \nYou deal " + user.playerAttack() + " points of damage!");
                    enemy2.receiveDamage(user.playerAttack());
                }
                else
                    view.print(enemy2.getMonsterName() + " dodges your attack!");
            }
            else
                view.print("That monster does not exist! You're lack of speed causes you to miss an opportunity to attack!");
        }
        else if(userDecision.containts("USE ITEM"))
        {
            //controller method for items
        }
        else if(userDecision.containts("IGNORE"))
        {
            //controller method for room interactions
        }
        else
            view.print("That monster does not exist! The enemy gets closer and prepares for an all out assault!");
    }

}
