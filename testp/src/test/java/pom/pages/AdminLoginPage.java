package pom.pages;

import pom.helpers.WebHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminLoginPage {
    WebHelper webHelper;
    public WebDriver webDriver;

    public AdminLoginPage(WebDriver driver) {
        this.webDriver = driver;
        this.webHelper = new WebHelper(webDriver);
    }

    final By helpButtonLocator = By.xpath("/html/body/div/div[1]/div/div/div[3]/div[2]/button");
    final By usernameInputLocator = By.id("username");
    final By passwordInputLocator = By.id("password");
    final By loginLocator = By.id("doLogin");

    public void fillPassword(String password)
    {
        WebElement passwordElement = webDriver.findElement(passwordInputLocator);
        this.webHelper.scrollToElement(passwordElement);

        passwordElement.sendKeys(password);
    }

    public void fillUsername(String username)
    {
        WebElement usernameElement = webDriver.findElement(usernameInputLocator);
        this.webHelper.scrollToElement(usernameElement);

        usernameElement.sendKeys(username);
    }

    public void submitLoginForm()
    {
        WebElement loginButton = webDriver.findElement(loginLocator);
        this.webHelper.scrollToElement(loginButton);

        loginButton.click();
    }

    public void closeHelpCardElement(){
        WebElement helpCardCloseElement = webDriver.findElement(helpButtonLocator);
        this.webHelper.scrollToElement(helpCardCloseElement);

        if(helpCardCloseElement != null)
        {
            helpCardCloseElement.click();
        }
    }
}
