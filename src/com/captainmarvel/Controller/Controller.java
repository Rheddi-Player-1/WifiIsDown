package com.captainmarvel.Controller;

import java.util.Scanner;
import com.captainmarvel.*;

public class Controller
{
    private Player user;
    private Console view;
    private Scanner input;

    public Controller()
    {
        user = new Player();
        view = new Console();
        input = new Scanner(System.in);

        Item.readItems();
        Rooms.readRoomXML();
        Puzzle.readPuzzleXML();
        Monster.generateMonsters();
    }


    public void preBattlePhase()
    {
        int interuptedMax = Monster.allMonsters.get("M0" + (Monster.allMonsters.length - 2);
        int interupted = (int) (Math.random() * interuptedMax);

        int randomMonster = (int) (Math.random() * (Monster.allMonsters.get("M0" + (Monster.allMonsters.length - 3).getMaxEncounter));
        Monster enemy1 = null;
        int i = 0;
        while(i < Monster.allMonsters.length - 1 || enemy1 != null)
        {
            Monster temp = Monster.allMonsters.get("MO" + i);
            if(randomMonster >= temp.getMinEncounter() || randomMonster <= temp.getMaxEncounter)
                enemy1 = temp;
            else
                i++;
        }
        if(interupted >= Monster.allMonsters.get("MO" +(Monster.allMonsters.length - 2)).getMinEncounterValue() ||
                interupted <= Monster.allMonsters.get("MO" +(Monster.allMonsters.length - 2)).getMaxEncounterValue())
        {

        }
    }

    public void battlePhaseInterrupted(Monster enemy1, int enemy1Int, Monster enemy2, int enemy2Int, int playerInt)
    {

    }

    public void battlePhase(Monster enemy1, int enemy1Int, int playerInt)
    {

    }
}
