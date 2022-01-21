package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public final class Json extends Format {
    @Override
    public String format(Map<String, Map<String, Object>> diffResults) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(diffResults);
    }
}
