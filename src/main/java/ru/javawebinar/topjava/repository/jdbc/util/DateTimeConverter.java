package ru.javawebinar.topjava.repository.jdbc.util;

import java.time.LocalDateTime;

public interface DateTimeConverter<T> {
    T convert(LocalDateTime localDateTime);
}
