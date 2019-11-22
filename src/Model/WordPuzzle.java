package Model;

import Exceptions.*;

public class WordPuzzle extends Puzzle
{



	public WordPuzzle(String id, String type, Item prize, String solve, String answer, String examine, String hint,
			Item itemUse) {
		super(id, type, prize, solve, answer, examine, hint, itemUse);
		// TODO Auto-generated constructor stub
	}

	public String solveWordPuzzle(String solution, Rooms room)
	{
		if(getType().equalsIgnoreCase("WP"))
		{
			int attempt = 0;

			do{
				attempt++;

				if(getAnswer().equalsIgnoreCase(solution))
				{					
					if(getPrize() != null)
					{
						attempt = 3;
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

