package com.example.lunchvoting.web.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

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
}
