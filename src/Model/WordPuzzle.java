package Model;

import Exceptions.*;

public class WordPuzzle extends Puzzle
{



	public WordPuzzle(String id, String type, Item prize, String solve, String answer, String examine, String hint,
			Item itemUse) {
		super(id, type, prize, solve, answer, examine, hint, itemUse);
		// TODO Auto-generated constructor stub
	}

	public boolean solveWordPuzzle(String solution, Rooms room)
	{
		if(getType().equalsIgnoreCase("WP"))
		{

				if(getAnswer().equalsIgnoreCase(solution))
				{					
					if(getPrize() != null)
					{
						room.getRoomItems().add(getPrize());
						throw new WinPrizeException();
					}

					room.setRoomPuzzleID("none");
					return true;
				}
				else
					return false;
		}
		else
			return false;
	}
}

