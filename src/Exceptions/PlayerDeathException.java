package Exceptions;

public class PlayerDeathException extends RuntimeException
{
    public PlayerDeathException()
    {
        super("You cannot bare the stress of solving the campus's problems!\nGAME OVER!");
    }
}
