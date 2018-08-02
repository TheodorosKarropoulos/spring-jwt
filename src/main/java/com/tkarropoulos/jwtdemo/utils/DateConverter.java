package com.tkarropoulos.jwtdemo.utils;

import javax.persistence.AttributeConverter;
import java.util.Date;

public class DateConverter implements AttributeConverter<Long, Date> {

    @Override
    public Date convertToDatabaseColumn(Long longDate) {
        return longDate != null ? new Date(longDate) : null;
    }

    @Override
    public Long convertToEntityAttribute(Date dbDate) {
        return dbDate != null ? dbDate.getTime() : null;
    }
}
