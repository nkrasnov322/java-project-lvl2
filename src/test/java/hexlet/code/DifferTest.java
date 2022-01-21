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
        File stylishResultFile = getFileFromResource("stylish_result");
        Path stylishResultPath = Paths.get(stylishResultFile.getAbsolutePath());
        String stylishResultValue = Files.readString(stylishResultPath);
        String generate = Differ.generate(file1.getAbsolutePath(), file2.getAbsolutePath());
        Assertions.assertEquals(stylishResultValue, generate);

        File plainResultFile = getFileFromResource("plain_result");
        Path plainResultPath = Paths.get(plainResultFile.getAbsolutePath());
        String plainResultValue = Files.readString(plainResultPath);
        generate = Differ.generate(file1.getAbsolutePath(), file2.getAbsolutePath(), "plain");
        Assertions.assertEquals(plainResultValue, generate);

        File jsonResultFile = getFileFromResource("json_result");
        Path jsonResultPath = Paths.get(jsonResultFile.getAbsolutePath());
        String jsonResultValue = Files.readString(jsonResultPath);
        generate = Differ.generate(file1.getAbsolutePath(), file2.getAbsolutePath(), "json");
        Assertions.assertEquals(jsonResultValue, generate);

        file1 = getFileFromResource("file1.yaml");
        file2 = getFileFromResource("file2.yaml");
        generate = Differ.generate(file1.getAbsolutePath(), file2.getAbsolutePath());
        Assertions.assertEquals(stylishResultValue, generate);

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
