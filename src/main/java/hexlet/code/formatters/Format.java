package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public abstract class Format {

    public abstract String format(List<Map<String, Object>> diffResults) throws Exception;

}
