package com.cczyWyc.rpcfx_core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * rpc provider service
 *
 * @author wangyc
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProviderService {
    /**
     * service
     *
     * @return api service
     */
    String service();

    /**
     * group
     *
     * @return group
     */
    String group() default "default";

    /**
     * version
     *
     * @return version
     */
    String version() default "default";

    /**
     * tags. simple route
     *
     * @return tags
     */
    String tags() default "";

    /**
     * lb
     *
     * @return number use lb
     */
    int weight() default 1;
}

