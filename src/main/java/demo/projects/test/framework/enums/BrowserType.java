package demo.projects.test.framework.enums;

/**
 * Enum for implemented desktop browser clients.
 * Enum values contain getter method for Windows driver path.
 */
public enum BrowserType {
    /**
     * Firefox browser client enum.
     */
    FIREFOX("geckodriver.exe"),

    /**
     * Google Chrome browser client enum.
     * Returned path requires %s to be replaced with chrome driver version.
     */
    CHROME("chrome/%s/chromedriver.exe");

    /**
     * Path under which all drivers are located.
     */
    private static final String DRIVER_DIR = "drivers/";

    /**
     * Windows driver path under {@link BrowserType#DRIVER_DIR} directory.
     */
    private final String winDriverPath;

    /**
     * Constructor sets path for Windows driver path.
     * @param winDriverPath Windows driver path under {@link BrowserType#DRIVER_DIR} directory.
     */
    BrowserType(String winDriverPath) {
        this.winDriverPath = winDriverPath;
    }

    /**
     * Get Windows driver path.
     * @return Windows driver path.
     */
    public String getWinDriverPath() {
        return DRIVER_DIR + winDriverPath;
    }


}
