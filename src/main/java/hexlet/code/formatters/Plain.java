package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Plain extends Format {

    @Override
    public String format(List<Map<String, Object>> diffResults) {
        return stringDiffResults(diffResults);
    }

    private String diffRepresentation(String fieldName, String status, Object value1, Object value2) {
        return switch (status) {
            case "changed" -> "Property '" + fieldName + "' was updated. From "
                    + stringifyComplexValue(value1) + " to " + stringifyComplexValue(value2);
            case "added" -> "Property '" + fieldName + "' was added with value: " + stringifyComplexValue(value2);
            case "removed" -> "Property '" + fieldName + "' was removed";
            default -> "";
        };
    }

    private String stringifyComplexValue(Object value) {
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

    private String stringDiffResults(List<Map<String, Object>> diffResults) {
        List<String> results = new ArrayList<>();
        for (Map<String, Object> diff: diffResults) {
            String status = (String) diff.get("status");
            String fieldName = (String) diff.get("fieldName");
            Object value1 = diff.get("value1");
            Object value2 = diff.get("value2");
            String result =  diffRepresentation(fieldName, status, value1, value2);
            if (result.isEmpty()) {
                continue;
            }
            results.add(result);
        }
        return results.stream()
                .collect(Collectors.joining("\n"));
    }
}
