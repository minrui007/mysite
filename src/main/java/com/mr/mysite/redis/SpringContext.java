package com.mr.mysite.redis;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 以静态变量保存Spring ApplicationContext，可以在任何代码任何地方任何时候中取出ApplicationContext
 */
@Component
public class SpringContext implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的context注入函数，将其存入静态变量
     * @param applicationContext
     * @throws BeansException
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext = applicationContext;  //NOSONAR
    }

    /**
     * 取得存储在静态变量中的ApplicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return  applicationContext;
    }

    /**
     * 从静态变量ApplicationContext中取得Bean，自动转型为所赋值对象的类型
     * @param name
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static<T> T getBean(String name) {
        checkApplicationContext();;
        return (T)applicationContext.getBean(name);
    }

    /**
     * 从静态变量ApplicationContext中取得bean，自动转型为所赋值对象的类型
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static<T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        return (T)applicationContext.getBeansOfType(clazz);
    }

    /**
     * 清除applicationContext静态变量
     */
    public static void cleaApplicationContext() {
        applicationContext = null;
    }

    private static void checkApplicationContext() {
        if(null == applicationContext) {
            throw new IllegalStateException("applicationContext未注入，请在applicationContext.xml中定义SpringContext");
        }
    }



}
