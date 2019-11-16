import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Rooms 
{
	private static int roomID;
	private static String roomName;
	private static String roomDescription;
	private static int roomPuzzleID;
	private static ArrayList<String> inventory;
	private String textNodeName;
	private String textNodeValue;
	private String value;
	
	//constructor with no parameters
	Rooms()
	{
		
	}

	//Getter method for variable roomID
	public int getRoomID() 
	{
		return roomID;
	}
	
	//Setter method for variable roomID
	public static void setRoomID(int roomID) 
	{
		roomID = roomID;
	}
	
	//Getter method for variable roomName
	public String getRoomName() 
	{
		return roomName;
	}
	
	//Setter method for variable roomName
	public static void setRoomName(String roomName) 
	{
		roomName = roomName;
	}
	
	//Getter method for variable roomDescription
	public String getRoomDescription() 
	{
		return roomDescription;
	}
	
	//Setter method for variable roomDescription
	public static void setRoomDescription(String roomDescription) 
	{
		roomDescription = roomDescription;
	}
	
	public int getRoomPuzzleID()
	{
		return roomPuzzleID;
	}

	public static void setRoomPuzzleID(int roomPuzzleID)
	{
		roomPuzzleID = roomPuzzleID;
	}
	
	//Getter method
	public String getValue() 
	{
		return value;
	}
	
	//Setter method
	public void setValue(String value) 
	{
		this.value = value;
	}
	

	public String getTextNodeName() 
	{
		return textNodeName;
	}

	public void setTextNodeName(String textNodeName) 
	{
		this.textNodeName = textNodeName;
	}

	public String getTextNodeValue() 
	{
		return textNodeValue;
	}

	public void setTextNodeValue(String textNodeValue) 
	{
		this.textNodeValue = textNodeValue;
	}	
	
	public void storeItem(String value, Item items)
	{	
		int location = items.getInitialItemLocation();
		if(roomID == location)
		{
			String name = items.getItemName();
			inventory.add(value);
			System.out.println("Item added to your inventory.");
		}
		else
		{
			System.out.println("Item not in your inventory.");
		}
	}
	
	public void removeItem(String value)
	{
		if(inventory.contains(value))
		{
			inventory.remove(value);
			System.out.println("Item removed from your inventory.");
		}
	}
	//Method reads Rooms XML file
	public void readRoomsXML(String textNodeName, String textNodeValue)
	{
		File xml = new File("Rooms.xml");
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder b;
		try {
			b = f.newDocumentBuilder();
			Document d = b.parse(xml);
			d.getDocumentElement();
			NodeList roomNodes = d.getElementsByTagName("Room");
			for(int i = 0; i < roomNodes.getLength(); i++)
			{
				Node roomNode = roomNodes.item(i);
				if(roomNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element roomElement = (Element) roomNode;
					NodeList textNodes = roomElement.getElementsByTagName(textNodeName);
					
					if(textNodes.getLength() > 0)
					{
						if(textNodes.item(0).getTextContent().equalsIgnoreCase(textNodeValue))
						{	
							System.out.println(roomElement.getElementsByTagName("name").item(0).getTextContent());
							System.out.println(roomElement.getElementsByTagName("description").item(0).getTextContent());
							
							roomName = roomElement.getElementsByTagName("name").item(0).getTextContent();
							String id = roomElement.getElementsByTagName("id").item(0).getTextContent();
							roomID = Integer.parseInt(id);
						}
					}
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("\nWrong Input. Try Again.\n");
		}
	}
	
	//Method to change rooms
	public void changeRoom(String textNodeName, String textNodeValue)
	{
		File xml = new File("Rooms.xml");
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder b;
		try 
		{
			b = f.newDocumentBuilder();
			Document d = b.parse(xml);
			d.getDocumentElement();
			
			NodeList roomNodes = d.getElementsByTagName("Room");
			for(int i = 0; i < roomNodes.getLength(); i++)
			{
				Node roomNode = roomNodes.item(i);
				if(roomNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element roomElement = (Element) roomNode;
					NodeList textNodes = roomElement.getElementsByTagName(textNodeName);
					
					if(textNodes.getLength() > 0)
					{
						if(textNodes.item(0).getTextContent().equalsIgnoreCase(textNodeValue))
						{	
							//The statement print out the tag name information
							String n =roomElement.getElementsByTagName(getTemp().toLowerCase()).item(0).getTextContent();
							//print directions by attribute and value
							System.out.print("\n");
							readRoomsXML("id", n);
						}
					}
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("\nWrong Input. Try Again.\n");
		}
	}
}
