package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class Stylish extends Format {

    public String format(Map<String, Map<String, Object>> diffResults) {
        return wrapTheStringDiffs(stringDiffResults(diffResults));
    }

    private String wrapTheStringDiffs(String stringDiffResults) {
        return "{\n" + stringDiffResults + "}";
    }

    private String diffRepresentation(String key, String operation, Object value) {
        if ("changed".equals(operation)) {
            List listValue = (List) value;
            String removedDiff = diffRepresentation(key, "removed", listValue.get(0));
            String addedDiff = diffRepresentation(key, "added", listValue.get(1));
            return  removedDiff + addedDiff;
        }
        String result;
        if ("added".equals(operation)) {
            result = "+";
        } else if ("removed".equals(operation)) {
            result = "-";
        } else {
            result = " ";
        }
        return "  " + result + " " + key + ": " +  value + "\n";
    }

    private String stringDiffResults(Map<String, Map<String, Object>> diffResults) {
        String result = "";
        for (Map.Entry<String, Map<String, Object>> keyEntry: diffResults.entrySet()) {
            String key = keyEntry.getKey();
            for (Map.Entry<String, Object> diffEntry: keyEntry.getValue().entrySet()) {
                result += diffRepresentation(key, diffEntry.getKey(), diffEntry.getValue());
            }
        }
        return result;
    }
}
