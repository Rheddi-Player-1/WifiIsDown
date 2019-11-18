package Exceptions;

public class LockedRoomException extends RuntimeException
{

    public LockedRoomException()
    {
        super("Access denied. Must solve puzzle to enter room.");
    }

}
