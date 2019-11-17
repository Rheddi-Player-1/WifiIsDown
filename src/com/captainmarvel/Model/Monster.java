package com.captainmarvel.Model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import com.captainmarvel.Exceptions.*;

public class Monster
{
    private String monsterID;
    private String monsterName;
    private String monsterDescription;
    private String monsterAttackPhrase;
    private int monsterHealth;
    private int monsterAttack;
    private int minEncounterValue;
    private int maxEncounterValue;
    private Item heldItem;

    public static HashMap<String, Monster> allMonsters;

    public Monster(String monsterID, String monsterName, String monsterDescription, String monsterAttackPhrase,
                   int monsterHealth, int monsterAttack, int minEcnounter, int maxEncounter, Item heldItem)
    {
        this.monsterID = monsterID;
        this.monsterName = monsterName;
        this.monsterDescription = monsterDescription;
        this.monsterAttackPhrase = monsterAttackPhrase;
        this.monsterHealth = monsterHealth;
        this.monsterAttack = monsterAttack;
        this.minEncounterValue = minEcnounter;
        this.maxEncounterValue = maxEncounter;
        this.heldItem = heldItem;
    }

    public String getMonsterID()
    {
        return monsterID;
    }

    public void setMonsterID(String monsterID)
    {
        this.monsterID = monsterID;
    }

    public String getMonsterName()
    {
        return monsterName;
    }

    public void setMonsterName(String monsterName)
    {
        this.monsterName = monsterName;
    }

    public String getMonsterDescription()
    {
        return monsterDescription;
    }

    public void setMonsterDescription(String monsterDescription)
    {
        this.monsterDescription = monsterDescription;
    }

    public String getMonsterAttackPhrase()
    {
        return monsterAttackPhrase;
    }

    public void setMonsterAttackPhrase(String monsterAttackPhrase)
    {
        this.monsterAttackPhrase = monsterAttackPhrase;
    }

    public int getMonsterHealth()
    {
        return monsterHealth;
    }

    public void setMonsterHealth(int monsterHealth)
    {
        this.monsterHealth = monsterHealth;
    }

    public Item getHeldItem()
    {
        return heldItem;
    }

    public void setHeldItem(Item heldItem)
    {
        this.heldItem = heldItem;
    }

    public String inspectMonster()
    {
        return getMonsterDescription();
    }

    public int getMonsterAttack()
    {
        return monsterAttack;
    }

    public void setMonsterAttack(int monsterAttack)
    {
        this.monsterAttack = monsterAttack;
    }

    public int getMinEncounterValue()
    {
        return minEncounterValue;
    }

    public void setMinEncounterValue(int minEncounterValue)
    {
        this.minEncounterValue = minEncounterValue;
    }

    public int getMaxEncounterValue()
    {
        return maxEncounterValue;
    }

    public void setMaxEncounterValue(int maxEncounterValue)
    {
        this.maxEncounterValue = maxEncounterValue;
    }

    public int attack()
    {
        return getMonsterAttack();
    }

    public void receiveDamage(int damage)
    {
        monsterHealth -= damage;
        if(monsterHealth <= 0)
            throw new MonsterDeathException(monsterName);
    }

    public static void generateMonsters()
    {
        try
        {
            File itemInfo = new File("XMLs/Monsters.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(itemInfo);

            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();

            NodeList nList = doc.getElementsByTagName("monster");

            for(int i = 0; i < nList.getLength(); i++)
            {
                Node nNode = nList.item(i);

                String monsterCode = "";
                String monsterName = "";
                String monsterDescription = "";
                String monsterAttackPhase = "";
                int maxEncounter = 0;
                int minEncounter = 0;
                int health = 0;
                int attack  = 0;
                Item heldItem = null;
                if (nNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) nNode;

                    monsterCode = eElement.getAttribute("monsterCode");
                    monsterName = eElement.getElementsByTagName("monsterName").item(0).getTextContent().toUpperCase();
                    monsterDescription = eElement.getElementsByTagName("monsterDescription").item(0).getTextContent();
                    monsterAttackPhase = eElement.getElementsByTagName("monsterAttackPhrase").item(0).getTextContent();
                    maxEncounter = Integer.parseInt(eElement.getElementsByTagName("monsterMaxEncounterValue").item(0).getTextContent());
                    minEncounter = Integer.parseInt(eElement.getElementsByTagName("monsterMinEncounterValue").item(0).getTextContent());
                    health = Integer.parseInt(eElement.getElementsByTagName("monsterHealth").item(0).getTextContent());
                    attack = Integer.parseInt(eElement.getElementsByTagName("monsterAttack").item(0).getTextContent());
                    String tempItemCode = eElement.getElementsByTagName("monsterHeldItem").item(0).getTextContent().toUpperCase();

                    if(!tempItemCode.equalsIgnoreCase("null"))
                        heldItem = Item.allItems.get(tempItemCode);
                    else
                        heldItem = null;

                }

                Monster newMon = new Monster(monsterCode, monsterName, monsterDescription, monsterAttackPhase, health, attack, minEncounter, maxEncounter, heldItem);
                allMonsters.put(newMon.getMonsterID(), newMon);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error! Please try again.\n");
            e.printStackTrace();
        }
    }

}
