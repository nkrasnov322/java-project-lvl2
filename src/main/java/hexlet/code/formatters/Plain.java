package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Plain extends Format {

    @Override
    public String format(Map<String, Map<String, Object>> diffResults) {
        return stringDiffResults(diffResults);
    }

    private String diffRepresentation(String key, String operation, Object value) {
        if ("changed".equals(operation)) {

            Object value1 = ((List) value).get(0);
            Object value2 = ((List) value).get(1);

            return "Property '" + key + "' was updated. From "
                                + checkComplexValue(value1) + " to " + checkComplexValue(value2);
        }
        if ("added".equals(operation)) {
            return "Property '" + key + "' was added with value: " + checkComplexValue(value);
        } else if ("removed".equals(operation)) {
            return "Property '" + key + "' was removed";
        } else {
            return "";
        }
    }

    private String checkComplexValue(Object value) {
        if (value == null) {
            return "null";
        }
        var classValue = value.getClass();
        if ((classValue.isArray())
                || classValue.isAssignableFrom(LinkedHashMap.class)
                || classValue.isAssignableFrom(ArrayList.class)) {
            return "[complex value]";
        }
        if (classValue.isAssignableFrom(String.class)) {
            return "'" + value + "'";
        }
        return String.valueOf(value);
    }

    private String stringDiffResults(Map<String, Map<String, Object>> diffResults) {
        List<String> results = new ArrayList<>();
        for (Map.Entry<String, Map<String, Object>> keyEntry: diffResults.entrySet()) {
            String key = keyEntry.getKey();
            for (Map.Entry<String, Object> diffEntry: keyEntry.getValue().entrySet()) {
                String result =  diffRepresentation(key, diffEntry.getKey(), diffEntry.getValue());
                if (result.isEmpty()) {
                    continue;
                }
                results.add(result);
            }
        }

        return results.stream()
                .collect(Collectors.joining("\n"));
    }
}
