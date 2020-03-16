/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.math.BigDecimal;

/**
 *
 * @author kaungkhine
 */
public class storedData
{
    private BigDecimal left;
    private BigDecimal right;
    private String operator; 
    private BigDecimal fin; 
            
    public storedData(BigDecimal l, BigDecimal r, String o, BigDecimal f)
    {
        this.left = l;
        this.right = r;
        this.operator = o;
        this.fin = f;
    }
    
    public storedData getData()
    {
        storedData data = new storedData(left, right, operator, fin);
        return data;
    }
    
    public BigDecimal getLeft()
    {
        return left;
    }
    
    public BigDecimal getRight()
    {
        return right;
    }
    
    public String getOP()
    {
        return operator; 
    }
    
    public BigDecimal getFin()
    {
        return fin;
    }
    
    
    @Override
    public String toString()
    {
        return left + " " + operator + " " + right + " = " + fin;
    }
}
