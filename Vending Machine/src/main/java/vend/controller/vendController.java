package vend.controller;

import vend.service.NoItemInventoryException;
import vend.service.vendDataValidationException;
import vend.service.vendInsufficientFundsException;
import vend.service.vendServiceLayer;
import vend.dao.vendPersistenceException;
import vend.dao.vendDaoFileImpl;
import vend.model.Change;
import vend.model.vend;
import vend.io.UserIO;
import vend.io.UserIOConsoleImpl;
import vend.view.vendView;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class vendController {
    private vendView view; // = new dvdView();
    private UserIO io = new UserIOConsoleImpl();
    private vendServiceLayer service; // = new dvdDaoImpl();
    // private List<String> tempDVD;
    BigDecimal userMoney = new BigDecimal("0");
    String itemPicked = "";

    public vendController(vendView viewVend, vendServiceLayer vendService) {
        this.view = viewVend;
        this.service = vendService;
    }



    // public void fillList() {
    // JSONParser parser = new JSONParser();
    // try
    // {
    // JSONArray a = (JSONArray) parser.parse(new FileReader("data.json"));
    // for (Object o : a)
    // {
    // JSONObject dvdJSON = (JSONObject) o;
    // String titleString = (String) dvdJSON.get("titleString");
    // // System.out.println(titleString);
    // String releaseDate = (String) dvdJSON.get("releaseDate");
    // String MPAA = (String) dvdJSON.get("MPAA");
    // String dirName = (String) dvdJSON.get("dirName");
    // String stuName = (String) dvdJSON.get("stuName");
    // String userName = (String) dvdJSON.get("userName");

    // vend dvdTemp = new vend(titleString, releaseDate, MPAA, dirName, stuName, userName);
    // listOfDVD.add(dvdTemp);
    // }
    // // List<String> tempDVD =
    // // Files.readAllLines(java.nio.file.Paths.get("./data.txt"),
    // // StandardCharsets.UTF_8);
    // // for(String line : tempDVD)
    // // {
    // // String[] tokens = line.split(",");
    // // System.out.println(tokens[0]);
    // // }
    // } catch (IOException | ParseException e)
    // {
    // // TODO Auto-generated catch block
    // e.printStackTrace();

    // }

    // }

    public void run() throws NoItemInventoryException, vendInsufficientFundsException,
            vendDataValidationException, vendPersistenceException {
        // boolean keepGoing = true;
        // int menuSelection = 0;
        // try
        // {
        // while (keepGoing)
        // {
        // menuSelection = getMenuSelection();
        // // io.print("Main Menu");
        // // io.print("1. List DVDs");
        // // io.print("2. Add a vend");
        // // io.print("3. View a vend");
        // // io.print("4. Remove a vend");
        // // io.print("5. Exit");

        // // menuSelection = io.readInt("Please select from the"
        // // + " above choices.", 1, 5);

        // switch (menuSelection) {
        // case 1:
        // io.print("LIST ITEM");
        // listItem();
        // break;
        // case 2:
        // io.print("ADD ITEM");
        // createItem();
        // break;
        // case 3:
        // io.print("VIEW ITEM");
        // viewItem();
        // break;
        // case 4:
        // io.print("REMOVE Item");
        // removeItem();
        // break;
        // case 5:
        // keepGoing = false;
        // exitMessage();
        // break;
        // default:
        // unknownCommand();
        // // io.print("UNKNOWN COMMAND");
        // }

        // }
        // } catch (vendPersistenceException e)
        // {
        // }
        // io.print("GOOD BYE");
        // exitMessage();
        // save();
        listItem();
        userMoney = getMoney();
        try
        {
            validateMoney(userMoney);
        } catch (vendDataValidationException e)
        {
            view.displayErrorMessage(e.toString());
            run();
        }
        itemPicked = selectItem();
        try {
            validateItem(itemPicked);
        } catch (vendDataValidationException e) {
            view.displayErrorMessage(e.toString());
            run();
        }
        // Change change = new Change(userMoney.intValue());
        vend item = service.getItem(itemPicked);
        // System.out.println(item.getItemCost());
        checkMoney(userMoney, item);
        String change = service.getChange();
        printChange(service.changeAsString(change));

        // System.out.println(userMoney);
        System.exit(0);
    }

    private void validateItem(String itemPicked)
            throws vendPersistenceException, vendDataValidationException {
        boolean check = false;
        ArrayList<vend> temp = service.getAllItems();
        for (vend d : temp)
        {
            if (d.getItemName().equals(itemPicked))
            {
                check = true;
            }
        }
        if (check)
        {

        } else
        {
            throw new vendDataValidationException("ITEM DOES NOT EXIST IN STORAGE\n");
        }
    }



    private void validateMoney(BigDecimal userMoney) throws vendDataValidationException {
        if ((userMoney.compareTo(BigDecimal.ZERO) > 0)
                && (getNumberOfDecimalPlaces(userMoney) == 2))
        {
        } else
        {
            throw new vendDataValidationException(
                    "PLEASE ENTER A POSITIVE NUMBER IN THE FORMAT PRESENTED ABOVE (ex \"1.94\")\n");
        }
    }

    int getNumberOfDecimalPlaces(BigDecimal bigDecimal) {
        String string = bigDecimal.stripTrailingZeros().toPlainString();
        int index = string.indexOf(".");
        return index < 0 ? 0 : string.length() - index - 1;
    }



    private void printChange(String change) {
        view.printUserChange(change);
    }

    private void checkMoney(BigDecimal money, vend item) throws vendInsufficientFundsException,
            vendPersistenceException, NoItemInventoryException {
        BigDecimal temp = new BigDecimal(item.getItemCost());
        // <=
        // money == user gained money
        // temp == selected item costs
        // if money >= temp
        // temp <= temp
        // firstBigDecimal.compareTo(secondBigDecimal) <= 0 // "<="
        if (temp.compareTo(money) <= 0)
        {
            money = money.subtract(temp);
            // System.out.println(money.doubleValue());
            service.setChange(money);
            // System.out.println(money.subtract(temp) + "");
            service.getItem(item.getItemName()).setItemCost(money + "");
            updateInventory(item);
        } else
        {
            view.displayErrorMessage("INSUFFICENT FUNDS USER INPUT MONEY: $" + money);
            view.displayErrorMessage("ITEM SELECTED COST: $" + item.getItemCost());
            view.displayErrorMessage("PLEASE ENTER MORE MONEY");
            throw new vendInsufficientFundsException("NOT ENOUGH MONEY");
        }
    }


    private void updateInventory(vend item)
            throws NoItemInventoryException, vendPersistenceException {
        int num = Integer.parseInt(item.getNum());
        if (num == 0)
        {
            view.displayErrorMessage("NO MORE ITEMS LEFT PLEASE SELECT A DIFFERENT ITEM");
            throw new NoItemInventoryException("NO MORE ITEMS IN MACHINE");
        } else
        {
            num = num - 1;
            service.numUpdate(item, num + "");
        }
    }


    private String selectItem() {
        return view.selectVendItem();
    }


    private BigDecimal getMoney() {
        return view.getUserMoney();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private int getEditSelection() {
        return view.printEditVendMenu();
    }

    private int getViewSelection() {
        return view.printViewVendMenu();
    }

    private void createItem() throws NoItemInventoryException, vendInsufficientFundsException,
            vendPersistenceException, vendDataValidationException {
        view.displayCreateItemBanner();
        boolean hasErrors = false;
        do
        {
            vend newItem = view.getNewItemInfo();
            try
            {
                service.addItem(newItem);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch (vendDataValidationException e)
            {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    private void listItem() throws vendPersistenceException {
        view.displayDisplayAllBanner();
        view.displayAllItem(service.getAllItems());
        String result = view.displayPresentExit();
        if (result.equals("exit"))
        {
            System.exit(0);
        } else
        {

        }
    }

    private void viewItem() throws vendPersistenceException, NoItemInventoryException,
            vendInsufficientFundsException, vendDataValidationException {
        view.displayDisplayItemBanner();
        String titleName = view.getItemTitle();
        ArrayList temp = new ArrayList<>();
        // temp = dao.getAllDVDs();
        // System.out.println(temp.get(0));


        vend item = service.getItem(titleName);
        // System.out.println(dvd.getUserNote());
        view.displayItemInfo(item);
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing)
        {
            menuSelection = getViewSelection();
            // io.print("Main Menu");
            // io.print("1. List DVDs");
            // io.print("2. Add a vend");
            // io.print("3. View a vend");
            // io.print("4. Remove a vend");
            // io.print("5. Exit");

            // menuSelection = io.readInt("Please select from the"
            // + " above choices.", 1, 5);

            switch (menuSelection) {
                case 1:
                    io.print("EDIT ITEM");
                    editItem(item);
                    break;
                case 2:
                    io.print("DISPLAY ITEM");
                    view.displayItemInfo(item);
                    // dao.listDVD(listOfDVD);
                    break;
                case 3:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
                    // io.print("UNKNOWN COMMAND");
            }

        }
    }

    private void editItem(vend item) throws vendPersistenceException, NoItemInventoryException,
            vendInsufficientFundsException, vendDataValidationException {
        String temp;
        vend oldItem = new vend(item.getItemName(), item.getItemCost(), item.getNum());
        String print;
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing)
        {
            menuSelection = getEditSelection();
            // io.print("Main Menu");
            // io.print("1. List DVDs");
            // io.print("2. Add a vend");
            // io.print("3. View a vend");
            // io.print("4. Remove a vend");
            // io.print("5. Exit");

            // menuSelection = io.readInt("Please select from the"
            // + " above choices.", 1, 5);

            switch (menuSelection) {
                case 1:
                    io.print("EDIT ITEM NAME");
                    temp = item.getItemName();
                    print = io.readString("Enter new item name");
                    item.setItemName(print);
                    service.removeItem(oldItem);
                    service.addItem(item);
                    break;
                case 2:
                    io.print("EDIT ITEM COST");
                    temp = item.getItemCost();

                    print = io.readString("Enter new item cost");
                    item.setItemCost(print);
                    service.removeItem(oldItem);
                    service.addItem(item);
                    break;
                case 3:
                    io.print("EDIT # OF ITEMS");
                    print = io.readString("Enter new # of items");
                    item.setNum(print);
                    service.removeItem(oldItem);
                    service.addItem(item);
                    break;
                case 4:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
                    // io.print("UNKNOWN COMMAND");
            }

        }

        /*
         * String selection, stringValue, oldTitle, rating;
         * 
         * // Iteratively get a vend field to edit and edit editMenu: do { selection =
         * view.promptString( "\nWhat would like to edit?\n" +
         * "TITLE, RELEASE, RATING, DIRECTOR, STUDIO, NOTE\n" + "or EXIT" );
         * 
         * // Handles the user's selection of field switch (selection.toLowerCase()) { case
         * ("title"): oldTitle = dvd.getTitle(); stringValue =
         * view.promptString("\nWhat is the new title?"); dvd.setTitle(stringValue);
         * 
         * dao.removeDvd(oldTitle); dao.addDvd(dvd); break; case ("release"): stringValue =
         * view.promptString("\nWhat is the new release date?"); dvd.setRelease(stringValue); break;
         * case ("rating"): doubleValue = view.promptDouble("\nWhat is the new rating?");
         * dvd.setRating(doubleValue); break; case ("director"): stringValue =
         * view.promptString("\nWhat is the new director?"); dvd.setNote(stringValue); break; case
         * ("studio"): stringValue = view.promptString("\nWhat is the new studio?");
         * dvd.setStudio(stringValue); break; case ("note"): stringValue =
         * view.promptString("\nWhat is the new note?"); dvd.setNote(stringValue); break; case
         * ("exit"): break editMenu; default: continue; } } while (true);
         */
    }

    private void removeItem() throws vendPersistenceException, NoItemInventoryException,
            vendInsufficientFundsException {
        view.displayRemoveItemBanner();
        String title = view.getItemTitle();
        vend item = service.getItem(title);
        service.removeItem(item);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
