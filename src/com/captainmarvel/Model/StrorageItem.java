package com.captainmarvel.Model;

public class StrorageItem extends Item
{
    private int itemCapacity;

    public StrorageItem(String itemID, String itemName, String itemDescription, int itemCapacity)
    {
        super(itemID, itemName, itemDescription);
        this.itemCapacity = itemCapacity;
    }

    public int getItemSize()
    {
        return itemCapacity;
    }

    public void setItemSize(int itemCapacity)
    {
        this.itemCapacity = itemCapacity;
    }

}
