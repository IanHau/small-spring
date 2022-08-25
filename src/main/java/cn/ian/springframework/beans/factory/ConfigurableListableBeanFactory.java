package cn.ian.springframework.beans.factory;

import cn.ian.springframework.beans.factory.config.AutowireCapableBeanFactory;
import cn.ian.springframework.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends AutowireCapableBeanFactory, ConfigurableBeanFactory,ListableBeanFactory {
    void preInstantiateSingletons();
}
