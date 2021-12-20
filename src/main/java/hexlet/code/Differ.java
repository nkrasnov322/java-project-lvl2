package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(String file1, String file2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        var type = new TypeReference<Map<String, String>>() { };
        Map<String, String> map1;
        Map<String, String> map2;
        map1 = objectMapper.readValue(new File(file1), type);
        map2 = objectMapper.readValue(new File(file2), type);

        Set<String> keys = new TreeSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());

        List<String> result = new ArrayList<>();
        for (String key : keys) {

            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            if (value1 == null && value2 != null) {
                result.add("  + " + key + ": " +  value2 + "\n");
            } else if (value1 != null && value2 == null) {
                result.add("  - " + key + ": " +  value1 + "\n");
            } else if (value1.equals(value2)) {
                result.add("    " + key + ": " +  value2 + "\n");
            } else {
                result.add("  - " + key + ": " +  value1 + "\n");
                result.add("  + " + key + ": " +  value2 + "\n");
            }
        }
        return "{\n" + result.stream().collect(Collectors.joining()) + "}";
    }
}
