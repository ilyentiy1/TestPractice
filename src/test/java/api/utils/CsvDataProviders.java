package api.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CsvDataProviders {

    public static Iterator<Object[]> getUserData() throws IOException {
        return readCsv("src/test/resources/api/valueSource/UserParameters.csv");
    }

    public static Iterator<Object[]> getIssueData() throws IOException {
        return readCsv("src/test/resources/api/valueSource/IssueParameters.csv");
    }

    public static Iterator<Object[]> getProjectData() throws IOException {
        return readCsv("src/test/resources/api/valueSource/ProjectParameters.csv");
    }

    public static Iterator<Object[]> getAuthData() throws IOException {
        return readCsv("src/test/resources/api/valueSource/AuthParameters.csv");
    }

    private static Iterator<Object[]> readCsv(String path) throws IOException {
        List<Object[]> data = new ArrayList<>();

        try (Reader in = new FileReader(path)) {
            CSVFormat format = CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .setIgnoreHeaderCase(true)
                    .setTrim(true).get();

            Iterable<CSVRecord> records = format.parse(in);
            for (CSVRecord record : records) {
                data.add(record.toList().toArray());
            }
        }
        return data.iterator();
    }
}
