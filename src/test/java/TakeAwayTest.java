import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class TakeAwayTest {

    private static AppiumDriver<WebElement> driver;
    TakeAwayHomepage takeAwayHomepage;
    SearchResultPage searchResultPage;

    String homeUrl = "http://thuisbezorgd.nl";


    @BeforeTest
    @Parameters({"platformName","automationName", "browserName", "platformVersion", "deviceName","udid"})
    static void setUp(String platformName, String automationName, String browserName, String platformVersion,
                          String deviceName,String udid) throws MalformedURLException {
        System.out.println("Before Test");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("automationName", automationName);
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("autoGrantPermissions", "true");

        try
        {
            if (platformName.equals("iOS")) {
                driver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            }else{
                driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            }
        }
        catch (WebDriverException exception){
            Assert.fail(deviceName+ " has an error: " +exception.getMessage());
        }


        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    @BeforeMethod
    @Parameters({"platformName"})
    void testSetUp(String platformName) throws InterruptedException {
        System.out.println("Before Method");
        takeAwayHomepage = new TakeAwayHomepage(driver);
        searchResultPage = new SearchResultPage(driver);
        driver.get(homeUrl);
        takeAwayHomepage.waitForHome(platformName);
    }

    @AfterTest
    static void tearDown(){
        System.out.println("After Test");
        driver.quit();
    }
}
