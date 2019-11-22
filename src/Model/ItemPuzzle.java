package Model;

import Exceptions.*;

public class ItemPuzzle extends Puzzle
{


	public ItemPuzzle(String id, String type, Item prize, String solve, String answer, String examine, String hint,
			Item itemUse) {
		super(id, type, prize, solve, answer, examine, hint, itemUse);
		// TODO Auto-generated constructor stub
	}

	public String solveItemPuzzle(String solution, Rooms room)
	{
		if(getType().equalsIgnoreCase("IP"))
		{
			int attempt = 0;

			do{
				attempt++;
				
				Player player = null;
				Item item = null;
				
				if(player.getPlayerEquipedItem().equals(getItemUse()) && getAnswer().equalsIgnoreCase(solution))
				{
					attempt = 3;
					
					if(getPrize() != null)
					{
						room.getRoomItems().add(getPrize());
						throw new WinPrizeException();
					}
					
					room.setRoomPuzzleID("none");
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
