package ru.javawebinar.topjava.repository.jdbc.util;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.Profiles;

import java.time.LocalDateTime;

@Component
@Profile(Profiles.POSTGRES_DB)
public class PostgresDateTimeConverter implements DateTimeConverter<LocalDateTime> {
    @Override
    public LocalDateTime convert(LocalDateTime localDateTime) {
        return localDateTime;
    }
}
