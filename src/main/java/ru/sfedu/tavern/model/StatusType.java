package ru.sfedu.tavern.model;

/**
 *
 * @author entropy
 */
public enum StatusType {
    
    /**
     *
     */
    OK("OK"),

    /**
     *
     */
    ERROR("ERROR");
    
    private String description;
    private StatusType(){}
    
    private StatusType(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }  
    
}
