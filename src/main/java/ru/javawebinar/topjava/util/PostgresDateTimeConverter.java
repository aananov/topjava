package ru.javawebinar.topjava.util;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Profile("postgres")
public class PostgresDateTimeConverter implements DateTimeConverter<LocalDateTime> {
    @Override
    public LocalDateTime convert(LocalDateTime localDateTime) {
        return localDateTime;
    }
}
