package api.tests;

import api.client.IssueClient;
import api.specs.response.IssueSpecs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

@DisplayName("Тестирование CRUD над сущностью задачи")
public class IssueTest extends BaseTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/api/valueSource/IssueParameters.csv", numLinesToSkip = 1)
    public void issueTest(String summary, String description, int expectedStatusCode, boolean isPositive) {

        /*
        Если позитивный сценарий, то:
        1. Создание сущности задачи
        2. Чтение задачи
        3. Удаление задачи
        Если негативный сценарий, то:
        1. Попытка создания сущности
        2. Вывод ошибки
         */

        IssueSpecs issueSpecs = new IssueClient()
                .createIssue(summary, description)
                .passes();

        if(isPositive) {
            issueSpecs
                        .issueCreateCheck(expectedStatusCode)
                    .then()
                    .readIssueById()
                    .passes()
                        .issueReadCheck(summary)
                    .then()
                    .deleteIssue()
                    .passes()
                        .issueDeleteCheck();
        } else {
            issueSpecs
                    .issueCreateCheck(expectedStatusCode);
        }

        List<String> issuesSummaries = new IssueClient()
                .readIssues()
                .then()
                .extract().body().jsonPath().getList("issues.summary");

        Assertions.assertTrue(issuesSummaries.stream().noneMatch(x -> x.contains(summary)));

    }
}
