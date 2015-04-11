package com.bikesandwheels.annotations.wrappers;

import com.bikesandwheels.annotations.Date;

import java.text.*;

class DateWrapper {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateWrapper that = (DateWrapper) o;

        return date.equals(that.date);

    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
