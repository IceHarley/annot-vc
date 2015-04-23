package com.bikesandwheels.tools;

import java.util.Iterator;

public class StringUtils {
    private static final String DEFAULT_DELIMITER = ", ";

    public static String join(Iterable<? extends CharSequence> str) {
        return join(str, DEFAULT_DELIMITER);
    }

    public static String join(Iterable<? extends CharSequence> str, String delimiter) {
        Iterator<? extends CharSequence> iterator = str.iterator();
        if (!iterator.hasNext())
            return "";
        StringBuilder buffer = new StringBuilder(iterator.next());
        while (iterator.hasNext())
            buffer.append(delimiter).append(iterator.next());
        return buffer.toString();
    }
}
