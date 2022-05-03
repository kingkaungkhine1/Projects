package dvd.main;

import dvd.controller.dvdController;

public class DVDmain {
    public static void main(String[] args)
    {
        dvdController controller = new dvdController();
        controller.fillList();
        controller.run();
    }
}
