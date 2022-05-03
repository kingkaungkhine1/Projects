/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vend.dao;
import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import vend.model.Change;
import vend.model.vend;

/**
 *
 * @author kaung
 */
public class vendDaoFileImpl implements vendDao
{
    private String DATA_FILE = "";
    //public static final String DATA_FILE = "data.json";
    private ArrayList<vend> listOfItems = new ArrayList<vend>();
    private BigDecimal currentChange = new BigDecimal("0");

    public vendDaoFileImpl(){
        this.DATA_FILE = "data.json";
    }

    public vendDaoFileImpl(String dataJSONFile)
    {
        this.DATA_FILE = dataJSONFile;
    }

    public ArrayList<vend> getListOfItems() 
    {
        return this.listOfItems;
    }

    public void setListOfItem(ArrayList<vend> listItems) 
    {
        this.listOfItems = listItems;
    }

    private vend unmarshallItem(Object o) {
        JSONObject itemJSON = (JSONObject) o;

        //change names
        String itemName = (String) itemJSON.get("itemName");
        // System.out.println(titleString);
        String itemCost = (String) itemJSON.get("itemCost");
        String numOfItems = (String) itemJSON.get("numOfItems");

        vend itemTemp = new vend(itemName, itemCost, numOfItems);
        return itemTemp;
    }

    public String marshallItem()
    {
        String json = new Gson().toJson(listOfItems);
        //System.out.println(json);
        return json;
        // JSONObject temp = new JSONObject();
        // for(int i = 0; i < listOfDVD.size(); i++)
        // {
        //     temp.put("titleString", listOfDVD.get(i).getTitleString());
        // }
        // return temp;
        // JsonObject temp = Json.createObjectBuilder()
        // .add("titleString", dvd.getTitleString())
        // .add("releaseDate", dvd.getReleaseDate())
        // .add("MPAA", dvd.getMPAA())
        // .add("dirName", dvd.getDirName())
        // .add("stuName", dvd.getStuName())
        // .add("userName", dvd.getUserNote())
        // .build();
        
        // return temp;
        //
        // String dvdAsText = dvd.getTitleString() + DELIMITER;
        // dvdAsText += dvd.getReleaseDate()* + DELIMITER;
        // dvdAsText += dvd.getMPAA() + DELIMITER;
        // dvdAsText += dvd.getStuName() + DELIMITER;
        // dvdAsText += dvd.getUserNote() + DELIMITER;

        // return ;
    }

    public void writeItems() throws vendPersistenceException {
        
        //FileWriter filew;
        try
        {
            // File file = new File("test.json");
            // FileWriter filew = new FileWriter("test.json");
            FileWriter fileWriter = new FileWriter(DATA_FILE);
            // //JSONArray arrTemp = marshallDVD();
            String temp = new Gson().toJson(listOfItems);
            //System.out.println(temp);
            // filew.write(temp.toJSONString());
            // filew.flush();
            // //filew.close();
            //BufferedWriter writer = Files.newBufferedWriter(Paths.get("data.json"));

            new FileWriter(DATA_FILE, false).close();

            fileWriter.write(temp);
            fileWriter.close();
            //System.out.println("Test SAVED?");
            //writer.close();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        // JsonArray jsonTemp = Json.createArrayBuilder()
        // .build();
        // try
        // {
        //     StringWriter writer = new StringWriter();
        //     //JsonWriter jsonWriter = Json.createWriter(new FileOutputStream("data.txt"));
        //     JsonWriter jsonWriter = Json.createWriter(new PrintWriter(writer));
        //     for(DVD d: listOfDVD)
        //     {
        //         JsonObject temp = marshallDVD(d);
        //         jsonTemp.add(temp); 
        //     }
        //     jsonWriter.writeArray(jsonTemp);
        //     jsonWriter.close();

        //     FileWriter fstream = new FileWriter("test.json");
        //     BufferedWriter out = new BufferedWriter(fstream);
        //     out.write(writer.toString());
        //     out.close();

        // } catch (IOException e)
        // {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
    }

    public void loadItems() throws vendPersistenceException {
        listOfItems.clear();
        try
        {
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader(DATA_FILE));
            // if(a != null)
            // {
            //     a.clear();
            // }
            for (Object o : a)
            {
                // JSONObject dvdJSON = (JSONObject) o;
                // String titleString = (String) dvdJSON.get("titleString");
                // // System.out.println(titleString);
                // String releaseDate = (String) dvdJSON.get("releaseDate");
                // String MPAA = (String) dvdJSON.get("MPAA");
                // String dirName = (String) dvdJSON.get("dirName");
                // String stuName = (String) dvdJSON.get("stuName");
                // String userName = (String) dvdJSON.get("userName");

                // DVD dvdTemp = new DVD(titleString, releaseDate, MPAA, dirName, stuName, userName);
                // return dvdTemp;
                vend itemTemp = unmarshallItem(o);
                
                listOfItems.add(itemTemp);
            }
            // List<String> tempDVD =
            // Files.readAllLines(java.nio.file.Paths.get("./data.txt"),
            // StandardCharsets.UTF_8);
            // for(String line : tempDVD)
            // {
            // String[] tokens = line.split(",");
            // System.out.println(tokens[0]);
            // }
        } 
        catch (IOException | org.json.simple.parser.ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }    
    }




