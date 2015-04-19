package com.bikesandwheels.tools;

import java.util.Iterator;

public class StringUtils {
    public static String join(Iterable<? extends CharSequence> s, String delimiter) {
        Iterator<? extends CharSequence> iterator = s.iterator();
        if (!iterator.hasNext())
            return "";
        StringBuilder buffer = new StringBuilder(iterator.next());
        while (iterator.hasNext())
            buffer.append(delimiter).append(iterator.next());
        return buffer.toString();
    }
}
