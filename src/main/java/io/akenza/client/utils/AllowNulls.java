package io.akenza.client.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Annotation to allow nulls in collections
 * https://immutables.github.io/immutable.html#nulls-in-collection
 */
@Target(ElementType.TYPE_USE)
public @interface AllowNulls {
}
