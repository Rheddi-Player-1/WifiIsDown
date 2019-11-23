package Model;

import Exceptions.*;

public class WordPuzzle extends Puzzle
{

    private String answer;

	public WordPuzzle(String id, String type, Item prize, String solve, String examine, String hint, String answer) {
		super(id, type, prize, solve, examine, hint);

		this.answer = answer;

	}

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public Item solveWordPuzzle(String solution, String roomID) throws WinPrizeException
	{
        if(solution.equals(answer))
        {
            Rooms.rooms.get(roomID).setRoomPuzzleID("NONE");
            return getPrize();
        }
        else
            return null;
	}
}

