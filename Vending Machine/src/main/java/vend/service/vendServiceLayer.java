package vend.service;
import java.math.BigDecimal;
import java.util.ArrayList;
import vend.dao.vendPersistenceException;
import vend.model.vend;
/**
 *
 * @author kaung
 */
public interface vendServiceLayer
{
	public ArrayList<vend> getAllItems() throws vendPersistenceException;

    
    public void addItem(vend item) throws NoItemInventoryException, vendInsufficientFundsException, vendPersistenceException, vendDataValidationException;

    public vend getItem(String itemName) throws vendPersistenceException;

    public void removeItem(vend item) throws vendPersistenceException;

    public void setList(ArrayList<vend> temp) throws vendPersistenceException;

    public void numUpdate(vend item, String num) throws NoItemInventoryException, vendPersistenceException; 
    
    public String getChange();

    public void setChange(BigDecimal money);
    
    public void clearList() throws vendPersistenceException;

    public String changeAsString(String changes);

    public void save(); 

    public void load();
}
