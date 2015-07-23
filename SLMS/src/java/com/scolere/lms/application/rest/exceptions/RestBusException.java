/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.exceptions;

/**
 *
 * @author dell
 */
public class RestBusException extends Exception {

    /**
     * Creates a new instance of
     * <code>RestBusException</code> without detail message.
     */
    public RestBusException() {
    }

    /**
     * Constructs an instance of
     * <code>RestBusException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public RestBusException(String msg) {
        super(msg);
    }
}
