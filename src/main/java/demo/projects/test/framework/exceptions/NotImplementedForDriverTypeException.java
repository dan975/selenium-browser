package demo.projects.test.framework.exceptions;

import demo.projects.test.framework.enums.DriverType;

/**
 * Exception for components that are not implemented for {@link DriverType} value.
 */
public class NotImplementedForDriverTypeException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Not implemented for the requested driver type: %s";

    /**
     * Constructor for default message exception.
     * @param driverType Driver type for which to throw the exception.
     */
    public NotImplementedForDriverTypeException(DriverType driverType) {
        super(String.format(DEFAULT_MESSAGE, driverType));
    }
}
