package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class Stylish extends Format {

    public String format(List<Map<String, Object>> diffResults) {
        return wrapTheStringDiffs(stringDiffResults(diffResults));
    }

    private String wrapTheStringDiffs(String stringDiffResults) {
        return "{\n" + stringDiffResults + "}";
    }

    private String diffRepresentation(String fieldName, String status, Object value1, Object value2) {
        if ("changed".equals(status)) {
            String removedDiff = diffRepresentation(fieldName, "removed", value1, null);
            String addedDiff = diffRepresentation(fieldName, "added", null, value2);
            return  removedDiff + addedDiff;
        }
        Object value;
        String result;
        switch (status) {
            case "added":
                result = "+";
                value = value2;
                break;
            case "removed":
                result = "-";
                value = value1;
                break;
            default:
                result = " ";
                value = value2;
        }
        return "  " + result + " " + fieldName + ": " +  value + "\n";
    }

    private String stringDiffResults(List<Map<String, Object>> diffResults) {
        String result = "";
        for (Map<String, Object> diff: diffResults) {
            String status = (String) diff.get("status");
            String fieldName = (String) diff.get("fieldName");
            Object value1 = diff.get("value1");
            Object value2 = diff.get("value2");
            result += diffRepresentation(fieldName, status, value1, value2);
        }
        return result;
    }
}
