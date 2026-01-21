package api.tests.Issue;

import api.BaseRestAssuredTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@DisplayName("Тестирование CRUD над сущностью задачи")
public class IssueTest extends IssueBaseTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/api/valueSource/IssueParameters.csv", numLinesToSkip = 1)
    public void issueTest(String summary, String description, int expectedStatusCode, boolean isPositive) {
        /*
        Если позитивный сценарий, то:
        1.Создание сущности задачи
        2.Чтение задачи
        3.Удаление задачи
        Если негативный сценарий, то:
        1.Попытка создания сущности
        2.Вывод ошибки
         */
        if(isPositive) {
            issueClient
                    .createIssue(summary, description)
                    .passes()
                        .issueCreateCheck(expectedStatusCode)
                    .then()
                    .readIssueById()
                    .passes()
                        .issueReadCheck()
                    .then()
                    .deleteIssue()
                    .passes()
                        .issueDeleteCheck();
        } else {
            issueClient
                    .createIssue(summary, description)
                    .passes()
                    .issueCreateCheck(expectedStatusCode);
        }

    }
}
