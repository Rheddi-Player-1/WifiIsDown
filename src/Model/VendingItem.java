package Model;

import java.util.ArrayList;

public class VendingItem extends Item
{
	private int itemCapacity;
	private boolean isPickable;
	private ArrayList<Item> heldItem;

	public VendingItem(String itemID, String itemName, String itemDescription, int itemSize, int itemCapacity,
					   boolean isPickable, ArrayList<Item> heldItem)
	{
		super(itemID, itemName, itemDescription, itemSize);
		this.itemCapacity = itemCapacity;
		this.isPickable = isPickable;
		this.heldItem = heldItem;
	}

	public int getItemCapacity()
	{
		return itemCapacity;
	}

	public void setItemCapacity(int itemCapacity)
	{
		this.itemCapacity = itemCapacity;
	}

	public boolean isPickable()
	{
		return isPickable;
	}

	public void setPickable(boolean isPickable)
	{
		this.isPickable = isPickable;
	}

	public ArrayList<Item> getHeldItem()
	{
		return heldItem;
	}

	public void setHeldItem(ArrayList<Item> heldItem) { this.heldItem = heldItem; }

}

