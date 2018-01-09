package com.example.lunchvoting.util.mapping;

import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Some helper methods for entity to DTO mapping using Dozer
 */
public class MappingUtil {

    public static <T, U> List<U> mapList(final Mapper mapper, final List<T> source, final Class<U> destType) {
        final List<U> dest = new ArrayList<>();
            for(T element : source) {
                dest.add(mapper.map(element, destType));
            }
        return dest;
    }
}
