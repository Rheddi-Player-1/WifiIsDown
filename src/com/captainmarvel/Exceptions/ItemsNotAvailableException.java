package com.captainmarvel.Exceptions;

public class ItemsNotAvailableException extends RuntimeException
{

    public ItemsNotAvailable(String itemName)
    {
        super(itemName + " cannot be added to inventory.");
    }

}
