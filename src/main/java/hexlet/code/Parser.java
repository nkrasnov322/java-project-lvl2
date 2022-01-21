package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    private static ObjectMapper mapperForExtension(String fileExtension) throws Exception {
        if ("json".equals(fileExtension)) {
            return new ObjectMapper();
        } else if ("yaml".equals(fileExtension) || "yml".equals(fileExtension)) {
            return new ObjectMapper(new YAMLFactory());
        } else {
            throw new Exception();
        }
    }

    public static Map<String, Object> parse(String fileName) throws Exception {
        var type = new TypeReference<HashMap<String, Object>>() { };
        ObjectMapper mapper = mapperForExtension(fileExtension(fileName));
        return mapper.readValue(new File(fileName), type);
    }

    private static String fileExtension(String fileName) throws Exception {
        int indexDot = fileName.lastIndexOf(".");
        if (indexDot == -1) {
            throw new Exception();
        }
        return fileName.substring(indexDot + 1);
    }

}
