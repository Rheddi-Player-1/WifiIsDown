package Exceptions;

public class MonsterDeathException extends RuntimeException
{

    public MonsterDeathException(String monsterName)
    {
        super(monsterName + " has been deafeated!");
    }

}
