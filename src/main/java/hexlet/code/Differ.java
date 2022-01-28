package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Differ {

    public static String generate(String file1, String file2) throws Exception {
        return generate(file1, file2, "stylish");
    }

    private static String fileExtension(String fileName) throws Exception {
        int indexDot = fileName.lastIndexOf(".");
        if (indexDot == -1) {
            throw new Exception("Файл без расширения");
        }
        return fileName.substring(indexDot + 1);
    }

    private static String textOfFile(String file1) throws IOException {
        return Files.readString(Path.of(file1));
    }

    public static String generate(String file1, String file2, String format) throws Exception {
        Map<String, Object> map1 = Parser.parse(textOfFile(file1), fileExtension(file1));
        Map<String, Object> map2 = Parser.parse(textOfFile(file2), fileExtension(file2));

        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        Map<String, Map<String, Object>> result = new TreeMap<>();
        for (String key : keys) {

            Object value1 = map1.get(key);
            Object value2 = map2.get(key);
            boolean isValue1 = map1.containsKey(key);
            boolean isValue2 = map2.containsKey(key);

            Map<String, Object> resultMap = new HashMap<>();
            if (!isValue1) {
                resultMap.put("added", value2);
            } else if (!isValue2) {
                resultMap.put("removed", value1);
            } else if (Objects.equals(value1, value2)) {
                resultMap = Map.of("equals", value2);
            } else {
                resultMap = Map.of("changed", listOfTwoElements(value1, value2));
            }
            result.put(key, resultMap);
        }
        return Formatter.format(result, format);
    }

    private static List<Object> listOfTwoElements(Object value1, Object value2) {
        // так как List.of не работает с null, сделал функцию для получения списка
        List<Object> result = new ArrayList<>();
        result.add(value1);
        result.add(value2);
        return result;
    }
}
