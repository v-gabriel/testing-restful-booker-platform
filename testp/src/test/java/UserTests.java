import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pom.helpers.Constants;
import pom.pages.UserPage;

public class UserTests {
    public WebDriver webDriver;
    public WebDriverWait wait;
    public UserPage userPage;

    /**
     * Executes before each Test tag in testng.xml.
     * @param browser
     * Browser flag.
     * @throws Exception
     * Web driver init failed.
     */
    @BeforeMethod
    @Parameters("browser")
    public void setupTest(String browser) throws Exception {

        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            webDriver = new EdgeDriver();
        }
        else{
            throw new Exception(
                    "Error while setting up web driver." +
                            " Check 'browser' param in 'testng.xml' or manually setup web drivers."
            );
        }
        webDriver.manage().window().maximize();
        webDriver.navigate().to(Constants.userBaseUrl);

        this.wait = new WebDriverWait(webDriver, Constants.timeOutInSeconds);

        this.userPage = new UserPage(webDriver);

        this.userPage.closeHelpCardElement();
    }

    /**
     * User add booking test. Fails if errors popup is present on booking form.
     */
    @Test
    @Parameters({"inputsStringValue", "inputsEmailValue", "inputsPhoneValue"})
    public void userAddBooking(
            String inputsStringValue,
            String inputsEmailValue,
            String inputsPhoneValue
    ) throws InterruptedException {

        this.userPage.openBookingForm();

        this.userPage.fillBookingInfoStringInputs(inputsStringValue);
        this.userPage.fillBookingInfoEmail(inputsEmailValue);
        this.userPage.fillBookingInfoPhone(inputsPhoneValue);

        this.userPage.submitBooking();

        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.userPage.bookingInfoErrorsLocator));
        WebElement bookingInfoErrorsElement = this.userPage.getBookingFormErrorsElement();

        Assert.assertNull(bookingInfoErrorsElement);
    }

    /**
     * User submit contact test.
     */
    @Test
    @Parameters({"inputsStringValue", "inputsDescriptionValue", "inputsEmailValue", "inputsPhoneValue"})
    public void userSubmitContact(
            String inputsStringValue,
            String inputsDescriptionValue,
            String inputsEmailValue,
            String inputsPhoneValue
    ) throws InterruptedException {

        this.userPage.fillContactInfoStringInputs(inputsStringValue);

        this.userPage.fillContactInfoDescription(inputsDescriptionValue);
        this.userPage.fillContactInfoPhone(inputsPhoneValue);
        this.userPage.fillContactInfoEmail(inputsEmailValue);

        this.userPage.submitContactInfo();

        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.userPage.postSubmittedContactElementLocator));
        WebElement postSubmittedContactElement = this.userPage.getPostContactSubmitResponseElement();

        Assert.assertNotNull(postSubmittedContactElement);
    }

    /**
     * Executes after each Test tag in testng.xml.
     */
    @AfterMethod
    public void teardownTest() {
        webDriver.manage().deleteAllCookies();
        webDriver.quit();
    }
}
