package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;

public interface DateTimeConverter<T> {
    T convert(LocalDateTime localDateTime);
}
