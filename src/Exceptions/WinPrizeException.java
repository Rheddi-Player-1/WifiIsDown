package Exceptions;

public class WinPrizeException extends RuntimeException
{

    public WinPrizeException(String prize)
    {
        super("Item prize " + prize + " was added to your inventory!");
    }

}
