package Model;

public class HealingItem extends Item
{
	private int healingPoints;

	public HealingItem(String itemID, String itemName, String itemDescription, int itemSize, int healingPoints)
	{
		super(itemID, itemName, itemDescription, itemSize);
		this.healingPoints = healingPoints;
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
