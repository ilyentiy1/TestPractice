package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.core.BaseSeleniumPage;

public class AgilesPage extends BaseSeleniumPage {

    @FindBy(xpath = "//button[@data-test='Новая карточка']")
    private WebElement newIssueButton;

    @FindBy(xpath = "//textarea[@placeholder='Заголовок']")
    private WebElement summaryField;

    @FindBy(xpath = "//div[contains(@style, 'описание')]")
    private WebElement descriptionField;

    @FindBy(xpath = "//button[@data-test='submit-button']")
    private WebElement submitButton;

    @FindBy(xpath = "//h1[contains(@class, 'summary')]")
    private WebElement summary;

    @FindBy(xpath = "//p[@data-pos='0']")
    private  WebElement description;

    public AgilesPage() {
        driver.get("http://localhost:8080/agiles/192-2/current");
        PageFactory.initElements(driver, this);
    }

    public AgilesPage createNewIssue(String summary, String description) {
        newIssueButton.click();
        summaryField.sendKeys(summary);
        descriptionField.sendKeys(description);
        submitButton.click();

        return this;
    }


}