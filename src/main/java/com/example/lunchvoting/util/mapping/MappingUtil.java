package com.example.lunchvoting.util.mapping;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Some helper methods for entity to DTO mapping using Dozer
 */
public class MappingUtil {

    // Mapper instance for use outside of the spring context
    private static final Mapper MAPPER = new DozerBeanMapper();
    static {
        ((DozerBeanMapper)MAPPER).setMappingFiles(Arrays.asList("dozer-mapping.xml"));
    }

    public static <T, U> List<U> mapList(final Mapper mapper, final List<T> source, final Class<U> destType) {
        final List<U> dest = new ArrayList<>();
            for(T element : source) {
                dest.add(mapper.map(element, destType));
            }
        return dest;
    }

    public static Mapper getMapper() {
        return MAPPER;
    }
}
