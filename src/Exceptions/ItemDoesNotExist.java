package Exceptions;

public class ItemDoesNotExist extends RuntimeException
{
    public ItemDoesNotExist()
    {
        super("That's not here!");
    }
}
