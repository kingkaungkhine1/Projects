package vend.model;

import java.util.Objects;

public class vend {
    String itemName = "";
    String itemCost = "";
    String numOfItems = "";

    public vend(String name, String cost, String num)
    {
        this.itemName = name;
        this.itemCost = cost;
        this.numOfItems = num;
    }

    public vend() {}

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemString) {
        this.itemName = itemString;
    }

    public String getItemCost() {
        return this.itemCost;
    }

    public void setItemCost(String releaseDate) {
        this.itemCost = releaseDate;
    }

    public String getNum() {
        return this.numOfItems;
    }

    public void setNum(String num) {
        this.numOfItems = num;
    }


    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
            return true;

        if(o == null)
            return false;
        if(getClass() != o.getClass())
        {
            return false;
        }
        if (!(o instanceof vend)) {
            return false;
        }
        vend vendItem = (vend) o;
        return Objects.equals(this.itemName, vendItem.itemName) && Objects.equals(this.itemCost, vendItem.itemCost) && Objects.equals(this.numOfItems, vendItem.numOfItems);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hash(itemName, itemCost, numOfItems);
    }

    @Override
    public String toString()
    {
        return "Vend Item{" + "itemName=" + itemName + ", itemCost=" + itemCost + ", # of Items" + numOfItems + '}';
    }
    
}
