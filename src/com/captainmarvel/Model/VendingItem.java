package com.captainmarvel.Model;

public class VendingItem extends Item
{
	private int itemSize;
	private int itemCapacity;
	private boolean isPickable;
	private Item heldItem;

	public VendingItem(String itemID, String itemName, String itemDescription, int itemSize, int itemCapacity,
					   boolean isPickable, Item heldItem)
	{
		super(itemID, itemName, itemDescription);
		this.itemSize = itemSize;
		this.itemCapacity = itemCapacity;
		this.isPickable = isPickable;
		this.heldItem = heldItem;
	}

	public int getItemSize()
	{
		return itemSize;
	}

	public void setItemSize(int itemSize)
	{
		this.itemSize = itemSize;
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

	public Item getHeldItem()
	{
		return heldItem;
	}

	public void setHeldItem(Item heldItem)
	{
		this.heldItem = heldItem;
	}

}
