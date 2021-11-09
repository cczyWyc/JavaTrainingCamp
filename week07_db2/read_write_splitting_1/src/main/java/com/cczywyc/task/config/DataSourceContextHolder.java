package com.cczywyc.task.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author wangyc
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DataSourceContextHolder {
    /** A context holder component that determines the context which is better to be thread bound by using ThreadLocal
     * so that the context is bound to the currently executing thread. */
    private static ThreadLocal<DataSourceEnum> threadLocal;

    /**
     * init
     */
    public DataSourceContextHolder() {
        threadLocal = new ThreadLocal<>();
    }

    /**
     * set datasource
     *
     * @param dataSourceEnum datasource enum
     */
    public void setBranchContext(DataSourceEnum dataSourceEnum) {
        threadLocal.set(dataSourceEnum);
    }

    /**
     * get datasource
     *
     * @return datasource
     */
    public DataSourceEnum getBranchContext() {
        return threadLocal.get();
    }


    /**
     * remove datasource
     *
     */
    public static void clearBranchContext() {
        threadLocal.remove();
    }
}
