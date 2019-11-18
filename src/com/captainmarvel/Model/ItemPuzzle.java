package com.captainmarvel.Model;

public class ItemPuzzle extends Puzzle
{
	public ItemPuzzle(String type, String answer, String prize)
	{
		super();
	}
	public void SolveItemPuzzle(String solution, Item item, Puzzle puzzle)
	{
		if(getType().equalsIgnoreCase("IP"))
		{
			int attempt = 0;

			do{
				attempt++;
			
				if(item.inventory.contains(getAnswer()) && getAnswer().equalsIgnoreCase(solution))
				{
					item.inventory.remove(solution);
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

