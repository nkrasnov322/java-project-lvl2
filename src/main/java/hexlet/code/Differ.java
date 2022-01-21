package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;



public class Differ {
    public static String generate(String file1, String file2) throws Exception {
        return generate(file1, file2, "stylish");
    }

    public static String generate(String file1, String file2, String format) throws Exception {
        Map<String, Object> map1 = Parser.parse(file1);
        Map<String, Object> map2 = Parser.parse(file2);

        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        Map<String, Map<String, Object>> result = new TreeMap<>();
        for (String key : keys) {

            Object value1 = map1.get(key);
            Object value2 = map2.get(key);
            boolean isValue1 = map1.containsKey(key);
            boolean isValue2 = map2.containsKey(key);
            boolean isValuesEqualsNull = (value1 == null && value2 == null);

            Map<String, Object> resultMap = new HashMap<>();
            if (!isValue1 && isValue2) {
                resultMap.put("added", value2);
            } else if (isValue1 && !isValue2) {
                resultMap.put("removed", value1);
            } else if (isValuesEqualsNull || (value1 != null && value1.equals(value2))) {
                resultMap = Map.of("equals", value2);
            } else if (isValue1 && isValue2) {
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
