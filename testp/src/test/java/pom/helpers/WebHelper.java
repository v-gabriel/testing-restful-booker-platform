package pom.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class WebHelper {
    private final WebDriver webDriver;
    private final WebDriverWait wait;
    public WebHelper(WebDriver driver) {
        this.webDriver = driver;
        this.wait = new WebDriverWait(webDriver, Constants.timeOutInSeconds);
    }

    /**
     *  Scroll until given element is visible.
     */
    public void scrollToElement(WebElement element)
    {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    /**
     *  Wait for given elements to load
     */
    public void waitFor(ArrayList<By> locators){
        for (By l : locators) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(l));
        }
    }

    public int randomInteger(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
