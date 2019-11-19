package Exceptions;

public class WinPrizeException extends RuntimeException
{

    public WinPrizeException()
    {
        super("Prize has been added to your inventory!");
    }

}
