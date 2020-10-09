package ca.jrvs.apps.trading.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class JsonUtil {

    /**
     * Convert a Java object into a JSON String.
     * @param object
     * @param prettyJson
     * @param includeNullValues
     * @return result JSON String
     * @throws JsonProcessingException
     */
    public static String toJsonFromObject(Object object, boolean prettyJson, boolean includeNullValues)
            throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        if (!includeNullValues) {
            m.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        if (prettyJson) {
            m.enable(SerializationFeature.INDENT_OUTPUT);
        }
        return m.writeValueAsString(object);
    }

    /**
     * Parse JSON String into an instance of class T
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return result Java object
     * @throws IOException
     * @throws IOException
     */
    public static <T> T toObjectFromJson(String json, Class<T> clazz) throws IOException, IOException {
        ObjectMapper m = new ObjectMapper();
        // m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return (T) m.readValue(json, clazz);
    }
}