    @Override
    public ArrayList<vend> getAllItems() throws vendPersistenceException{
        loadItems();
        return listOfItems;
    }


    @Override
    public vend getItem(String itemName) throws vendPersistenceException{
        loadItems();
        for(vend d : listOfItems)
        {
            if(d.getItemName().equals(itemName))
            {
                return d;
            }
        }
        return null;
    }

    @Override
    public void removeItem(vend item) throws vendPersistenceException{
        loadItems();
        listOfItems.remove(item);
        writeItems();
    }


    @Override
    public void addItem(vend item) throws vendPersistenceException{
        loadItems();
        vend temp = item;
        listOfItems.add(temp);
        writeItems();
    }

    @Override
    public void setList(ArrayList<vend> temp) throws vendPersistenceException {
        listOfItems = temp;
    }

    @Override
    public void save() throws vendPersistenceException{
        writeItems();
    }

    @Override
    public void load() throws vendPersistenceException {
        loadItems();        
    }

    @Override
    public void numUpdate(vend item, String num) throws vendPersistenceException {
        loadItems();
        int index = listOfItems.indexOf(item);
        listOfItems.get(index).setNum(num);
        writeItems();
    }

    @Override
    public String getChange() {
        // TODO Auto-generated method stub
        BigDecimal quarter = BigDecimal.valueOf(Change.valueOf("QUARTER").value());
        BigDecimal dime = BigDecimal.valueOf(Change.valueOf("DIME").value());
        BigDecimal nickel = BigDecimal.valueOf(Change.valueOf("NICKEL").value());
        BigDecimal penny = BigDecimal.valueOf(Change.valueOf("PENNY").value());

        quarter = currentChange.divide(quarter, RoundingMode.FLOOR).setScale(2);
        currentChange = currentChange.remainder(new BigDecimal("0.25"));
        dime = currentChange.divide(dime, RoundingMode.FLOOR).setScale(2);
        currentChange = currentChange.remainder(new BigDecimal("0.10"));
        nickel = currentChange.divide(nickel, RoundingMode.FLOOR).setScale(2);
        currentChange = currentChange.remainder(new BigDecimal("0.05"));
        penny = currentChange.divide(penny, RoundingMode.FLOOR).setScale(2);
        currentChange = currentChange.remainder(new BigDecimal("0.01"));

        return "Quarters: " + quarter.setScale(0, RoundingMode.FLOOR).setScale(2).intValue() + "\nDime: " + dime.setScale(0, RoundingMode.FLOOR).setScale(2).intValue() + "\nNickel: " + nickel.setScale(0, RoundingMode.FLOOR).setScale(2).intValue() + "\nPenny: " + penny.setScale(0, RoundingMode.FLOOR).setScale(2).intValue();
    }

    @Override
    public void setChange(BigDecimal money) {
        currentChange = money;
    }

    @Override
    public void clearItems() throws vendPersistenceException
    {
        loadItems();
        for(vend d: listOfItems)
        {
            removeItem(d);
        }
        listOfItems.clear();
        listOfItems = new ArrayList<>();
        writeItems();
    }

    @Override
    public String changeToString(String change) {
        // TODO Auto-generated method stub
        return change;
    }
}
