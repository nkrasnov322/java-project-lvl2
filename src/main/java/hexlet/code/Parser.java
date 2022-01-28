package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    private static ObjectMapper mapperForFileFormat(String fileExtension) throws Exception {
        return switch (fileExtension) {
            case "json" -> new ObjectMapper();
            case "yaml", "yml" -> new ObjectMapper(new YAMLFactory());
            default -> throw new Exception("Неизвестный формат файлов");
        };
    }

    public static Map<String, Object> parse(String text, String format) throws Exception {
        var type = new TypeReference<HashMap<String, Object>>() { };
        ObjectMapper mapper = mapperForFileFormat(format);
        return mapper.readValue(text, type);
    }

}
