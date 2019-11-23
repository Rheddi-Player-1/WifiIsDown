package Exceptions;

public class WinPrizeException extends RuntimeException
{

    public WinPrizeException()
    {
        super("The puzzle dropped an object in the room!");
    }

}
