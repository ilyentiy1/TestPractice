package ui.core;

import lombok.Setter;
import org.openqa.selenium.WebDriver;

abstract public class BaseSeleniumPage {
    @Setter
    protected static WebDriver driver;
}
