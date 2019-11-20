package Model;

import Exceptions.*;

public class ItemPuzzle extends Puzzle
{
	public ItemPuzzle(String id, String type, Item prize, String solve, String answer, String examine, String hint, Item itemUse) {
		super(id, type, prize, solve, answer, examine, hint, itemUse);
	}

	public String SolveItemPuzzle(String solution)
	{
		if(getType().equalsIgnoreCase("IP"))
		{
			int attempt = 0;

			do{
				attempt++;
				
				Rooms rooms = Rooms.rooms.get(solution);
				Player player = null;
				Item item = null;
				
				if(player.getPlayerEquipedItem().equals(getItemUse()) && getAnswer().equalsIgnoreCase(solution))
				{
					
					attempt = 3;
					
					if(!getPrize().equals("NONE"))
					{
						rooms.roomItems.add(getPrize());
						throw new WinPrizeException();
					}
					
					rooms.roomItems.remove(item);
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
