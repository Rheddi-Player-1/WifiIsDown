package com.captainmarvel.Model;

public class ConsumableItem extends Item
{
    private int itemSize;
    private double itemRecovery;

    public ConsumableItem(String itemID, String itemName, String itemDescription, int itemSize, double itemRecovery)
    {
        super(itemID, itemName, itemDescription);
        this.itemSize = itemSize;
        this.itemRecovery = itemRecovery;
    }

}
