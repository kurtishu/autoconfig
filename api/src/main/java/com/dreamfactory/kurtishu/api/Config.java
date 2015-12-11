package com.dreamfactory.kurtishu.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * kurtishu on 12/10/15
 * Eevery one should have a dream, what if one day it comes true!
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Config {
    String value();
}
