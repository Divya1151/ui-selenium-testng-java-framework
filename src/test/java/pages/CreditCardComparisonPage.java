package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a Credit Cards Comparison page class which helps to get Actual data on this page for selected cards.
 */

public class CreditCardComparisonPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id='card0']//div[@class='cardheader'][@style != 'height: 24px; display: none;']")
    List<WebElement> cardHeaders;

    public CreditCardComparisonPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * This function helps to get Actual data of credit cards which needs to be compared.
     */
    public List<Map<String, String>> getActualResult(int noOfCards) {
        List<Map<String, String>> actualResult = new ArrayList<>();
        for (int num = 0; num < noOfCards; num++) {
            List<WebElement> subHeaders = driver.findElements(By.xpath("//*[@id='card" + num + "']//div[@class='sub-header'][@style != 'display: none;']"));
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < cardHeaders.size(); i++) {
                map.put(cardHeaders.get(i).getText(), subHeaders.get(i).getText());
            }
            actualResult.add(map);
        }
        return actualResult;

    }

    /**
     * This functions helps to get names of the credit cards which are being compared
     */
    public List<String> getComparisonCardNames(int noOfCards) {
        List<String> cardNames = new ArrayList<>();
        for (int num = 0; num < noOfCards; num++) {
            WebElement cardName = driver.findElement(By.xpath("//*[@id='card" + num + "']/div[@class='cardcontainer-header']"));
            cardNames.add(cardName.getText());
        }
        return cardNames;

    }

}