package cn.ian.springframework.beans.factory.config;

import cn.ian.springframework.beans.BeansException;

public interface BeanPostProcessor {

    /**
     * 在 Bean 对象初始化方法执行之前，执行此方法
     */
    Object postProcessorBeforeInitialization(Object bean,String beanName) throws BeansException;

    /**
     * 在 Bean 对象初始化方法执行之后，执行此方法
     */
    Object postProcessorAfterInitialization(Object bean,String beanName) throws BeansException;

}
