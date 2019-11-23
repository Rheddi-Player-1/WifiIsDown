package Model;

import Exceptions.*;

public class ItemPuzzle extends Puzzle
{


	public ItemPuzzle(String id, String type, Item prize, String solve, String answer, String examine, String hint,
			Item itemUse) {
		super(id, type, prize, solve, answer, examine, hint, itemUse);
		// TODO Auto-generated constructor stub
	}

	public boolean solveItemPuzzle(String solution, Rooms room)
	{
		if(getType().equalsIgnoreCase("IP"))
		{


			Player player = null;
			Item item = null;

			if (player.getPlayerEquipedItem().equals(getItemUse()) && getAnswer().equalsIgnoreCase(solution))
			{

				if (getPrize() != null)
				{
					room.getRoomItems().add(getPrize());
					throw new WinPrizeException();
				}

				room.setRoomPuzzleID("none");
				return true;
			} else
				return false;
		}
		else
		{
			return false;
		}
	}
}
