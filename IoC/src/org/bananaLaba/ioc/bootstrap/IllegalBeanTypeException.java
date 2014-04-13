package org.bananaLaba.ioc.bootstrap;

public class IllegalBeanTypeException extends Exception {

    private static final long serialVersionUID = -3773146968219138518L;

    public IllegalBeanTypeException() {
    }

    public IllegalBeanTypeException(final String message) {
        super(message);
    }

    public IllegalBeanTypeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public IllegalBeanTypeException(final Throwable cause) {
        super(cause);
    }

}
