package vend.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
import dvd.controller.dvdController;
import dvd.dao.dvdDao;
import dvd.dao.dvdDaoFileImpl;
import dvd.view.UserIO;
import dvd.view.UserIOConsoleImpl;
import dvd.view.dvdView;
*/

import vend.controller.vendController;
import vend.dao.vendAuditDao;
import vend.dao.vendAuditDaoImpl;
import vend.dao.vendDao;
import vend.service.NoItemInventoryException;
import vend.service.vendDataValidationException;
import vend.service.vendInsufficientFundsException;
import vend.service.vendServiceLayerImpl;
import vend.dao.vendDaoFileImpl;
import vend.dao.vendPersistenceException;
import vend.view.*;

public class vendMain {
    public static void main(String[] args) throws NoItemInventoryException, vendInsufficientFundsException, vendDataValidationException, vendPersistenceException
    {
        // UserIO myIO = new UserIOConsoleImpl();
        // vendView myView = new vendView(myIO);
        // vendDao myDao = new vendDaoFileImpl();

        // vendAuditDao myAuditDao = new vendAuditDaoImpl();

        // vendServiceLayerImpl myService = new vendServiceLayerImpl(myDao, myAuditDao);
        // vendController controller = new vendController(myView, myService);
        // //controller.fillList();
        // controller.run();

        ApplicationContext appContext
                = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        vendController controller = appContext.getBean("controller", vendController.class);
        controller.run();
    }
}
