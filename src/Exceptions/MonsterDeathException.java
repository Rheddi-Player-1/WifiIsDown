package Exceptions;

public class MonsterDeathException extends RuntimeException
{

    public MonsterDeathException()
    {
        super("Enemy has been deafeated!");
    }

}
