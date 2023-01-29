package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pom.helpers.WebHelper;
import java.util.ArrayList;
import java.util.List;

public class UserPage {
    private final WebDriver webDriver;
    private final WebHelper webHelper;

    public UserPage(WebDriver driver) {
        this.webDriver = driver;
        this.webHelper = new WebHelper(webDriver);
    }

    final By helpButtonLocator = By.xpath("/html/body/div/div[1]/div/div/div[3]/div[2]/button");

    final By openBookingFormButtonLocator = By.cssSelector("button.btn:nth-child(5)");
    final By submitBookingButtonLocator = By.cssSelector("button.btn:nth-child(6)");
    final By[] bookingInfoStringLocators = {
            By.cssSelector(".room-firstname"),
            By.cssSelector(".room-lastname"),
    };
    final By bookingInfoEmailLocator = By.cssSelector(".room-email");
    final By bookingInfoPhoneLocator = By.cssSelector(".room-phone");
    final public By bookingInfoErrorsLocator = By.xpath("/html/body/div/div/div/div[4]/div/div[2]/div[3]/div[5]");

    final By[] contactInfoStringLocators = {
            By.cssSelector("#name"),
            By.cssSelector("#subject"),
    };
    final By contactInfoDescriptionLocator = By.cssSelector("#description");
    final By contactInfoPhoneLocator = By.cssSelector("#phone");
    final By contactInfoEmailLocator = By.cssSelector("#email");
    final By submitContactButtonLocator =  By.id("submitContact");
    final public By postSubmittedContactElementLocator = By.cssSelector("div.col-sm-5:nth-child(2) > div:nth-child(1) > h2:nth-child(1)");

    public void closeHelpCardElement(){
        WebElement helpCardCloseElement = webDriver.findElement(helpButtonLocator);
        this.webHelper.scrollToElement(helpCardCloseElement);

        if(helpCardCloseElement != null)
        {
            helpCardCloseElement.click();
        }
    }

    public void openBookingForm(){
        WebElement openBookingFormElement = webDriver.findElement(openBookingFormButtonLocator);
        this.webHelper.scrollToElement(openBookingFormElement);

        openBookingFormElement.click();
    }

    public void fillBookingInfoStringInputs(String value){
        List<WebElement> bookingInfoStringInputElements = new ArrayList<>();
        for (By l : bookingInfoStringLocators) {
            WebElement el = webDriver.findElement(l);
            bookingInfoStringInputElements.add(el);
        }

        for (WebElement el : bookingInfoStringInputElements) {
            this.webHelper.scrollToElement(el);

            el.sendKeys(value);
        }
    }

    public void fillBookingInfoEmail(String value){
        WebElement emailInputElement = webDriver.findElement(bookingInfoEmailLocator);
        this.webHelper.scrollToElement(emailInputElement);

        emailInputElement.sendKeys(value);
    }

    public void fillBookingInfoPhone(String value){
        WebElement phoneInputElement = webDriver.findElement(bookingInfoPhoneLocator);
        this.webHelper.scrollToElement(phoneInputElement);

        phoneInputElement.sendKeys(value);
    }

    public void submitBooking(){
        WebElement submitBookingElement = webDriver.findElement(submitBookingButtonLocator);
        this.webHelper.scrollToElement(submitBookingElement);

        submitBookingElement.click();
    }

    public void fillContactInfoStringInputs(String value){
        List<WebElement> contactInfoStringInputElements = new ArrayList<>();
        for (By l : contactInfoStringLocators) {
            WebElement el = webDriver.findElement(l);
            contactInfoStringInputElements.add(el);
        }

        for (WebElement el : contactInfoStringInputElements) {
            this.webHelper.scrollToElement(el);

            el.sendKeys(value);
        }
    }

    public void fillContactInfoPhone(String value){
        WebElement phoneInputElement = webDriver.findElement(contactInfoPhoneLocator);
        this.webHelper.scrollToElement(phoneInputElement);

        phoneInputElement.sendKeys(value);
    }

    public void fillContactInfoEmail(String value){
        WebElement phoneInputElement = webDriver.findElement(contactInfoEmailLocator);
        this.webHelper.scrollToElement(phoneInputElement);

        phoneInputElement.sendKeys(value);
    }

    public void fillContactInfoDescription(String value){
        WebElement phoneInputElement = webDriver.findElement(contactInfoDescriptionLocator);
        this.webHelper.scrollToElement(phoneInputElement);

        phoneInputElement.sendKeys(value);
    }

    public void submitContactInfo(){
        WebElement submitContactElement = webDriver.findElement(submitContactButtonLocator);
        this.webHelper.scrollToElement(submitContactElement);

        submitContactElement.click();
    }

    public WebElement getPostContactSubmitResponseElement(){
        WebElement postSubmittedContactElement = webDriver.findElement(postSubmittedContactElementLocator);
        this.webHelper.scrollToElement(postSubmittedContactElement);

        return postSubmittedContactElement;
    }

    public WebElement getBookingFormErrorsElement(){
        WebElement bookingInfoErrorsElement = webDriver.findElement(bookingInfoErrorsLocator);
        this.webHelper.scrollToElement(bookingInfoErrorsElement);

        return bookingInfoErrorsElement;
    }
}
