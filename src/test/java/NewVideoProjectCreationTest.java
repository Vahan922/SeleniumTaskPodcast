import lombok.extern.slf4j.Slf4j;
import org.example.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertEquals;

@Slf4j
public class NewVideoProjectCreationTest {
    private static final Logger log = LoggerFactory.getLogger(NewVideoProjectCreationTest.class);

    WebDriver driver = new ChromeDriver();
    WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
    BaseClass baseClass = new BaseClass(driver, driverWait);
    List<WebElement> listOfCards;

    @BeforeTest
    public void login() {
        driver.get(Credentials.TEST_USER.getBaseUrl());
        driver.manage().window().maximize();
        log.info("title is   " + driver.getTitle() + " : Url is " + driver.getCurrentUrl());

        assertEquals(driver.getTitle(), baseClass.messageForCheckingHomePageTitle, baseClass.errorMessageForCheckingHomePageTitle);
    }

    @Test
    public void creatingVideoProject() throws InterruptedException {
        log.info("Attention!!! Please Check if you have more than 19 existing  project test will not be Passed:");

        baseClass.clickByXpathWithoutWait(baseClass.logInButtonFromHomePage);
//        driver.findElement(By.xpath("//div[text()='Log in']")).click();

        baseClass.waitUntilVisibilityOfAllElementsLocatedBy(baseClass.inputUserNameXpath);
//        driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@type='text']")));

        baseClass.sendKeyByXpath(baseClass.inputUserNameXpath, Credentials.TEST_USER.getUsername());
//        driver.findElement(By.xpath("//input[@id='input' and @type='text']")).sendKeys(Credentials.TEST_USER.getUsername());

        baseClass.sendKeyByXpath(baseClass.inputPasswordXpath, Credentials.TEST_USER.getPassword());
//        driver.findElement(By.xpath("//input[@id='input' and @type='password']")).sendKeys(Credentials.TEST_USER.getPassword());

        baseClass.clickByXpathWithoutWait(baseClass.logInButtonFromLoginWindow);
//        driver.findElement(By.xpath("//button[text()='Login']")).click();
        log.info(baseClass.messageForNavigatingHomePage);
        baseClass.waitUntilVisibilityOfAllElementsLocatedBy(baseClass.videoEditorButtonXpath);
//        driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='relative']//button[contains(@class, 'VIDEO_EDITOR')]")));
        Thread.sleep(2500L);
        listOfCards = driver.findElements(By.xpath(baseClass.existingCardsXpath));
        int countOfProjectBeforeTest = listOfCards.size();
        log.info("Count Of Projects Before Test: " + countOfProjectBeforeTest);
        //Click Video Editor button
        baseClass.clickByXpathWithoutWait(baseClass.videoEditorButtonXpath);
//        driver.findElement(By.xpath(videoEditorButtonXpath)).click();
        log.info(baseClass.messageForNavigatingVideoProject);
        //Wait until saved
        baseClass.waitUntilInVisible(baseClass.invisibilityOfSavedStatus);
//        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='ml-8 typo-small text-surface-neutral-500' and text()='Saved!']")));
        //click back button
        Thread.sleep(3000L);
        baseClass.clickByXpathWithoutWait(baseClass.backButtonXpath);
//        driver.findElement(By.xpath("//button[@class='icon-btn w-40 h-40 rounded-full hover:bg-state-hover hover:bg-opacity-100' and @type='button']")).click();
        //Wait until saved
        baseClass.waitUntilInVisible(baseClass.invisibilityOfSavedStatus);
//        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='ml-8 typo-small text-surface-neutral-500' and text()='Saved!']")));
        //Wait visibility of existing projects
        baseClass.waitUntilVisibilityOfAllElementsLocatedBy(baseClass.videoEditorButtonXpath);
//        driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='relative']//button[contains(@class, 'VIDEO_EDITOR')]")));
        Thread.sleep(2500L);

        listOfCards = driver.findElements(By.xpath(baseClass.existingCardsXpath));
        int countOfProjectAfterTest = listOfCards.size();
        log.info("Count Of Projects After Test: " + countOfProjectAfterTest);
        assertEquals(countOfProjectBeforeTest, countOfProjectAfterTest - 1, baseClass.errorMessageForComparingCards);
        assertEquals(listOfCards.getFirst().getDomAttribute(baseClass.domAttributeName), baseClass.icon, baseClass.errorMessageForComparingIcons);
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
        log.info("Stop");
    }
}

