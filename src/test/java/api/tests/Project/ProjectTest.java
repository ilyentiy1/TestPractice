package api.tests.Project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@DisplayName("Тестирование CRUD над проектом")
public class ProjectTest extends ProjectBaseTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/api/valueSource/ProjectParameters.csv", numLinesToSkip = 1)
    public void createProjectTest(String name, String shortName,
                                  String description, int expectedStatusCode, boolean isPositive) {
        /*
        Если позитивный сценарий, то:
        1.Создание проекта
        2.Чтение проекта
        3.Удаление проекта
        Если негативный сценарий, то:
        1.Попытка создания сущности
        2.Вывод сообщения с ошибкой
         */
        if(isPositive) {
            projectClient
                    .createProject(name, shortName, description)
                    .passes()
                        .projectCreateCheck(expectedStatusCode)
                    .then()
                    .readProjectById()
                    .passes()
                        .projectReadCheck()
                    .then()
                    .deleteProject()
                    .passes()
                        .projectDeleteCheck();
        } else {
            projectClient
                    .createProject(name, shortName, description)
                    .passes()
                    .projectCreateCheck(expectedStatusCode);
        }

    }
}
