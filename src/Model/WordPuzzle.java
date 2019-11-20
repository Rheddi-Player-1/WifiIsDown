package Model;

import Exceptions.*;

public class WordPuzzle extends Puzzle
{
	public WordPuzzle(String id, String type, String prize, String solve, String answer, String examine, String hint, String itemUse) {
		super(id, type, prize, solve, answer, examine, hint, itemUse);
	}

	public String SolveWordPuzzle(String solution, Item item)
	{
		if(getType().equalsIgnoreCase("WP"))
		{
			int attempt = 0;

			do{
				attempt++;
			
				if(getAnswer().equalsIgnoreCase(solution))
				{					
					if(!getPrize().equalsIgnoreCase("NONE"))
					{
						attempt = 3;
						item.inventory.add(item);
						throw new WinPrizeException();
					}
					
					return "\nCongrats!! You solved the puzzle.\n";				
				}
				else
				{
					int attempts = 3 - attempt;
					return "\nYou have " + attempts + " attempts left.";
				}
			}while(attempt != 3);
		}
		return null;
	}
}
