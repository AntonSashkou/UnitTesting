package com.sashkou.junit;

import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToOctalStringArgumentConverter extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType) {
        assertEquals(Integer.class, source.getClass(), "Can only convert from Integers.");
        assertEquals(String.class, targetType, "Can only convert to String");
        return Integer.toOctalString((Integer) source);
    }
}
