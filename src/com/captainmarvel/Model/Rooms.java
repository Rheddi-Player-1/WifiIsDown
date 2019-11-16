import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Rooms 
{
	private static String roomID;
	private static String roomName;
	private static String roomDescription;
	private String textNodeName;
	private String textNodeValue;
	private String value;
	private static ArrayList<Item> inventory;

	//constructor with no parameters
	Rooms()
	{
		
	}

	//Getter method for variable roomID
	public String getRoomID() 
	{
		return roomID;
	}
	
	//Setter method for variable roomID
	public static void setRoomID(String roomID) 
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
	
	public void storeItem(<String, Item> allItems, String value)
	{
		Item items = allItems.getAllItems().containsKey(value);
		
		if(roomID.equalsIgnoreCase(items))
		{
			inventory.put(items.getName(), value);
		}
	}
	
	public void removeItem(String value)
	{
		if(inventory.containsKey(value))
		{
			inventory.remove(value);
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
							roomID = roomElement.getElementsByTagName("id").item(0).getTextContent();
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
