package api.tests.Project;

import api.BaseRestAssuredTest;
import api.client.ProjectClient;
import org.junit.jupiter.api.BeforeEach;

public abstract class ProjectBaseTest extends BaseRestAssuredTest {
    protected ProjectClient projectClient;

    @BeforeEach
    public void setUpProjectTest() {
        projectClient = new ProjectClient();
    }
}
