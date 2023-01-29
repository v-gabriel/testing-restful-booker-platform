package pom.pages;

import pom.helpers.WebHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class AdminPage
{
    public WebDriver webDriver;
    public WebHelper webHelper;

    public AdminPage(WebDriver driver) {
        this.webDriver = driver;
        this.webHelper = new WebHelper(webDriver);
    }

    final public By logoutLocator = By.cssSelector(".ml-auto > li:nth-child(3) > a:nth-child(1)");
    final By roomNameInputLocator = By.id("roomName");
    final By[] roomDetailsCheckboxLocators = new By[]{
            By.cssSelector("#wifiCheckbox"),
            By.cssSelector("#tvCheckbox"),
            By.cssSelector("#radioCheckbox"),
            By.cssSelector("#refreshCheckbox"),
            By.cssSelector("#safeCheckbox"),
            By.cssSelector("#viewsCheckbox")
    };
    final By typeDropdownLocator = By.id("type");
    final String typeDropdownBaseCssSelector = "#type > option:nth-child({0})";
    final int typeDropdownOptionNumber = 5;
    final By isAccessibleDropdownLocator = By.id("accessible");
    final String isAccessibleOptionBaseCssSelector = "#accessible > option:nth-child({0})";
    final int isAccessibleDropdownOptionNumber = 2;
    final By priceInputLocator = By.id("roomPrice");
    final By createRoomLocator = By.id("createRoom");
    final public String roomBaseId = "room{0}";

    public void fillRoomName(String value) {
        WebElement roomNameInputElement = webDriver.findElement(roomNameInputLocator);
        this.webHelper.scrollToElement(roomNameInputElement);

        roomNameInputElement.sendKeys(value);
    }

    public void fillRoomPrice(int value) {
        WebElement priceInputElement = webDriver.findElement(priceInputLocator);
        this.webHelper.scrollToElement(priceInputElement);

        priceInputElement.sendKeys(String.valueOf(value));
    }

    public void randomlySelectRoomType(){
        WebElement typeDropdownElement = webDriver.findElement(typeDropdownLocator);
        this.webHelper.scrollToElement(typeDropdownElement);

        typeDropdownElement.click();
        int elId = this.webHelper.randomInteger(1, typeDropdownOptionNumber);
        WebElement typeOptionElement =
                webDriver.findElement(
                        By.cssSelector(typeDropdownBaseCssSelector.replace("{0}", String.valueOf(elId)))
                );
        typeOptionElement.click();
    }

    public void randomlySelectCheckboxInputElements(){
        List<WebElement> checkboxElements = new ArrayList<>();
        for (By l : roomDetailsCheckboxLocators) {
            WebElement el = webDriver.findElement(l);
            checkboxElements.add(el);
        }

        this.webHelper.scrollToElement(checkboxElements.get(checkboxElements.size() - 1));

        for(WebElement e: checkboxElements)
        {
            boolean choose = this.webHelper.randomInteger(1, 1000) % 2 == 0;
            if(choose)
            {
                e.click();
            }
        }
    }

    public void randomlySelectRoomAccessibility(){
        WebElement isAccessibleDropdownElement = webDriver.findElement(isAccessibleDropdownLocator);
        this.webHelper.scrollToElement(isAccessibleDropdownElement);

        isAccessibleDropdownElement.click();
        int elId = this.webHelper.randomInteger(1, isAccessibleDropdownOptionNumber);
        WebElement isAccessibleOptionElement =
                webDriver.findElement(
                        By.cssSelector(isAccessibleOptionBaseCssSelector.replace("{0}", String.valueOf(elId)))
                );
        isAccessibleOptionElement.click();
    }

    public void submitRoomCreationForm(){
        WebElement createRoomElement = webDriver.findElement(createRoomLocator);
        this.webHelper.scrollToElement(createRoomElement);

        createRoomElement.click();
    }

    public WebElement getRoomElementById(int roomId){
        By roomLocator =  By.id(roomBaseId.replace("{0}", String.valueOf(roomId)));
        WebElement roomElement = webDriver.findElement(roomLocator);
        this.webHelper.scrollToElement(roomElement);

        return roomElement;
    }

    public WebElement getLogoutElement()
    {
        WebElement logoutElement = webDriver.findElement(logoutLocator);
        this.webHelper.scrollToElement(logoutElement);

        return logoutElement;
    }
}
