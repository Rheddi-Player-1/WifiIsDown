package com.captainmarvel.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class Item
{
	private String itemID;
	private String itemName;
	private String itemDescription;
	private int itemLocation;
	private int itemSize;
	private boolean isPickable;
	private HashMap <String, Item> allItems;
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private Rooms room;


	public String getItemID()
	{
		return itemID;
	}

	public String getItemName()
	{
		return itemName;
	}

	public String getItemDescription()
	{
		return itemDescription;
	}

	public int getInitialItemLocation()
	{
		return itemLocation;
	}

	public int getItemSize()
	{
		return itemSize;
	}

	public void setItemSize(int itemSize)
	{
		this.itemSize = itemSize;
	}

	public boolean isPickable()
	{
		return isPickable;
	}

	public void setPickable(boolean isPickable)
	{
		this.isPickable = isPickable;
	}
	public HashMap<String, Item> getAllItems()
	{
		return allItems;
	}
	public void setAllItems(HashMap<String, Item> allItems)
	{
		this.allItems = allItems;
	}

	public String inspectItem()
	{
		return itemDescription;
	}

	public Item pickUpItem()
	{
		inventory.add(room.getRoomItem());
		room.removeFromRoom(getItemID(), item);
	}

	public Item dropItem()
	{
		return null;

	}

	public void useItem()
	{

	}

	public Item equipItem()
	{
		return null;

	}

	//Read all the items to a hashmap
	public static void generateItems()
	{
		File itemInfo = new File("src/com/captainmarvel/XMLs/Items.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try
		{
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(itemInfo);
			doc.getDocumentElement().normalize();

			NodeList vendingItemNodes = doc.getElementsByTagName("vendingItem");
			for (int i = 0; i < vendingItemNodes.getLength(); i++)
			{
				Node node = vendingItemNodes.item(i);
			}

			NodeList consumableItemNodes = doc.getElementsByTagName("consumableItem");
			for (int j = 0; j < consumableItemNodes.getLength(); j++)
			{

			}

			NodeList weaponItemNodes = doc.getElementsByTagName("weaponItem");
			for (int k =  0; k < weaponItemNodes.getLength(); k++)
			{

			}

			NodeList keyItemNodes = doc.getElementsByTagName("keyItem");
			for (int l = 0; l < keyItemNodes.getLength(); l++)
			{

			}

			NodeList storageItemNodes = doc.getElementsByTagName("storageItem");
			for (int m = 0; m < storageItemNodes.getLength(); m++)
			{

			}

		}
		catch (Exception e)
		{

		}
	}
}
