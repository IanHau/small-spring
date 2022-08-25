package cn.ian.springframework.core.io;

public interface ResourceLoader {

    /**
     * 伪路径前缀："classpath"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
