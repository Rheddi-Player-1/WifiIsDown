package Model;

package final_prj;

public class WeaponItem extends Item
{
	private double attackBonus;

	public WeaponItem(String itemID, String itemName, String itemDescription, int itemSize, double attackBonus)
	{
		super(itemID, itemName, itemDescription, itemSize);
		this.attackBonus = attackBonus;
	}

	public double getAttackBonus()
	{
		return attackBonus;
	}

	public void setAttackBonus(int attackBonus)
	{
		this.attackBonus = attackBonus;
	}

	private int damagePoints;

	public int getDamagePoints()
	{
		return damagePoints;
	}

	public void setDamagePoints(int damagePoints)
	{
		this.damagePoints = damagePoints;
	}
}

