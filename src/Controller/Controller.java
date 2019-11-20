package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import Model.*;
import View.*;
import Exceptions.*;

public class Controller
{
    private Player user;
    private Console view;
    private Scanner input;
    private Monster mainEnemy;
    private ArrayList<Item> carriedItems = new ArrayList<Item>();
    private boolean isMonsterDead;

    public Controller()
    {
        view = new Console();
        input = new Scanner(System.in);
        mainEnemy = null;
        isMonsterDead = false;

        Item.readItemXML();
        Rooms.readRoomsXML();
        Puzzle.readPuzzleXML();
        Monster.generateMonsters();
    }

    public void titleScreen()
    {
        view.print(" __      __.______________.___  ");
        view.print("/  \\    /  \\   \\_   _____/|   | ");
        view.print("\\   \\/\\/   /   ||    __)  |   | ");
        view.print(" \\        /|   ||     \\   |   | ");
        view.print("  \\__/\\  / |___|\\___  /   |___| ");
        view.print("       \\/           \\/         ");
        view.print("          .__                   ");
        view.print("          |__| ______          ");
        view.print("          |  |/  ___/           ");
        view.print("          |  |\\___ \\            ");
        view.print("          |__/____  >           ");
        view.print("                  \\/            ");
        view.print("________                        ");
        view.print("\\______ \\   ______  _  ______   ");
        view.print(" |    |  \\ /  _ \\ \\/ \\/ /    \\  ");
        view.print(" |    `   (  <_> )     /   |  \\ ");
        view.print("/_______  /\\____/ \\/\\_/|___|  / ");
        view.print("        \\/                  \\/  ");
    }

    public void gameStart()
    {
        view.print("NEW GAME\nCONTINUE");
        String userInput = input.nextLine().toUpperCase();
        if(userInput.contains("NEW GAME"))
            newGame();
        else if (userInput.contains("CONTINUE"))
            loadGame();
        else
        {
            view.print("Invalid Input. Please try again...");
            gameStart();
        }
    }


    public void newGame()
    {
        view.print("----------------------------------------------------------------------------------------------------");
        view.print("You wake up in the Kaufman Library...\n... and the WIFI is down!");
        view.print("----------------------------------------------------------------------------------------------------");

        view.print("Enter your name: ");
        String name = input.nextLine();

        user = new Player(name, Rooms.rooms.get("R00"));
    }

