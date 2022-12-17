package io.akenza.client.utils;

import org.immutables.value.Value;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS) // Make it class retention for incremental compilation
@Value.Style(
        jdkOnly = true, //do not use Guava
        visibility = Value.Style.ImplementationVisibility.PUBLIC // Generated class will be always public
)
public @interface AkenzaStyle {
}