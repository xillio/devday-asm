package nl.xillio.devday.asm.tools;

import org.objectweb.asm.TypeReference;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class TypeReferences {
    public static String convertTypeReferenceSort(int sort) {
        return Arrays.stream(TypeReference.class.getDeclaredFields())
                .filter(f -> Modifier.isStatic(f.getModifiers()) && Modifier.isFinal(f.getModifiers()))
                .filter(f -> f.getType().isPrimitive() && f.getType().isAssignableFrom(int.class))
                .filter(f -> {
                    try {
                        return f.getInt(null) == sort;
                    } catch (IllegalAccessException e) {
                        return false;
                    }
                })
                .map(Field::getName)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(sort + " is unkown"));
    }
}
