package ru.sfedu.tavern.exception;

/**
 *
 * @author entropy
 */
public class RecordNotFoundException extends Exception{
    
    public RecordNotFoundException() {
        super("Record not found");
    }

}
