import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;

class TakeAwayHomepage {


    @FindBy(name = "mysearchstring")
    private WebElement searchTextField;

    @FindBy(id = "submit_deliveryarea")
    private WebElement submitSearchButton;

    @FindBy(id = "privacybanner")
    private WebElement cookyClose;

    private AppiumDriver<WebElement> driver;
    private WebDriverWait wait;

    TakeAwayHomepage(AppiumDriver<WebElement> driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }


    void waitForHome(String platformName) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(
                cookyClose));

        //accept cookie
        cookyClose.click();

        if (platformName.equals("iOS")) {
            //allow location service thuisbezorgd.nl
            Thread.sleep(1000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            HashMap<String, String> tapObject = new HashMap<String, String>();
            tapObject.put("action", "accept");
            tapObject.put("Title", "OK");
            js.executeScript("mobile:alert", tapObject);
        } else {
            //Android
            //allow location service thuisbezorgd.nl
            driver.context("NATIVE_APP").findElement(By.id("android:id/button1")).click();
            //switch back to Chrome
            driver.context("CHROMIUM");
        }

    }

    void searchRestaurant(String searchString) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(
                searchTextField));
        searchTextField.sendKeys(searchString);

        Thread.sleep(1000);

        String xpathRestaurant = "//*[text()[contains(.,'" + searchString + "')]]";
        driver.findElement(By.xpath(xpathRestaurant)).click();

    }

}
