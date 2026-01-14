package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.core.BaseSeleniumTest;
import ui.utils.CsvDataProviders;


public class YouTrackUITest extends BaseSeleniumTest {

    @Test(dataProvider = "issueData", dataProviderClass = CsvDataProviders.class)
    public void createIssueTest(String summary, String description) {
        AgilesPage agilesPage = new AgilesPage().createNewIssue(summary, description);
        Assert.assertEquals(summary, );
        Assert.assertEquals(description, );
    }


}
