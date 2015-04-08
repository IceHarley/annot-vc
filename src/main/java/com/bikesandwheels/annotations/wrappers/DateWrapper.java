package com.bikesandwheels.annotations.wrappers;

import com.bikesandwheels.annotations.Date;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.text.*;

public class DateWrapper {
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    private java.util.Date date;

    public DateWrapper(Date dateAnnotation) {
        parseDate(dateAnnotation);
    }

    private void parseDate(Date dateAnnotation) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        formatter.setLenient(false);
        try {
            date = formatter.parse(String.format("%d.%d.%d", dateAnnotation.day(), dateAnnotation.month(), dateAnnotation.year()));
        }
        catch (ParseException e) {
            throw new IllegalArgumentException(String.format("Wrong date: %s", dateAnnotation));
        }
    }

    public java.util.Date getDate() {
        return date;
    }
}
