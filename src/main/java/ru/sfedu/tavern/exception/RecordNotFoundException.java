package ru.sfedu.tavern.exception;

/**
 *
 * @author entropy
 */
public class RecordNotFoundException extends Exception{
    private final long num;
    public RecordNotFoundException(long num) {
        super("Record not found");
        this.num = num;
    }

    public long getNum() {
        return num;
    }

}
