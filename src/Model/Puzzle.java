package Model;

import java.io.File;
import java.util.HashMap;

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
	private String prize;
	private String solve;
	private String answer;
	private String examine;
	private String hint;
	public static HashMap<String, Puzzle> puzzle = new HashMap<>();
	
	public Puzzle(String id, String type, String prize, String solve, String answer, String examine, String hint) 
	{
		this.id = id;
		this.type = type;
		this.prize = prize;
		this.solve = solve;
		this.answer = answer;
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

	public String getPrize() {
		return prize;
	}

	public void setPrize(String prize) {
		this.prize = prize;
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

	//Method Reads Puzzle XML file
	public static void readPuzzleXML()
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
				Node node = puzzleNodes.item(i);
				
				if(node.getNodeType() == Node.ELEMENT_NODE)
				{
					Element e = (Element) node;
					
					String id = e.getElementsByTagName("id").item(0).getTextContent();
					String type = e.getElementsByTagName("type").item(0).getTextContent();
					String prize = e.getElementsByTagName("prize").item(0).getTextContent();
					String solve = e.getElementsByTagName("solve").item(0).getTextContent();
					String answer = e.getElementsByTagName("answer").item(0).getTextContent();
					String examine = e.getElementsByTagName("examine").item(0).getTextContent();
					String hint = e.getElementsByTagName("hint").item(0).getTextContent();
					
					puzzle.put(id, new Puzzle(id, type, prize, solve, answer, examine, hint));
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("\nWrong Input. Try Again.\n");
		}
	}
}

