package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = about:blank
public class AgilesPage {

    @FindBy(xpath = "//button[@data-test='Новая карточка']")
    private WebElement newIssueButton;

    @FindBy(xpath = "//textarea[@placeholder='Заголовок']")
    private WebElement summaryField;

    @FindBy(xpath = "//button[@data-test='submit-button']")
    private WebElement submitButton;

    public AgilesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

//    public AgilesPage createNewIssue() {
//
//
//        return new ;
//    }


}