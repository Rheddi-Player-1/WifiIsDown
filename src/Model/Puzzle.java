package Model;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Puzzle 
{
	private String id;
	private String type;
	private Item prize;
	private String solve;
	private String examine;
	private String hint;
	public static HashMap<String, Puzzle> puzzle = new HashMap<>();
	
	public Puzzle(String id, String type, Item prize, String solve, String examine, String hint)
	{
		this.id = id;
		this.type = type;
		this.prize = prize;
		this.solve = solve;
		this.examine = examine;
		this.hint = hint;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Item getPrize() {
		return prize;
	}

	public void setPrize(Item prize) {
		this.prize = prize;
	}

	public String getSolve() {
		return solve;
	}

	public void setSolve(String solve) {
		this.solve = solve;
	}

	public String getExamine() {
		return examine;
	}

	public void setExamine(String examine) {
		this.examine = examine;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}
	
	public String puzzleDescription()
	{
		return solve;
	}


	//Method Reads Puzzle XML file
	public static void readPuzzleXML()
	{
		File xml = new File("src/Xmls/Puzzles.xml");
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder b;
		try {
			b = f.newDocumentBuilder();
			Document d = b.parse(xml);
			d.getDocumentElement();
			
			NodeList puzzleNodes = d.getElementsByTagName("wordPuzzles");
			for(int i = 0; i < puzzleNodes.getLength(); i++)
			{
				Node node = puzzleNodes.item(i);
				
				Item prize = null;
				if(node.getNodeType() == Node.ELEMENT_NODE)
				{
					Element e = (Element) node;
					
					String id = e.getElementsByTagName("id").item(0).getTextContent();
					String type = e.getElementsByTagName("type").item(0).getTextContent();
					String temp1 = e.getElementsByTagName("prize").item(0).getTextContent();
					String solve = e.getElementsByTagName("solve").item(0).getTextContent();
					String answer = e.getElementsByTagName("answer").item(0).getTextContent();
					String examine = e.getElementsByTagName("examine").item(0).getTextContent();
					String hint = e.getElementsByTagName("hint").item(0).getTextContent();
					
					if(!temp1.equalsIgnoreCase("NONE"))
                        prize = Item.allItems.get(temp1);
                    else
                        prize = null;

					puzzle.put(id, new WordPuzzle(id, type, prize, solve, examine, hint, answer));
				}
			}

			NodeList puzzleNodesToo = d.getElementsByTagName("itemPuzzles");
			for(int i = 0; i < puzzleNodesToo.getLength(); i++)
			{
				Node node = puzzleNodes.item(i);

				Item prize = null;
				Item itemUse = null;
				if(node.getNodeType() == Node.ELEMENT_NODE)
				{
					Element e = (Element) node;

					String id = e.getElementsByTagName("id").item(0).getTextContent();
					String type = e.getElementsByTagName("type").item(0).getTextContent();
					String temp1 = e.getElementsByTagName("prize").item(0).getTextContent();
					String solve = e.getElementsByTagName("solve").item(0).getTextContent();
					String examine = e.getElementsByTagName("examine").item(0).getTextContent();
					String hint = e.getElementsByTagName("hint").item(0).getTextContent();
					String temp2 = e.getElementsByTagName("itemUse").item(0).getTextContent();

					if(!temp1.equalsIgnoreCase("NONE"))
						prize = Item.allItems.get(temp1);
					else
						prize = null;

					if(!temp2.equalsIgnoreCase("null"))
						itemUse = Item.allItems.get(temp2);
					else
						itemUse = null;

					puzzle.put(id, new ItemPuzzle(id, type, prize, solve, examine, hint, itemUse));
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}


