package com.captainmarvel.Model;

public class WordPuzzle extends Puzzle
{
	public WordPuzzle(String type, String answer, String prize)
	{
		super();
	}
	public void SolveWordPuzzle(String solution, Item item)
	{
		if(getType().equalsIgnoreCase("WP"))
		{
			int attempt = 0;

			do{
				attempt++;
			
				if(getAnswer().equalsIgnoreCase(solution))
				{
					System.out.println("\nCongrats!! You solved the puzzle.\n");
					
					if(!getPrize().equalsIgnoreCase("NONE"))
					{
						item.inventory.add(item);
						System.out.println("Item prize " + getPrize() + " was added to your inventory!");
					}
					attempt = 3;
				
				}
				else
				{
					int attempts = 3 - attempt;
					System.out.println("\nYou have " + attempts + " attempts left.");
				}
			}while(attempt != 3);
		}
	}
}
