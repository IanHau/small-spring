package cn.ian.springframework.beans.factory.support;

import cn.ian.springframework.beans.BeansException;
import cn.ian.springframework.core.io.Resource;
import cn.ian.springframework.core.io.ResourceLoader;

public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();
    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;
    void loadBeanDefinitions(Resource... resource) throws BeansException;
    void loadBeanDefinitions(String location) throws BeansException;

}
