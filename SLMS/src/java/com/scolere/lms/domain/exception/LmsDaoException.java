/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.domain.exception;

/**
 *
 * @author dell
 */
public class LmsDaoException extends Exception {

    /**
     * Creates a new instance of
     * <code>LmsDaoException</code> without detail message.
     */
    public LmsDaoException() {
    }

    /**
     * Constructs an instance of
     * <code>LmsDaoException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public LmsDaoException(String msg) {
        super("Exception occured at dao layer : "+msg);
    }
}
