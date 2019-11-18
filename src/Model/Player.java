package Model;

import com.captainmarvel.Exceptions.*;

public class Player
{
    private String playerName;
    private int playerMaxStress;
    private int playerCurrentStress;
    private int playerAttack;
    private Weapon playerEquipedItem;
    private StorageItem playerStorageItem;
    private Item playerHeldItem;
    private Room currentRoom;

    //New Game Constructor
    public Player(String playerName)
    {
        this.playerName = playerName;
        playerMaxStress = 20;
        playerAttack = 5;
        playerCurrentStress = 0;
        playerEquipedItem = null;
        playerStorageItem = null;
        playerEquipedItem = null;
        currentRoom = //Room.allRooms.get(R00);
    }

    //Load Game Constructor
    public Player(String playerName, int playerMaxStress, int playerCurrentStress, int playerAttack, Weapon playerEquipedItem,
                  StorageItem playerStorageItem, Item playerHeldItem, Room currentRoom)
    {
        this.playerName = playerName;
        this.playerMaxStress = playerMaxStress;
        this.playerAttack = playerAttack;
        this.playerCurrentStress = playerCurrentStress;
        this.playerEquipedItem = playerEquipedItem;
        this.playerStorageItem = playerStorageItem;
        this.playerEquipedItem = playerEquipedItem;
        this.currentRoom = currentRoom;
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

    public Weapon getPlayerEquipedItem()
    {
        return playerEquipedItem;
    }

    public void setPlayerEquipedItem(Weapon playerEquipedItem)
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

    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom)
    {
        this.currentRoom = currentRoom;
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
