package dvd.controller;

import dvd.model.DVD;
import dvd.view.UserIO;
import dvd.view.UserIOConsoleImpl;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class dvdController {
    private UserIO io = new UserIOConsoleImpl();
    //private List<String> tempDVD;
    private ArrayList<DVD> listOfDVD = new ArrayList<DVD>();

    public void fillList() {
        JSONParser parser = new JSONParser();
            try {
                JSONArray a = (JSONArray) parser.parse(new FileReader("data.json"));
                for(Object o : a)
                {
                    JSONObject dvdJSON = (JSONObject) o;
                    String titleString = (String) dvdJSON.get("titleString");
                    //System.out.println(titleString);
                    String releaseDate = (String) dvdJSON.get("releaseDate");
                    String MPAA = (String) dvdJSON.get("MPAA");
                    String dirName = (String) dvdJSON.get("dirName");
                    String stuName = (String) dvdJSON.get("stuName");
                    String userName = (String) dvdJSON.get("userName");

                    DVD dvdTemp = new DVD(titleString, releaseDate, MPAA, dirName, stuName, userName);
                    listOfDVD.add(dvdTemp);
                }
                // List<String> tempDVD = Files.readAllLines(java.nio.file.Paths.get("./data.txt"), StandardCharsets.UTF_8);
                // for(String line : tempDVD)
                // {
                //     String[] tokens = line.split(",");
                //     System.out.println(tokens[0]);
                // }
            } catch (IOException | ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                
            }


    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {
            io.print("Main Menu");
            io.print("1. List DVDs");
            io.print("2. Add a DVD");
            io.print("3. View a DVD");
            io.print("4. Remove a DVD");
            io.print("5. Exit");

            menuSelection = io.readInt("Please select from the"
                    + " above choices.", 1, 5);

            switch (menuSelection) {
                case 1:
                    io.print("LIST DVD");
                    break;
                case 2:
                    io.print("ADD DVD");
                    break;
                case 3:
                    io.print("VIEW DVD");
                    break;
                case 4:
                    io.print("REMOVE DVD");
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }

        }
        io.print("GOOD BYE");
    }

    public void listDVDs() 
    {
        
    }
}
