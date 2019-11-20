package Model;

import Exceptions.*;

public class ItemPuzzle extends Puzzle
{
	public ItemPuzzle(String id, String type, String prize, String solve, String answer, String examine, String hint, String itemUse) {
		super(id, type, prize, solve, answer, examine, hint, itemUse);
	}

	public String SolveItemPuzzle(String solution, Item item)
	{
		if(getType().equalsIgnoreCase("IP"))
		{
			int attempt = 0;

			do{
				attempt++;
			
				if(item.inventory.contains(getItemUse()) && getAnswer().equalsIgnoreCase(solution))
				{
					
					attempt = 3;
					
					if(!getPrize().equalsIgnoreCase("NONE"))
					{
						item.inventory.add(item);
						throw new WinPrizeException();
					}
					
					item.inventory.remove(solution);
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
