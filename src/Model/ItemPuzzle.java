package Model;

import Exceptions.*;

public class ItemPuzzle extends Puzzle
{

	private Item itemUse;

	public ItemPuzzle(String id, String type, Item prize, String solve, String examine, String hint,
			Item itemUse) {
		super(id, type, prize, solve, examine, hint);

		this.itemUse = itemUse;
	}

	public Item getItemUse()
	{
		return itemUse;
	}

	public void setItemUse(Item itemUse)
	{
		this.itemUse = itemUse;
	}

	public Item solveItemPuzzle(Item answer, String roomID) throws WinPrizeException
	{
		if(answer.getItemID().equals(itemUse.getItemID()))
		{
			Rooms.rooms.get(roomID).setRoomPuzzleID("NONE");
			return getPrize();
		}
		else
			return null;
	}
}
