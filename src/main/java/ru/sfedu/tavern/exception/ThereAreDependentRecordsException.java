/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.exception;

/**
 *
 * @author entropy
 */
public class ThereAreDependentRecordsException extends Exception {

    /**
     * Creates a new instance of <code>ThereAreDependentElementsException</code>
     * without detail message.
     */
    public ThereAreDependentRecordsException() {
        super("There are dependent records");
    }

    /**
     * Constructs an instance of <code>ThereAreDependentElementsException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ThereAreDependentRecordsException(String msg) {
        super(msg);
    }
}