    public void mainMenu()
    {
        boolean useVend = false;
        view.print(user.getCurrentRooms().getRoomDescription());
        view.print("Type \"help\" for assistance.");
        String userInput = input.nextLine().toUpperCase();

        if(userInput.contains("INVENTORY"))
            inventory();
        else if(userInput.contains("INVESTIGATE"))
        {
            if(isMonsterDead = false)
                preBattlePhase();
            else if (!user.getCurrentRooms().getVendingItem().equalsIgnoreCase("NONE"))
            {
                view.print("There's a vending machine here, do you want to use it?");
                VendingItem vendingMachine  = (VendingItem) Item.allItems.get(user.getCurrentRooms().getVendingItem());
                for(int i = 0; i < vendingMachine.getHeldItem().size(); i++)
                    view.print(vendingMachine.getHeldItem().get(i).getItemName() + "\n");

                view.print("Nevermind...");
                String userInputToo = input.nextLine().toUpperCase();

                if(userInputToo.contains("NEVERMIND"))
                {
                    useVend = true;
                    mainMenu();
                }
                else
                {
                    boolean isThere = false;
                    int i = 0;
                    while(!isThere || i < vendingMachine.getHeldItem().size())
                    {
                        if(vendingMachine.getHeldItem().get(i).getItemName().toUpperCase().contains(userInputToo))
                        {
                            try
                            {
                                user.addItem(vendingMachine.getHeldItem().get(i));
                                isThere = true;
                                useVend = true;
                                mainMenu();
                            }
                            catch (OverEncumbered e)
                            {
                                view.print(e.getMessage());
                                view.print("Try again later");
                                mainMenu();
                            }
                        }
                        else
                        {
                            view.print("That's not an option. Try again later.");
                            mainMenu();
                        }
                    }
                }
            }
            else if(useVend == true || user.getCurrentRooms().getVendingItem().equalsIgnoreCase("NONE"))
            {
                ArrayList<Item> roomItems = user.getCurrentRooms().getRoomItems();

                for(int i = 0; i < roomItems.size(); i++)
                {
                    view.print(roomItems.get(i).getItemName() + "\n" + roomItems.get(i).getItemDescription() + "\n");
                }
                view.print("Do you want to pick up anything?");
                String userInputToo = input.nextLine().toUpperCase();

                try
                {
                    boolean isItThere = false;
                    int i = 0;
                    while(!isItThere || i < roomItems.size())
                    {
                        if(roomItems.get(i).getItemName().toUpperCase().contains(userInputToo) && user.getCarriedItems().contains(Item.allItems.get("KI06")))
                        {
                            user.getCurrentRooms().removeFromRoom(roomItems.get(i));
                            user.removeItem("KI06");
                            user.addItem(roomItems.get(i));
                            view.print(userInputToo + " has been added to your inventory");
                            mainMenu();
                        }
                        else
                        {
                            view.print("That's not here!");
                            mainMenu();
                        }
                    }
                }
                catch(OverEncumbered e)
                {
                    view.print(e.getMessage());
                    mainMenu();
                }

            }

        }
        else if(userInput.contains("MOVE"))
        {
            changeRooms();
        }
        else if(userInput.contains("DATA"))
        {
            view.print("SAVE GAME\nLOAD GAME");
            String userOtherInput = input.nextLine().toUpperCase();

            if(userOtherInput.contains("SAVE"))
                saveGame();
            else if(userOtherInput.contains("LOAD"))
                loadGame();
            else
            {
                view.print("I do not understand what you're saying");
                mainMenu();
            }
        }
        else if(userInput.contains("QUIT"))
            System.exit(0);
        else if(userInput.contains("HELP"))
        {
            view.print("Inventory: Check and use items that you are carrying.\nInvestigate: Search the room you are currently in.\nMove: " +
                    "Move to another room that's connected.\nData: Load or save current game.\nLeave: Quit current game.");
            mainMenu();

        }
        else
        {
            view.print("What is \""+ userInput + "\"? That's not a thing...");
            mainMenu();
        }

    }


    public void saveGame()
    {
        try
        {
            view.print(WriteRead.saveData(user));
        }
        catch (Exception e)
        {
            view.print("Error Saving data, please try again");
            mainMenu();
        }

    }

    public void loadGame()
    {
        try
        {
            view.print("----------------------------------------------------------------------------------------------------");
            view.print("CURRENT SAVES:");
            view.print("----------------------------------------------------------------------------------------------------");
            view.print(WriteRead.saveToString());
            view.print("----------------------------------------------------------------------------------------------------");
            view.print("\nEnter the file name");
            String userFile = input.nextLine();
            user = WriteRead.loadData(userFile);
        }
        catch (FileDoesNotExist e)
        {
            view.print(e.getMessage());
            loadGame();
        }
        catch(Exception e)
        {
            view.print("There has been an error, please try again.");
            gameStart();
        }
    }

