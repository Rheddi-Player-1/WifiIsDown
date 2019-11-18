package com.captainmarvel.Model;

    public class KeyItem extends Item
    {
        private int itemSize;

        public KeyItem(String itemID, String itemName, String itemDescription, int itemSize)
        {
            super(itemID, itemName, itemDescription);
            this.itemSize = itemSize;
        }

        public int getItemSize()
        {
            return itemSize;
        }

        public void setItemSize(int itemSize)
        {
            this.itemSize = itemSize;
        }
    }
