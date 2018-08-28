import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.util.List;

public class SearchResultPage {

    @FindBy(className = "title-delivery")
    private WebElement titleResults;

    @FindBy(css = "a.restaurantname")
    private WebElement restaurantNameInList;

    private AppiumDriver<WebElement> driver;
    private WebDriverWait wait;

    SearchResultPage(AppiumDriver<WebElement> driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 30);
    }

    void validateResult(String expectedResult) {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("h2.restaurantname")));

        Assert.assertEquals(expectedResult, getRestaurantNameInList(expectedResult),
                "Restaurant found in results");
    }

    /*
Make a list of all Restaurants found, and find the restaurant in the results
 */
    public String getRestaurantNameInList(String expectedResult) {

        List<WebElement> listOfRestaurants = driver.findElements(By.cssSelector("a.restaurantname"));
        int i = 0;

        for(WebElement restaurant : listOfRestaurants) {

            String restaurantName = restaurant.getText();

            System.out.println(i++ +" "+ restaurantName);

            if (restaurantName.equals(expectedResult)) {
                System.out.println("FOUND");
                return restaurantName;
            }
        }
        return "Not found";
    }
}
