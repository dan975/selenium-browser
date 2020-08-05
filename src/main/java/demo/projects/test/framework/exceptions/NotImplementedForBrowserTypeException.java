package demo.projects.test.framework.exceptions;

import demo.projects.test.framework.enums.BrowserType;

/**
 * Exception for components that are not implemented for {@link BrowserType}.
 */
public class NotImplementedForBrowserTypeException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Not implemented for the requested browser type: %s";

    /**
     * Constructor for default message exception.
     * @param browserType Browser type for which to throw the exception.
     */
    public NotImplementedForBrowserTypeException(BrowserType browserType) {
        super(String.format(DEFAULT_MESSAGE, browserType));
    }

}
