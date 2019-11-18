package Exceptions;

public class OverEncumbered extends RuntimeException
{
    public OverEncumbered()
    {
        super("Cannot add another Item. There is not enough space in your storage.");
    }

}
