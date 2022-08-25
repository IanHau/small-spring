package cn.ian.springframework.context.support;

import cn.ian.springframework.beans.BeansException;
import cn.ian.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.ian.springframework.beans.factory.config.BeanFactoryPostProcessor;
import cn.ian.springframework.beans.factory.config.BeanPostProcessor;
import cn.ian.springframework.context.ConfigurableApplicationContext;
import cn.ian.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException {
        //1. 创建 BeanFactory ，并加载 BeanDefinition
        refreshBeanFactory();

        //2. 获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //3. 在 Bean 实例化之前，执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        //4. BeanPostProcessor 需要提前于其他对象实例化之前执行注册操作
        registerBeanPostProcessor(beanFactory);

        //5. 提前实例化单例 Bean 对象
        beanFactory.preInstantiateSingletons();
    }

    private void registerBeanPostProcessor(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessorBeanFactory(beanFactory);
        }
    }

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    protected abstract void refreshBeanFactory() throws BeansException;
}
