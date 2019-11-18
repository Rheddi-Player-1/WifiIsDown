package Exceptions;

public class NotInRoomException extends RuntimeException
{

    public NotInRoomException()
    {
        super("Failed to drop item in room.");
    }

}
