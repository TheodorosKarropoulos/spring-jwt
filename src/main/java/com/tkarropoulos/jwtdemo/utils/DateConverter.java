package com.tkarropoulos.jwtdemo.utils;

import javax.persistence.AttributeConverter;
import java.util.Date;

public class DateConverter implements AttributeConverter<Long, Date> {

    @Override
    public Date convertToDatabaseColumn(Long aLong) {
        if (aLong == null)
            return null;

        return new Date(aLong);
    }

    @Override
    public Long convertToEntityAttribute(Date date) {
        if (date == null)
            return null;

        return date.getTime();
    }
}
