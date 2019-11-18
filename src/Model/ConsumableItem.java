package Model;

public class ConsumableItem extends Item
{
    private double itemRecovery;
    private int healingPoints;

    public ConsumableItem(String itemID, String itemName, String itemDescription, int itemSize, double itemRecovery)
    {
        super(itemID, itemName, itemDescription, itemSize);
        this.itemRecovery = itemRecovery;
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
