package cn.ian.springframework.test;

import cn.hutool.core.io.IoUtil;
import cn.ian.springframework.beans.BeansException;
import cn.ian.springframework.beans.PropertyValue;
import cn.ian.springframework.beans.PropertyValues;
import cn.ian.springframework.beans.factory.config.BeanDefinition;
import cn.ian.springframework.beans.factory.config.BeanReference;
import cn.ian.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.ian.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import cn.ian.springframework.core.io.DefaultResourceLoader;
import cn.ian.springframework.core.io.Resource;
import cn.ian.springframework.test.bean.UserDAO;
import cn.ian.springframework.test.bean.UserService;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class ApiTest {

    private DefaultResourceLoader resourceLoader;

    @BeforeEach
    public void init() {
        resourceLoader = new DefaultResourceLoader();
        System.out.println(resourceLoader.getClass());
    }

    @Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_url() throws IOException {
        Resource resource = resourceLoader.getResource("");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_xml() throws IOException, BeansException {
        //1. 初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //2. 读取配置文件&注册 Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");
        //3. 获取 Bean 对象
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

    @Test
    public void test_BeanFactory1() throws BeansException {
        //1. 初始化 BeanFactory
        // BeanFactory beanFactory = new BeanFactory();
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //2. 注册 bean
        // BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        // beanFactory.registerBeanDefinition("userService",beanDefinition);
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registryBeanDefinition("userService",beanDefinition);

        //3. 获取 bean
        // UserService userService = (UserService) beanFactory.getBean("userService");
        // userService.queryUserInfo();
        // UserService userService = (UserService) beanFactory.getBean("userService");
        UserService userService = (UserService) beanFactory.getBean("userService", "ian");
        userService.queryUserInfo();

        //4. 第二次获取 bean from Singleton
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        System.out.println(userService == userService_singleton);
    }

    @Test
    public void test_newInstance() throws InstantiationException, IllegalAccessException {
        UserService userService = UserService.class.newInstance();
        userService.queryUserInfo();
    }

    @Test
    public void test_constructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<UserService> constructor = userServiceClass.getDeclaredConstructor(String.class);
        UserService userService = constructor.newInstance("ian");
        userService.queryUserInfo();
    }

    @Test
    public void test_parameterTypes() throws NoSuchMethodException {
        Class<?>[] parameterTypes = UserService.class.getDeclaredConstructor(String.class).getParameterTypes();
        for (Class<?> parameterType : parameterTypes) {
            System.out.println(parameterType.getName());
        }
    }

    @Test
    public void test_cglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        UserService userService = (UserService) enhancer.create(new Class[]{String.class}, new Object[]{"ian"});
        Class[] classes = new Class[1];
        classes[0] = String.class;
        UserService userService1 = (UserService) enhancer.create(classes, new Object[]{"hau"});
        userService1.queryUserInfo();
//        userService.queryUserInfo();
    }

    @Test
    public void test_BeanFactory2() throws BeansException {
        //1. 初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //2. UserDAO 注册
        beanFactory.registryBeanDefinition("userDAO",new BeanDefinition(UserDAO.class));
        //3. UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId","10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDAO",new BeanReference("userDAO")));
        //4. UserService 注入 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registryBeanDefinition("userService",beanDefinition);
        //5. UserService 获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
