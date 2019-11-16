package com.captainmarvel.Exceptions;

public class ItemsNotAvailable extends RuntimeException
{

    public ItemsNotAvailable(String itemName)
    {
        super(itemName + " cannot be added to inventory.");
    }

}
