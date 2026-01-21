package api.tests.Issue;

import api.BaseRestAssuredTest;
import api.client.IssueClient;
import org.junit.jupiter.api.BeforeEach;

public abstract class IssueBaseTest extends BaseRestAssuredTest {

    protected IssueClient issueClient;

    @BeforeEach
    public void setUpIssueTest() {
        issueClient = new IssueClient();
    }
}
