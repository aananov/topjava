package ru.javawebinar.topjava.util.formatters;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;

public class CustomTimeFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<CustomTimeFormat> {

    @Override
    public Set<Class<?>> getFieldTypes() {
        return Collections.singleton(LocalTime.class);
    }

    @Override
    public Printer<?> getPrinter(CustomTimeFormat annotation, Class<?> fieldType) {
        return new CustomTimeFormatter();
    }

    @Override
    public Parser<?> getParser(CustomTimeFormat annotation, Class<?> fieldType) {
        return new CustomTimeFormatter();
    }
}
