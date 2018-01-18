package com.example.lunchvoting.web.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Map;

import static com.example.lunchvoting.web.json.HibernateAwareObjectMapper.getMapper;

/**
 * Some methods for read/write json
 */
public class JsonUtil {

    public static <T> String writeValue(T obj) {
        try {
            return getMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n" + obj, e);
        }
    }

    public static <T> String writeWithExtraProps(T obj, String extraName, Object extraValue) {
        return writeWithExtraProps(obj, Collections.singletonMap(extraName, extraValue));
    }

    public static <T> String writeWithExtraProps(T obj, Map<String, Object> extraProps) {
        Map<String, Object> map = getMapper().convertValue(obj, new TypeReference<Map<String, Object>>(){});
        map.putAll(extraProps);
        try {
            return getMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + obj + "'", e);
        }
    }
}
