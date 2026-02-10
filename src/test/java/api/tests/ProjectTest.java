package api.tests;

import api.client.ProjectClient;
import api.specs.response.ProjectSpecs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

@DisplayName("Тестирование CRUD над проектом")
public class ProjectTest extends BaseTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/api/valueSource/ProjectParameters.csv", numLinesToSkip = 1)
    public void createProjectTest(String name, String shortName,
                                  String description, int expectedStatusCode, boolean isPositive) {

        /*
        Если позитивный сценарий, то:
        1. Создание проекта
        2. Чтение проекта
        3. Удаление проекта
        Если негативный сценарий, то:
        1. Попытка создания сущности
        2. Вывод сообщения с ошибкой
         */

        ProjectSpecs projectSpecs = new ProjectClient()
                .createProject(name, shortName, description)
                .passes();

        if(isPositive) {
            projectSpecs
                    .projectCreateCheck(expectedStatusCode)
                    .then()
                    .readProjectById()
                    .passes()
                    .projectReadCheck(name)
                    .then()
                    .deleteProject()
                    .passes()
                    .projectDeleteCheck();
        } else {
            projectSpecs
                    .projectCreateCheck(expectedStatusCode);
        }

        List<String> projectsNames = new ProjectClient()
                .readProjects()
                .then()
                .extract().body().jsonPath().getList("projects.name");

        Assertions.assertTrue(projectsNames.stream().noneMatch(x -> x.contains(name)));

    }
}
