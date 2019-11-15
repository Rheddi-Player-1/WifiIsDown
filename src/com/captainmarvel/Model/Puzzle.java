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
	private static String name;
	private static String solve;
	private static String answer;
	private static String examine;
	private static String hint;
	private static String room;

	public Puzzle(String name, String solve, String answer, String examine, String hint, String room) 
	{
		this.name = name;
		this.solve = solve;
		this.answer = answer;
		this.examine = examine;
		this.hint = hint;
		this.room = room;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSolve() {
		return solve;
	}


	public void setSolve(String solve) {
		this.solve = solve;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	//Method Reads Puzzle XML file
	public static void readPuzzleXML(HashMap<String, Puzzle> puzzle)
	{
		File xml = new File("Puzzles.xml");
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder b;
		try {
			b = f.newDocumentBuilder();
			Document d = b.parse(xml);
			d.getDocumentElement();
			NodeList puzzleNodes = d.getElementsByTagName("puzzle");
			for(int i = 0; i < puzzleNodes.getLength(); i++)
			{
				Node puzzleNode = puzzleNodes.item(i);
				if(puzzleNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element puzzleElement = (Element) puzzleNode;
					NodeList textNodes = puzzleElement.getElementsByTagName("id");
					
					if(textNodes.getLength() > 0)
					{
						if(textNodes.item(0).getTextContent().equalsIgnoreCase("DBP"))
						{	
							name = puzzleElement.getElementsByTagName("name").item(0).getTextContent();
							solve = puzzleElement.getElementsByTagName("solve").item(0).getTextContent();
							examine = puzzleElement.getElementsByTagName("examine").item(0).getTextContent();
							answer = puzzleElement.getElementsByTagName("answer").item(0).getTextContent();
							hint = puzzleElement.getElementsByTagName("hint").item(0).getTextContent();
							room = puzzleElement.getElementsByTagName("room").item(0).getTextContent();
							
							puzzle.put(name, new Puzzle(name, solve, examine, answer, hint, room));
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