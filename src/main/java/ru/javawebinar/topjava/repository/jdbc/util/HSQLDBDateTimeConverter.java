package ru.javawebinar.topjava.repository.jdbc.util;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.Profiles;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@Profile(Profiles.HSQL_DB)
public class HSQLDBDateTimeConverter implements DateTimeConverter<Timestamp> {
    @Override
    public Timestamp convert(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }
}
