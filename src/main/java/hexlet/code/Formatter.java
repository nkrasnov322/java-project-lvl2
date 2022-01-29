package hexlet.code;

import hexlet.code.formatters.Format;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String format(List<Map<String, Object>> diffResults, String format) throws Exception {
        Format formatter = getFormatter(format);
        return formatter.format(diffResults);
    }

    private static Format getFormatter(String format) {
        if (format == null) {
            format = "";
        }
        return switch (format) {
            case "", "stylish" -> new Stylish();
            case "plain" -> new Plain();
            case "json" -> new Json();
            default ->
                throw new IllegalArgumentException("Неизвестный формат");
        };
    }
}
