package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {
    @Test
    void generate() throws Exception {
        File file1 = getFileFromResource("file1.json");
        File file2 = getFileFromResource("file2.json");
        File resultFile = getFileFromResource("json_result");
        Path resultPath = Paths.get(resultFile.getAbsolutePath());
        String resultValue = Files.readString(resultPath);
        String generate = Differ.generate(file1.getAbsolutePath(), file2.getAbsolutePath());
        Assertions.assertEquals(resultValue, generate);
    }
    private File getFileFromResource(String fileName) throws Exception {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }

    }
}
