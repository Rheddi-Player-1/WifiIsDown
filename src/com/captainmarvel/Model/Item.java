package final_prj;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

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
	
	
	public String getItemID() {
		return itemID;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public String getItemDescription() {
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
	
	public String pickUpItem()
	{
		return null;
		
	}
	
	//Read all the items to a hashmap
	public static void readItems()
	{
		try
		{
		File itemInfo = new File("src/Items.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(itemInfo);

		}
		catch (Exception e)
		{
			
		}
	}
}
