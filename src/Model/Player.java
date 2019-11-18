package Model;

import Exceptions.*;

public class Player
{
    private String playerName;
    private int playerMaxStress;
    private int playerCurrentStress;
    private int playerAttack;
    private WeaponItem playerEquipedItem;
    private StorageItem playerStorageItem;
    private Item playerHeldItem;
    private Rooms currentRooms;

    //New Game Constructor
    public Player(String playerName)
    {
        this.playerName = playerName;
        playerMaxStress = 20;
        playerAttack = 5;
        playerCurrentStress = 0;
        playerEquipedItem = null;
        playerStorageItem = null;
        playerHeldItem = null;
        currentRooms = //Rooms.allRoomss.get(R00);
    }

    //Load Game Constructor
    public Player(String playerName, int playerMaxStress, int playerCurrentStress, int playerAttack, WeaponItem playerEquipedItem,
                  StorageItem playerStorageItem, Item playerHeldItem, Rooms currentRooms)
    {
        this.playerName = playerName;
        this.playerMaxStress = playerMaxStress;
        this.playerAttack = playerAttack;
        this.playerCurrentStress = playerCurrentStress;
        this.playerEquipedItem = playerEquipedItem;
        this.playerStorageItem = playerStorageItem;
        this.playerHeldItem = playerHeldItem;
        this.currentRooms = currentRooms;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public int getPlayerMaxStress()
    {
        return playerMaxStress;
    }

    public void setPlayerMaxStress(int playerMaxStress)
    {
        this.playerMaxStress = playerMaxStress;
    }

    public int getPlayerCurrentStress()
    {
        return playerCurrentStress;
    }

    public void setPlayerCurrentStress(int playerCurrentStress)
    {
        this.playerCurrentStress = playerCurrentStress;
    }

    public int getPlayerAttack()
    {
        return playerAttack;
    }

    public void setPlayerAttack(int playerAttack)
    {
        this.playerAttack = playerAttack;
    }

    public WeaponItem getPlayerEquipedItem()
    {
        return playerEquipedItem;
    }

    public void setPlayerEquipedItem(WeaponItem playerEquipedItem)
    {
        this.playerEquipedItem = playerEquipedItem;
    }

    public StorageItem getPlayerStorageItem()
    {
        return playerStorageItem;
    }

    public void setPlayerStorageItem(StorageItem playerStorageItem)
    {
        this.playerStorageItem = playerStorageItem;
    }

    public Item getPlayerHeldItem()
    {
        return playerHeldItem;
    }

    public void setPlayerHeldItem(Item playerHeldItem)
    {
        this.playerHeldItem = playerHeldItem;
    }

    public Rooms getCurrentRooms()
    {
        return currentRooms;
    }

    public void setCurrentRooms(Rooms currentRooms)
    {
        this.currentRooms = currentRooms;
    }

    public int attack()
    {
        return getPlayerAttack();
    }

    public void receiveDamage(int damage)
    {
        playerCurrentStress += damage;
        if(playerCurrentStress >= getPlayerMaxStress())
            throw new PlayerDeathException();
    }


}