    public void preBattlePhase()
    {
        int randomMonster = 0;

        if(user.getCurrentRooms().getRoomBoard().equalsIgnoreCase("NONE"))
        {
            int interuptedMax = Monster.allMonsters.get("M0" + (Monster.allMonsters.size() - 2)).getMaxEncounterValue();
            int interupted = (int) (Math.random() * interuptedMax);

            randomMonster = (int) (Math.random() * (Monster.allMonsters.get("M0" + (Monster.allMonsters.size() - 3)).getMaxEncounterValue()));

            int i = 0;
            while(i < Monster.allMonsters.size() - 1 || mainEnemy != null)
            {
                Monster temp = Monster.allMonsters.get("MO" + i);
                if(randomMonster >= temp.getMinEncounterValue() || randomMonster <= temp.getMaxEncounterValue())
                    mainEnemy = temp;
                else
                    i++;
            }
            if(interupted >= Monster.allMonsters.get("MO" +(Monster.allMonsters.size() - 2)).getMinEncounterValue() ||
                    interupted <= Monster.allMonsters.get("MO" +(Monster.allMonsters.size()- 2)).getMaxEncounterValue())
            {
                Monster secondaryEnemy = Monster.allMonsters.get("MO" +(Monster.allMonsters.size() - 1));

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
            randomMonster = (int) (Math.random() * (Monster.allMonsters.get("M0" + (Monster.allMonsters.size() - 2)).getMaxEncounterValue()));
            int i = 0;
            while(i < Monster.allMonsters.size() - 1 || mainEnemy != null)
            {
                Monster temp = Monster.allMonsters.get("MO" + i);
                if(randomMonster >= temp.getMinEncounterValue() || randomMonster <= temp.getMaxEncounterValue())
                    mainEnemy = temp;
                else
                    i++;
            }

            int mainEnemyInt = (int) (Math.random() * (20 + 1) - 1);
            int playerInt = (int) (Math.random() * (20 + 1) - 1);

            battlePhase(mainEnemy, mainEnemyInt, playerInt);
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
                    playerInterruptedBattle(enemy1, enemy1Odds, enemy2, enemy2Odds, userOdds);
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
                    playerInterruptedBattle(enemy1, enemy1Odds, enemy2, enemy2Odds, userOdds);
            }
            else
                {
                    playerInterruptedBattle(enemy1, enemy1Odds, enemy2, enemy2Odds, userOdds);

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
        catch(PlayerDeathException e2)
        {
            view.print(e2.getMessage());
            titleScreen();
            gameStart();
        }

        battlePhaseInterrupted(enemy1, enemy1Int, enemy2, enemy2Int, playerInt);
    }

    public void battlePhase(Monster enemy, int enemyInt, int playerInt)
    {
        int enemyOdds = (int) (Math.random() * (20 + 1) - 1);
        int userOdds = (int) (Math.random() * (20 + 1) - 1);
        view.print("----------------------------------------------------------------------------------------------------");
        view.print("Your Current Stress: " + user.getPlayerCurrentStress());
        view.print("Enemy One: " + enemy.getMonsterName());
        view.print("----------------------------------------------------------------------------------------------------");
        try
        {
            if(playerInt > enemyInt)
            {
                playerBattle(enemy, enemyOdds, userOdds);
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
            view.print(e1.getMessage());
            isMonsterDead = true;
            mainMenu();

        }
        catch(PlayerDeathException e2)
        {
            view.print(e2.getMessage());
            titleScreen();
            gameStart();
        }
        battlePhase(enemy, enemyInt, playerInt);
    }

    public void enemyAttack(Monster enemy)
    {
        view.print(enemy.getMonsterName() + " attacks!\n" + enemy.getMonsterAttackPhrase() + "\nYou receive " + enemy.attack() + " points of damage!");
        user.receiveDamage(enemy.attack());
    }

    public void enemyMisses(Monster enemy)
    {
        view.print(enemy.getMonsterName() + " attacks!\n" + enemy.getMonsterAttackPhrase() + "\nThe attack misses!");
    }

    public void playerBattle(Monster enemy, int enemyOdds, int userOdds)
    {
        view.print("What will you do?");
        String userDecision = input.nextLine().toUpperCase();

        if(userDecision.contains("ATTACK"))
        {
            if(userOdds > enemyOdds)
            {
                view.print("You attack " + enemy.getMonsterName() + "! \nYou deal " + user.attack() + " points of damage!");
                enemy.receiveDamage(user.attack());
            }
            else
                view.print(enemy.getMonsterName() + " dodges your attack!");
        }
        else if(userDecision.contains("HELP"))
        {
            view.print("Attack: Attack the current enemy.\nUse Item: Change equipment and use consumable items.\nIgnore: " +
                    "Attempt to run from the fight.");
            playerBattle(enemy, enemyOdds, userOdds);

        }
        else if(userDecision.contains("USE ITEM"))
            inventory();
        else if(userDecision.contains("IGNORE"))
        {
            int pickANum = (int)(Math.random() * (20 + 1) - 1);

            if(pickANum == userOdds || userOdds > pickANum)
            {
                view.print("You ran from the fight!");
                isMonsterDead = true;
                mainMenu();
            }
            else
            {
                view.print("You could not get away!");
                battlePhase(enemy, enemyOdds, userOdds);
            }
        }
        else
        {
            view.print("That monster does not exist! The enemy gets closer and prepares for an all out assault!");
            battlePhase(enemy, enemyOdds, userOdds);
        }
    }

    public void playerInterruptedBattle(Monster enemy1, int enemy1Odds, Monster enemy2, int enemy2Odds, int userOdds)
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
                    view.print("You attack " + enemy1.getMonsterName() + "! \nYou deal " + user.attack() + " points of damage!");
                    enemy1.receiveDamage(user.attack());
                }
                else
                    view.print(enemy1.getMonsterName() + " dodges your attack!");
            }
            else if(userDecision.contains(enemy2.getMonsterName().toUpperCase()))
            {
                if(userOdds > enemy2Odds)
                {
                    view.print("You attack " + enemy2.getMonsterName() + "! \nYou deal " + user.attack() + " points of damage!");
                    enemy2.receiveDamage(user.attack());
                }
                else
                    view.print(enemy2.getMonsterName() + " dodges your attack!");
            }
            else
            {
                view.print("That monster does not exist! You're lack of speed causes you to miss an opportunity to attack!");
                battlePhaseInterrupted(enemy1, enemy1Odds, enemy2, enemy2Odds, userOdds);
            }
        }
        else if(userDecision.contains("USE ITEM"))
            inventory();
        else if(userDecision.contains("HELP"))
        {
            view.print("Attack: Attack one of the two enemies.\nUse Item: Change equipment and use consumable items.\nIgnore: " +
                    "Attempt to run from the fight.");
            playerInterruptedBattle(enemy1, enemy1Odds, enemy2, enemy2Odds, userOdds);

        }
        else if(userDecision.contains("IGNORE"))
        {
            int pickANum = (int)(Math.random() * (20 + 1) - 1);

            if(pickANum == userOdds || userOdds > pickANum)
            {
                view.print("You ran from the fight!");
                isMonsterDead = true;
                mainMenu();
            }
            else
            {
                view.print("You could not get away!");
                battlePhaseInterrupted(enemy1, enemy1Odds, enemy2, enemy2Odds, userOdds);
            }
        }
        else
        {
            view.print("That does not exist! The enemy gets closer and prepares for an all out assault!");
            battlePhaseInterrupted(enemy1, enemy1Odds, enemy2, enemy2Odds, userOdds);
        }
    }

    public void changeRooms()
    {
        view.print("Where do you want to go? Below are the list of rooms that you can choose from.\n");
        for (String r: user.getCurrentRooms().getRoomConnections())
        {
            view.print(r);
        }

        String userInput = input.nextLine().toUpperCase();
        String currentRoom = userInput.replaceAll("\\s+", ""); //Remove extra spacings in the input
        if (user.getCurrentRooms().getRoomConnections().contains(userInput))
        {
            Rooms room = Rooms.rooms.get(userInput);
            if(user.getPlayerEquipedItem().getItemName().contains(room.getKey()))
            {
            	isMonsterDead = false;
                user.setCurrentRooms(Rooms.rooms.get(currentRoom));
                mainMenu();
            }
            else
            {
            	mainMenu();
            }
        }
        else
        {
            view.print("That room does not exit.");
            changeRooms();
        }
    }

    public void inventory()
    {
        for (Item item : user.getCarriedItems())
        {
            view.print(item.getItemName());
        }

        view.print("Which item do you want to pick?");
        String userInput = input.nextLine().replaceAll("\\s+", "");
        Item i = Item.allItems.get(userInput);
        if (user.getCarriedItems().contains(i))
        {
            view.print("Do you want to drop, use, or equip an item?");
            String userinput = input.nextLine();
            if(userinput.equalsIgnoreCase("drop")) user.removeItem(i.getItemName());
            if(userinput.equalsIgnoreCase("use")) user.useItem(i);
            if(userinput.equalsIgnoreCase("equip")) user.equip(i);
        }

    }

}
