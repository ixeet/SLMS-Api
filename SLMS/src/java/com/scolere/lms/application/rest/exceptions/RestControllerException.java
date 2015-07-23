/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.exceptions;

/**
 *
 * @author dell
 */
public class RestControllerException extends Exception {

    /**
     * Creates a new instance of
     * <code>RestControllerException</code> without detail message.
     */
    public RestControllerException() {
    }

    /**
     * Constructs an instance of
     * <code>RestControllerException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public RestControllerException(String msg) {
        super(msg);
    }
}
