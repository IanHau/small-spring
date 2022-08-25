package cn.ian.springframework.context;

import cn.ian.springframework.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器
     */
    void refresh() throws BeansException;
}