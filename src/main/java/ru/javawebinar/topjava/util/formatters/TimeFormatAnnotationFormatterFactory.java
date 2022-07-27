package ru.javawebinar.topjava.util.formatters;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;

public class TimeFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<TimeFormat> {

    @Override
    public Set<Class<?>> getFieldTypes() {
        return Collections.singleton(LocalTime.class);
    }

    @Override
    public Printer<?> getPrinter(TimeFormat annotation, Class<?> fieldType) {
        return new TimeFormatter();
    }

    @Override
    public Parser<?> getParser(TimeFormat annotation, Class<?> fieldType) {
        return new TimeFormatter();
    }
}
