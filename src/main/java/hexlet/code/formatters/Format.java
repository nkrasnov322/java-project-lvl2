package hexlet.code.formatters;

import java.util.Map;

public abstract class Format {

    public abstract String format(Map<String, Map<String, Object>> diffResults) throws Exception;

}
