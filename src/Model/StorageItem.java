package Model;

public class StorageItem extends Item
{
    private static final int itemSize = 50;
    private int itemCapacity;

    public StorageItem(String itemID, String itemName, String itemDescription, int itemCapacity)
    {
        super(itemID, itemName, itemDescription, itemSize);
        this.itemCapacity = itemCapacity;
    }

    public int getItemCapacity()
    {
        return itemCapacity;
    }

    public void setItemCapacity(int itemCapacity)
    {
        this.itemCapacity = itemCapacity;
    }

}

