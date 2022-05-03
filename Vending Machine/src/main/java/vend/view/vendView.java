package vend.view;

import vend.io.UserIO;
import java.math.BigDecimal;
import java.util.ArrayList;

import vend.model.vend;

public class vendView {
    private UserIO io;
    //private UserIO io = new UserIOConsoleImpl();

    public vendView(UserIO useIO)
    {
        this.io = useIO;
    }
    
    public String selectVendItem()
    {
        String itemName = io.readString("Please enter an item based on the item name");
        return itemName;
    }

    public BigDecimal getUserMoney()
    {
        String money = io.readString("Please enter your money in 0.00 format");
        return new BigDecimal(money);
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List Item");
        io.print("2. Create New Item");
        io.print("3. View a Item");
        io.print("4. Remove an Item");
        io.print("5. Exit");

        return io.readInt("Please select from the above choices.", 1, 5);
    }

    public int printViewVendMenu(){
        io.print("Item Edit Menu");
        io.print("1. Edit Item");
        io.print("2. Display Iten");
        io.print("3. Exit");

        return io.readInt("Please select from the above choices.", 1, 3);
    }

    public int printEditVendMenu(){
        io.print("What would you like to edit?");
        io.print("1. Name");
        io.print("2. Cost");
        io.print("3. # of items");
        io.print("4. Exit");

        return io.readInt("Please select from the above choices.", 1, 4);
    }

    public vend getNewItemInfo() {
        String itemName = io.readString("Please enter item name");
        String itemCosts = io.readString("Please enter item cost");
        String numOfItems = io.readString("Please enter item amount");
        vend item = new vend(itemName, itemCosts, numOfItems);
        return item;
    }

    public void displayCreateItemBanner() {
        io.print("=== Create Item ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "Item successfully created.  Please hit enter to continue");
    }

    public void displayAllItem(ArrayList<vend> listOfItem) {

        //required lambda expression 
        listOfItem.forEach( (d) -> { 
            if(Integer.parseInt(d.getNum()) == 0)
            {

            }
            else
            {
                String itemInfo = "Item Name: " + d.getItemName() + " Item Cost: " + d.getItemCost() + "  # Of Items " + d.getNum();
                io.print(itemInfo);
            }
        });
        // for (vend d : listOfItem) {
        //     if(Integer.parseInt(d.getNum()) == 0)
        //     {
        //         continue;
        //     }
        //     else
        //     {
        //         String itemInfo = "Item Name: " + d.getItemName() + " Item Cost: " + d.getItemCost() + "  # Of Items " + d.getNum();
        //         io.print(itemInfo);
        //     }
        // }
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Items ===");
    }

    public void displayDisplayItemBanner () {
        io.print("=== Display Item ===");
    }
    
    public String getItemTitle() {
        return io.readString("Please enter the Item's name");
    }
    
    public void displayItemInfo(vend item) {
        if (item != null) {
            io.print("Item Name: " + item.getItemName());
            io.print("Item Cost: " + item.getItemCost());
            io.print("# Of Items: " + item.getNum());
            io.print("");
        } else {
            io.print("No such item.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveItemBanner () {
        io.print("=== Remove item ===");
    }

    public void displayRemoveResult(vend item) {
        if(item != null){
          io.print("Item successfully removed.");
        }else{
          io.print("No such Item.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String message) {
        io.print("ERROR: " + message);
    }

    public String displayPresentExit() {
        return io.readString("Please press Enter to continue or exit to quit");
    }

    public void printUserChange(String change) {
        io.print("===================================");
        io.print("Your total change is: \n" + change);
    }
}
