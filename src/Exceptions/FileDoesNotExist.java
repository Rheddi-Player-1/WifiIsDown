package Exceptions;

public class FileDoesNotExist extends RuntimeException
{
    public FileDoesNotExist(String fileName)
    {
        super(fileName + " does not exist!");
    }
}
