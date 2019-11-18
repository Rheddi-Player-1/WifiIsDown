package com.captainmarvel.Model;

public class ConsumableItem extends Item
{
    private int itemSize;
    private double itemRecovery;
    private int healingPoints;

    public ConsumableItem(String itemID, String itemName, String itemDescription, int itemSize, double itemRecovery)
    {
        super(itemID, itemName, itemDescription);
        this.itemSize = itemSize;
        this.itemRecovery = itemRecovery;
    }

    public int getItemSize() {
        return itemSize;
    }

    public void setItemSize(int itemSize) {
        this.itemSize = itemSize;
    }

    public double getItemRecovery() {
        return itemRecovery;
    }

    public void setItemRecovery(double itemRecovery) {
        this.itemRecovery = itemRecovery;
    }

    public int getHealingPoints()
    {
        return healingPoints;
    }

    public void setHealingPoints(int healingPoints)
    {
        this.healingPoints = healingPoints;
    }

}
