package Exceptions;

public class ItemDoesNotExist extends RuntimeException
{
    public ItemDoesNotExist(String itemName)
    {
        super(itemName + " is not here!");
    }
}
