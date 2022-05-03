package vend.model;

public enum Change {
    PENNY(0.01), NICKEL(0.05), DIME(0.10), QUARTER(0.25);
    
    private double value;
    
    Change(double value){
        this.value = value;
    }
    
    public double value() { return value; }
    
}