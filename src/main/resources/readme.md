工程结构：

small-spring
├── src
├── main
│   └── java
│       └── com.ian.springframework  
│           ├── beans
│           │   ├── factory
│           │   │   ├── factory
│           │   │   │   ├── AutowireCapableBeanFactory.java
│           │   │   │   ├── BeanDefinition.java
│           │   │   │   ├── BeanReference.java
│           │   │   │   ├── ConfigurableBeanFactory.java
│           │   │   │   └── SingletonBeanRegistry.java
│           │   │   ├── support
│           │   │   │   ├── AbstractAutowireCapableBeanFactory.java
│           │   │   │   ├── AbstractBeanDefinitionReader.java
│           │   │   │   ├── AbstractBeanFactory.java
│           │   │   │   ├── BeanDefinitionReader.java
│           │   │   │   ├── BeanDefinitionRegistry.java
│           │   │   │   ├── CglibSubclassingInstantiationStrategy.java
│           │   │   │   ├── DefaultListableBeanFactory.java
│           │   │   │   ├── DefaultSingletonBeanRegistry.java
│           │   │   │   ├── InstantiationStrategy.java
│           │   │   │   └── SimpleInstantiationStrategy.java  
│           │   │   ├── support
│           │   │   │   └── XmlBeanDefinitionReader.java
│           │   │   ├── BeanFactory.java
│           │   │   ├── ConfigurableListableBeanFactory.java
│           │   │   ├── HierarchicalBeanFactory.java
│           │   │   └── ListableBeanFactory.java
│           │   ├── BeansException.java
│           │   ├── PropertyValue.java
│           │   └── PropertyValues.java
│           ├── core.io
│           │   ├── ClassPathResource.java
│           │   ├── DefaultResourceLoader.java
│           │   ├── FileSystemResource.java
│           │   ├── Resource.java
│           │   ├── ResourceLoader.java
│           │   └── UrlResource.java
│           └── utils
│               └── ClassUtils.java
└── test
└── java
└── com.ian.springframework.test
├── bean
│   ├── UserDao.java
│   └── UserService.java
└── ApiTest.java