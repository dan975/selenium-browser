package demo.projects.test.framework.driver.abstraction;

import demo.projects.test.framework.annotations.ThreadComponent;
import demo.projects.test.framework.enums.BrowserType;
import demo.projects.test.framework.exceptions.NotImplementedForBrowserTypeException;
import lombok.Getter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.Map;

/**
 * Wrapper for Selenium desktop browser drivers.
 */
@ThreadComponent
@Profile("desktopBrowser")
public class Browser extends DriverWrapper {

    /**
     * Browser type of current driver wrapper instance.
     */
    @Value("${browserType}")
    @Getter
    private BrowserType browserType;

    @Value("${chromeVersion}")
    private String chromeVersion;

    @Value("${downloadDir}")
    private String downloadDir;

    @Override
    public void initDriverHelper() {
        var downloadDirAbsolutePath = System.getProperty("user.dir") + "\\" + this.downloadDir;
        switch (browserType) {
            case FIREFOX:
                //noinspection SpellCheckingInspection
                System.setProperty("webdriver.gecko.driver", browserType.getWinDriverPath());

                //Disable firefox logging
                System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

                //Configure downloads to be enabled in automation and set download directory
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                firefoxProfile.setPreference("browser.download.dir", downloadDirAbsolutePath);
                firefoxProfile.setPreference("browser.download.folderList", 2);
                firefoxProfile.setPreference("browser.download.useDownloadDir", true);
                firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
                firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
                //noinspection SpellCheckingInspection
                firefoxProfile.setPreference("pdfjs.disabled", true);

                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setProfile(firefoxProfile);

                 setDriver(new FirefoxDriver(firefoxOptions));
                break;
            case CHROME:
                var chromeDriverPath = String.format(browserType.getWinDriverPath(), chromeVersion);
                //noinspection SpellCheckingInspection
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);

                //noinspection SpellCheckingInspection
                System.setProperty("webdriver.chrome.silentOutput", "true");

                //Set download directory
                ChromeOptions chromeOptions = new ChromeOptions();
                Map<Object, Object> experimental = new HashMap<>();
                experimental.put("download.default_directory", downloadDirAbsolutePath);
                chromeOptions.setExperimentalOption("prefs", experimental);

                setDriver(new ChromeDriver(chromeOptions));
                break;
            default:
                throw new NotImplementedForBrowserTypeException(browserType);
        }
    }

    /**
     * @return True - if current initialized driver type is for Google Chrome browser.
     * False - if current initialized driver type is not for Google Chrome browser.
     */
    public boolean isChrome() {
        return browserType.equals(BrowserType.CHROME);
    }

    /**
     * @return True - if current initialized driver type is for Firefox browser.
     * False - if current initialized driver type is not for Firefox browser.
     */
    public boolean isFirefox() {
        return browserType.equals(BrowserType.FIREFOX);
    }
}
