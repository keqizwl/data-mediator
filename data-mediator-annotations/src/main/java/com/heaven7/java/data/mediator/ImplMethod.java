package com.heaven7.java.data.mediator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * define the impl method of module's 'self-method',
 * Created by heaven7 on 2017/10/24.
 * @since 1.1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface ImplMethod {
    /**
     * indicate the method name of impl class. default empty means use the same method name of impl Class.
     * @return the method name.
     * @see ImplClass
     */
    String value() default "";

    /**
     * force indicate this method from target class.
     * @return the method which comes from. default is illegal argument. you should use a valid class .
     * if you want force use the method from value of this 'from'.
     */
    Class<?> from() default void.class;
}
