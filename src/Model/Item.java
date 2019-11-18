package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
	private Item heldItem;
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
		Item item = room.getRoomItem();
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
				Node vNode = vendingItemNodes.item(i);

				if (vNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element vendingItem = (Element) vNode;
					String code = vendingItem.getAttribute("itemCode");
					String name = vendingItem.getElementsByTagName("itemName").item(0).getTextContent();
					String description = vendingItem.getElementsByTagName("itemDescription").item(0).getTextContent();
					int size = Integer.parseInt(vendingItem.getElementsByTagName("itemSize").item(0).getTextContent());
					int capacity = Integer.parseInt(vendingItem.getElementsByTagName("itemCapacity").item(0).getTextContent());
					boolean ispickable = Boolean.parseBoolean(vendingItem.getElementsByTagName("itemIsPickUpAble").item(0).getTextContent());
					String tempHeldItems = vendingItem.getElementsByTagName("heldItems").item(0).getTextContent();

					if (!tempHeldItems.isEmpty())
					{
						heldItem =
					}
				}
			}

			NodeList consumableItemNodes = doc.getElementsByTagName("consumableItem");
			for (int j = 0; j < consumableItemNodes.getLength(); j++)
			{

				Node cNode = consumableItemNodes.item(j);

				if (cNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element consumableItem = (Element) cNode;
					String code = consumableItem.getAttribute("itemCode");
					String name = consumableItem.getElementsByTagName("itemName").item(0).getTextContent();
					String description = consumableItem.getElementsByTagName("itemDescription").item(0).getTextContent();
					int size = Integer.parseInt(consumableItem.getElementsByTagName("itemSize").item(0).getTextContent());
					double recovery = Integer.parseInt(consumableItem.getElementsByTagName("itemRecovery").item(0).getTextContent());
				}
			}

			NodeList weaponItemNodes = doc.getElementsByTagName("weaponItem");
			for (int k =  0; k < weaponItemNodes.getLength(); k++)
			{
				Node wNode = weaponItemNodes.item(k);

				if (wNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element weaponItem = (Element) wNode;
					String code = weaponItem.getAttribute("itemCode");
					String name = weaponItem.getElementsByTagName("itemName").item(0).getTextContent();
					String description = weaponItem.getElementsByTagName("itemDescription").item(0).getTextContent();
					int size = Integer.parseInt(weaponItem.getElementsByTagName("itemSize").item(0).getTextContent());
					double attackBonus = Integer.parseInt(weaponItem.getElementsByTagName("attackBonus").item(0).getTextContent());
				}
			}

			NodeList keyItemNodes = doc.getElementsByTagName("keyItem");
			for (int l = 0; l < keyItemNodes.getLength(); l++)
			{
				Node kNode = keyItemNodes.item(l);

				if (kNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element keyItem = (Element) kNode;
					String code = keyItem.getAttribute("itemCode");
					String name = keyItem.getElementsByTagName("itemName").item(0).getTextContent();
					String description = keyItem.getElementsByTagName("itemDescription").item(0).getTextContent();
					int size = Integer.parseInt(keyItem.getElementsByTagName("itemSize").item(0).getTextContent());
				}
			}

			NodeList storageItemNodes = doc.getElementsByTagName("storageItem");
			for (int m = 0; m < storageItemNodes.getLength(); m++)
			{
				Node sNode = storageItemNodes.item(m);

				if (sNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element storageItem = (Element) sNode;
					String code = storageItem.getAttribute("itemCode");
					String name = storageItem.getElementsByTagName("itemName").item(0).getTextContent();
					String description = storageItem.getElementsByTagName("itemDescription").item(0).getTextContent();
					int capacity = Integer.parseInt(storageItem.getElementsByTagName("itemSize").item(0).getTextContent());
				}
			}

		}
		catch (Exception e)
		{
			System.out.println("Error! Please try again.\n");
			e.printStackTrace();
		}
	}
}











