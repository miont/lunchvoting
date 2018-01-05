package com.example.lunchvoting.web.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Custom Jackson ObjectMapper for latest Hibernate
 */
public class HibernateAwareObjectMapper extends ObjectMapper {

    private static final ObjectMapper MAPPER = new HibernateAwareObjectMapper();

    private HibernateAwareObjectMapper() {
        registerModule(new Hibernate5Module());

        // Serialize dates in UTC
        registerModule(new JavaTimeModule());
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        // Include fields not getters
        setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        // Not including null fields. Actually this doesnt work... why?
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static ObjectMapper getMapper() {
        return MAPPER;
    }

}
