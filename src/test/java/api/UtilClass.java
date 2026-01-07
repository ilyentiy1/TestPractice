package api;

public class UtilClass {

    public static void printUserTestInfo(String login, String name,
                                         int expectedStatusCode, boolean isPositive) {
        System.out.println("Login: " + login +
                "\nName: " + name +
                "\nExpected Status code: " + expectedStatusCode +
                "\nIs positive?: " + isPositive);
    }

    public static void printIssueTestInfo(String summary, String description,
                                          int expectedStatusCode, boolean isPositive) {
        System.out.println("Summary: " + summary +
                "\nDescription: " + description +
                "\nExpected Status code: " + expectedStatusCode +
                "\nIs positive?: " + isPositive);
    }

    public static void printProjectTestInfo(String name, String shortName,
                                            String description, int expectedStatusCode, boolean isPositive) {
        System.out.println("Name: " + name +
                "\nShort name: " + shortName +
                "\nDescription: " + description +
                "\nExpected Status code: " + expectedStatusCode +
                "\nIs positive?: " + isPositive);
    }
}
