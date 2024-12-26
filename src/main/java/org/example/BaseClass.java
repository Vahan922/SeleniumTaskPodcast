package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {

    public String messageForCheckingHomePageTitle = "Podcastle: All-in-One Video and Podcast Software";
    public String errorMessageForCheckingHomePageTitle = "Error: Incorrect Title or Homepage Issue";
    public String messageForNavigatingHomePage = "User is Navigate to the Project Overview page. ";
    public String messageForNavigatingVideoProject = "User is Navigate to the Project Overview page. ";
    public String errorMessageForComparingCards = "something Incorrect number of projects need to be added by 1 ";
    public String errorMessageForComparingIcons = "the icon of added card must be video_icon";
    public String domAttributeName = "alt";
    public String icon = "video_icon";
    public String logInButtonFromHomePage = "//div[text()='Log in']";
    public String inputUserNameXpath = "//input[@id='input' and @type='text']";
    public String inputPasswordXpath = "//input[@id='input' and @type='password']";
    public String logInButtonFromLoginWindow = "//button[text()='Login']";
    public String videoEditorButtonXpath = "//div[@class='relative']//button[contains(@class, 'VIDEO_EDITOR')]";
    public String existingCardsXpath = "//div[@role=\"presentation\"]//div[contains(@class, 'card')]//img[@alt]";
    public String invisibilityOfSavedStatus = "//span[@class='ml-8 typo-small text-surface-neutral-500' and text()='Saved!']";
    public String backButtonXpath = "//button[@class='icon-btn w-40 h-40 rounded-full hover:bg-state-hover hover:bg-opacity-100' and @type='button']";
    WebDriver driver;
    WebDriverWait wait;


    public BaseClass(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void waitUntilVisible(String xpath) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
    }

    public void waitUntilVisibilityOfAllElementsLocatedBy(String xpath) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
    }

    public void waitUntilInVisible(String xpath) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
    }

    public void waitUntilClickable(String xpath) {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpath))));
    }

    public void clickByXpathWithoutWait(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    public void sendKeyByXpath(String xpath, String keyMessage) {
        waitUntilVisible(xpath);
        driver.findElement(By.xpath(xpath)).sendKeys(keyMessage);
    }
}
