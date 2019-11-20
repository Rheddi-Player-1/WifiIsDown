package Model;

import Exceptions.*;

import java.util.ArrayList;

public class Player
{
    private String playerName;
    private int playerMaxStress;
    private int playerCurrentStress;
    private int playerAttack;
    private Item playerEquipedItem;
    private Item playerStorageItem;
    private Item playerHeldItem;
    private Rooms currentRooms;
    private int availableStorage;
    private ArrayList<Item> carriedItems;

    //New Game Constructor
    public Player(String playerName, Rooms startingRoom)
    {
        this.playerName = playerName;
        playerMaxStress = 20;
        playerAttack = 5;
        playerCurrentStress = 0;
        playerEquipedItem = null;
        playerStorageItem = null;
        playerHeldItem = null;
        currentRooms = startingRoom;
        availableStorage = 0;
        carriedItems = new ArrayList<>();
    }

    //Load Game Constructor
    public Player(String playerName, int playerCurrentStress, int playerAttack, Item playerEquipedItem,
                  Item playerStorageItem, Item playerHeldItem, Rooms currentRooms, ArrayList<Item> carriedItems, int availableStorage)
    {
        this.playerName = playerName;
        playerMaxStress = 20;
        this.playerAttack = playerAttack;
        this.playerCurrentStress = playerCurrentStress;
        this.playerEquipedItem = playerEquipedItem;
        this.playerStorageItem = playerStorageItem;
        this.playerHeldItem = playerHeldItem;
        this.currentRooms = currentRooms;
        this.carriedItems = carriedItems;
        this.availableStorage = availableStorage;
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

    public Item getPlayerEquipedItem()
    {
        return playerEquipedItem;
    }

    public void setPlayerEquipedItem(WeaponItem playerEquipedItem)
    {
        this.playerEquipedItem = playerEquipedItem;
    }

    public Item getPlayerStorageItem()
    {
        return playerStorageItem;
    }

    public void setPlayerStorageItem(StorageItem playerStorageItem)
    {
        this.playerStorageItem = playerStorageItem;
        availableStorage = playerStorageItem.getItemCapacity();
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

    public ArrayList<Item> getCarriedItems()
    {
        return carriedItems;
    }

    public void setCarriedItems(ArrayList<Item> carriedItems)
    {
        this.carriedItems = carriedItems;
    }

    public int attack()
    {
        return getPlayerAttack();
    }

    public void setPlayerEquipedItem(Item playerEquipedItem)
    {
        this.playerEquipedItem = playerEquipedItem;
    }

    public void setPlayerStorageItem(Item playerStorageItem)
    {
        this.playerStorageItem = playerStorageItem;
    }

    public int getAvailableStorage()
    {
        return availableStorage;
    }

    public void setAvailableStorage(int availableStorage)
    {
        this.availableStorage = availableStorage;
    }

    public void receiveDamage(int damage)
    {
        playerCurrentStress += damage;
        if(playerCurrentStress >= getPlayerMaxStress())
            throw new PlayerDeathException();
    }

    public void addItem(Item item)
    {
        if(item.getItemSize() <= availableStorage)
        {
            carriedItems.add(item);
            availableStorage -= item.getItemSize();
        }
        else
            throw new OverEncumbered();
    }

    public Item removeItem(String itemName)
    {
        Item removed = null;
        boolean isHere = false;
        int i = 0;
        while(i < carriedItems.size() || isHere)
        {
            if(carriedItems.get(i).getItemName().equalsIgnoreCase(itemName))
            {
                removed = carriedItems.get(i);
                carriedItems.remove(i);
                availableStorage += removed.getItemSize();
                isHere = true;
            }
            else
                i++;
        }

        if(!isHere)
            throw new ItemDoesNotExist();

        return removed;
    }

    public Item equip(Item item)
    {
        if(playerEquipedItem == null)
        {
            playerEquipedItem = item;
            return null;
        }
        else
        {
            Item tempItem = playerEquipedItem;
            playerEquipedItem = item;
            return tempItem;

        }

    }

    public boolean useItem(Item item)
    {
        try
        {
            ConsumableItem hItem = (ConsumableItem) item;
            if(hItem.getHealingPoints() > (playerMaxStress - playerCurrentStress))
                playerCurrentStress = playerMaxStress;
            else
                playerCurrentStress += hItem.getHealingPoints();

            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

}
