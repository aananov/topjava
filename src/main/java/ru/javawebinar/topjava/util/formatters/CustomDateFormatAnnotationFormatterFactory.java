package ru.javawebinar.topjava.util.formatters;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

public class CustomDateFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<CustomDateFormat> {
    @Override
    public Set<Class<?>> getFieldTypes() {
        return Collections.singleton(LocalDate.class);
    }

    @Override
    public Printer<?> getPrinter(CustomDateFormat annotation, Class<?> fieldType) {
        return new CustomDateFormatter();
    }

    @Override
    public Parser<?> getParser(CustomDateFormat annotation, Class<?> fieldType) {
        return new CustomDateFormatter();
    }
}
