package vend.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import vend.dao.vendAuditDao;
import vend.dao.vendDao;
import vend.dao.vendPersistenceException;
import vend.model.vend;

public class vendServiceLayerImpl implements vendServiceLayer
{
    private vendAuditDao auditDao;
    vendDao dao;

    public vendServiceLayerImpl(vendDao dao, vendAuditDao auditDao)
    {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public ArrayList<vend> getAllItems() throws vendPersistenceException {
        // TODO Auto-generated method stub
        return dao.getAllItems();
    }

    @Override
    public void addItem(vend item) throws vendPersistenceException, vendDataValidationException, NoItemInventoryException, vendInsufficientFundsException {
        // TODO Auto-generated method stub

        validateVendData(item);
        dao.addItem(item);
        auditDao.writeAuditEntry(
            "ITEM " + item.getItemName() + " CREATED.");

    }

    @Override
    public vend getItem(String itemName) throws vendPersistenceException {
        // TODO Auto-generated method stub
        return dao.getItem(itemName);
    }

    @Override
    public void removeItem(vend item) throws vendPersistenceException {
        // TODO Auto-generated method stub
        auditDao.writeAuditEntry("ITEM " + item.getItemName() + " REMOVED.");
        dao.removeItem(item);
        
    }

    @Override
    public void setList(ArrayList<vend> temp) throws vendPersistenceException {
        // TODO Auto-generated method stub
        dao.setList(temp);
    }

    @Override
    public void save() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void load() {
        // TODO Auto-generated method stub
        
    }

    private void validateVendData(vend item) throws vendDataValidationException
    {
        if(item.getNum() == null || item.getItemCost() == null || item.getItemName() == null || item.getNum().
        trim().length() == 0 || item.getItemCost().trim().length() == 0 || item.getItemName().trim().length() == 0)
        {
            throw new vendDataValidationException("ERROR All Fields Required");
        }
    }

    @Override
    public void numUpdate(vend item, String num) throws NoItemInventoryException, vendPersistenceException {
        int temp = Integer.parseInt(num) + 1;
        auditDao.writeAuditEntry("USER SELECTED ITEM " + item.getItemName() + " INVENTORY UPDATE: WENT FROM " + temp + " TO " + num);
        dao.numUpdate(item, num);
    }

    @Override
    public String getChange()
    {
        return dao.getChange();
    }

    @Override
    public void setChange(BigDecimal money)
    {
        try
        {
            auditDao.writeAuditEntry("USER RECEIVED CHANGE OF: $" + money.toString());
        } catch (vendPersistenceException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        dao.setChange(money);
    }

    @Override
    public void clearList() throws vendPersistenceException
    {
        dao.clearItems();
    }

    @Override
    public String changeAsString(String change) {
        // TODO Auto-generated method stub
        try
        {
            auditDao.writeAuditEntry("CHANGE USER RECIEVED IN COINS FROM MACHINE\n========================================================================================================================\n" + change + "\n========================================================================================================================");
        } catch (vendPersistenceException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dao.changeToString(change);
    }

}