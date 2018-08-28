import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

public class ExampleTest extends TakeAwayTest{
    private  AppiumDriver<WebElement> driver;


    @Test
    void testExample() throws InterruptedException {
        System.out.println("Test");
        final String searchString = "Orteliuslaan 1000, Utrecht";
        final String expectedResult = "Supervlaai";
        takeAwayHomepage.searchRestaurant(searchString);
        searchResultPage.validateResult(expectedResult);
    }
}
