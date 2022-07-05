package ru.javawebinar.topjava.util;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@Profile("jdbc")
public class HSQLDBDateTimeConverter implements DateTimeConverter<Timestamp> {
    @Override
    public Timestamp convert(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }
}
