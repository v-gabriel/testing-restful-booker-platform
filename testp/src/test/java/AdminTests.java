import com.google.gson.JsonObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import org.openqa.selenium.By;
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
import pom.helpers.RESTClient;
import pom.pages.AdminLoginPage;
import pom.pages.AdminPage;
import java.util.List;

public class AdminTests {

    public WebDriver webDriver;
    public WebDriverWait wait;
    AdminLoginPage adminLoginPage;
    AdminPage adminPage;

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

        this.wait = new WebDriverWait(webDriver, Constants.timeOutInSeconds);

        webDriver.manage().window().maximize();
        webDriver.navigate().to(Constants.adminBaseUrl);

        this.adminLoginPage = new AdminLoginPage(webDriver);
        this.adminPage = new AdminPage(webDriver);

        this.adminLoginPage.closeHelpCardElement();
    }

    /**
     * Admin login test.
     * Checks if auth response is 200 and page redirect is successful (logout element exists).
     * Tests both API and UI.
     * @param username
     * Admin username
     * @param password
     * Admin password
     */
    @Test
    @Parameters({"username", "password"})
    public void adminLoginTest(String username, String password) throws InterruptedException {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("password", password);

        Response response = RESTClient.adminPostLogin(username, password);
        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/);

        this.adminLoginPage.fillUsername(username);
        this.adminLoginPage.fillPassword(password);
        this.adminLoginPage.submitLoginForm();

        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.adminPage.logoutLocator));
        WebElement logoutElement = this.adminPage.getLogoutElement();

        Assert.assertNotNull(logoutElement);
    }

    /**
     * Admin add room test (all).
     * Fills all available parameters. Gets initial room count and compares it with new room count after insert.
     * Tests both API and UI.
     */
    @Test
    @Parameters({"username", "password", "inputsStringValue", "inputsIntegerValue"})
    public void adminAddRoomTest_All(
            String username,
            String password,
            String inputsStringValue,
            int inputsIntegerValue
    ) throws InterruptedException {
        this.adminLoginPage.fillUsername(username);
        this.adminLoginPage.fillPassword(password);
        this.adminLoginPage.submitLoginForm();

        List<Number> roomIds = RESTClient.getRoomIds();
        int initialRoomCount = roomIds.size();

        this.adminPage.randomlySelectCheckboxInputElements();
        this.adminPage.fillRoomName(inputsStringValue);
        this.adminPage.randomlySelectRoomType();
        this.adminPage.fillRoomPrice(inputsIntegerValue);
        this.adminPage.randomlySelectRoomAccessibility();

        this.adminPage.submitRoomCreationForm();

        roomIds = RESTClient.getRoomIds();
        int finalRoomCount = roomIds.size();
        int newRoomId = roomIds.get(finalRoomCount - 1).intValue();

        By roomLocator =  By.id(this.adminPage.roomBaseId.replace("{0}", String.valueOf(newRoomId)));
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(roomLocator));
        WebElement newCreatedElement = this.adminPage.getRoomElementById(newRoomId);

        Assert.assertNotNull(newCreatedElement);
        Assert.assertTrue(initialRoomCount < finalRoomCount);
    }


    /**
     * Admin add room test (required).
     * Fills only required params. Gets initial room count and compares it with new room count after insert.
     * Tests both API and UI.
     */
    @Test
    @Parameters({"username", "password", "inputsStringValue", "inputsIntegerValue"})
    public void adminAddRoomTest_Required(
            String username,
            String password,
            String inputsStringValue,
            int inputsIntegerValue
    ) throws InterruptedException {
        adminLoginTest(username, password);

        List<Number> roomIds = RESTClient.getRoomIds();
        int initialRoomCount = roomIds.size();

        this.adminPage.fillRoomName(inputsStringValue);
        this.adminPage.fillRoomPrice(inputsIntegerValue);

        this.adminPage.submitRoomCreationForm();

        roomIds = RESTClient.getRoomIds();
        int finalRoomCount = roomIds.size();
        int newRoomId = roomIds.get(finalRoomCount - 1).intValue();

        WebElement newCreatedElement = this.adminPage.getRoomElementById(newRoomId);

        Assert.assertNotNull(newCreatedElement);
        Assert.assertTrue(initialRoomCount < finalRoomCount);
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
