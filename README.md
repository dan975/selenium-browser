# Selenium browser automation demo test suite

## Intro
Selenium browser automation demo test suite.
Test suite for test automation on Windows desktop, Android browser applications.

Test suite has Selenium driver implementations for Firefox and Google Chrome desktop browsers.
Android Google Chrome browser and simulation of Android app test automation.

Image comparison Sikuli implementation present for asserting GUI node screenshots. 
Pdfbox implementation present for assertions of pdf file content. 

Test execution logs present under logs/ directory after test run. 
Logger configuration can be modified in src\main\resources\logback.xml file.

When executing from CLI surefire plugin configured to execute test suite in parallel.
Parallel execution implemented for desktop browser only. For Android test automation 
parallel execution not supported. Parallel execution options can be configured in 
pom.xml file under "maven-surefire-plugin" plugin configuration.

Allure report serves Cucumber scenario report.

Javadoc present under javadoc/ directory.

Test suite built on one device using one mobile device.
Compatibility issues with other devices likely.

## Dependency notes
Test suite built using AdoptOpenJDK 14 with Hotspot JVM.
OpenJ9 JVM(preferred) with AdoptOpenJDK 14 has compatibility issues with Lombok.

Not using Cucumber lambda step definitions because of issues with JDK version 12+ 
and using Cucumber major version 5 for compatibility with Allure reporting.

Using switch statements over switch expressions for compatibility with checkstyle plugin.

Using Junit4 with Cucumber to avoid any parallel execution issues with Junit5.

## Set up
### Desktop browser
For set up on desktop browser:
Install AdoptOpenJDK 14 with HotSpot JVM 
(https://adoptopenjdk.net/?variant=openjdk14&jvmVariant=hotspot)
and set the JAVA_HOME system property to the JDK install path and 
also add the install directory to the system Path property.

Install Maven (http://maven.apache.org/)
And set the MAVEN_HOME and M2_HOME system properties to the Maven install path.
(https://www.baeldung.com/install-maven-on-windows-linux-mac)

Install Allure:
Install allure command-line application, for Windows can use Scoop(https://scoop.sh/) command-line installer
https://docs.qameta.io/allure/#_installing_a_commandline

Install Git bash to execute bash scripts on Windows
(https://gitforwindows.org/)

Install browser client application for testing:
For executing test suite on desktop browser 
install the appropriate browser client.

For Firefox:
https://www.mozilla.org/en-US/firefox/new/

For Google Chrome:
https://www.google.com/chrome/

### Additional for Android automation
Additional set up for executing test suite on Android.

For installing NodeJS, Android SDK, Appium follow the Appium Pro article:
https://appiumpro.com/editions/91-getting-started-with-appium-for-android-on-windows

For executing tests on a physical device enable USB debugging on the device.
Follow the steps under "Enable USB Debugging Mode In Mobile Device":
https://www.softwaretestinghelp.com/appium-configure-mobile-device-with-system/

## Test suite execution
### Image comparison
In case of encountering issues with image comparison. Image comparison can be switched off 
in src\main\resources\application.properties by setting 
the "enableImageComparison" property value to false.

### Desktop browser
To execute the test suite on desktop browser
open Git Bash and cd into the project's bash-scripts/ directory.

Then execute: 

    ./executeTestSuite.sh dev desktopBrowser

or 

    bash executeTestSuite.sh dev desktopBrowser

To change the desktop browser client on which the test suite is executed
change the "browserType" property value in src\main\resources\application.properties

For Chrome different version automation also set the "chromeVersion" property value.
Test suite set up to execute with Chrome major version 84 only.
To run on other versions add the Chrome driver under drivers/ directory in same manner as for version 84.

### Android browser and app
To execute the test suite on Android Google Chrome browser
connect Android physical device by USB cable, open Git Bash 
and cd into the project's bash-scripts/ directory.

Then start the Appium server by executing for Chrome version 84:

    ./startAppiumServer.sh 84
    
or execute following command from any directory for any Chrome browser version installed on device:

    appium --allow-insecure chromedriver_autodownload

See https://appiumpro.com/editions/93-managing-chromedriver-for-android-chrome-and-webview-testing

Then execute: 

For Android as browser page testing:

    ./executeTestSuite.sh dev androidBrowser

For Android as application testing:

    ./executeTestSuite.sh dev androidApp

