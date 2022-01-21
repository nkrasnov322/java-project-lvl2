package hexlet.code;

import hexlet.code.formatters.Format;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import java.util.Map;

public class Formatter {

    public static String format(Map<String, Map<String, Object>> diffResults, String format) throws Exception {
        Format formatter = getFormatter(format);
        return formatter.format(diffResults);
    }

    public static String format(Map<String, Map<String, Object>> diffResults) throws Exception {
        return format(diffResults, "stylish");
    }

    private static Format getFormatter(String format) {
        if (format == null || "".equals(format) || "stylish".equals(format)) {
            return new Stylish();
        } else if ("plain".equals(format)) {
            return new Plain();
        } else if ("json".equals(format)) {
            return new Json();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
