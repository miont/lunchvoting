package com.example.lunchvoting.util;

import com.example.lunchvoting.domain.AbstractBaseEntity;
import com.example.lunchvoting.util.exception.NotFoundException;

/**
 * Helper methods for validation
 */
public class ValidationUtil {

    private ValidationUtil() {
    }

    public static void checkNotFoundWithId(boolean found, long id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T extends HasId> T checkNotFoundWithId(T object, long id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void assureIdConsistent(HasId bean, long id) {
//      http://stackoverflow.com/a/32728226/548473
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.getId() != id) {
            throw new IllegalArgumentException(bean + " must be with id=" + id);
        }
    }

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalArgumentException(bean + " must be new (id=null)");
        }
    }

    public static String getArgumentIsNullMsg(String argName) {
        return argName + " argument must not be null";
    }
}
