package cn.ian.springframework.beans.factory;

import cn.ian.springframework.beans.BeansException;
import com.sun.istack.internal.Nullable;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {

    <T> Map<String, T> getBeansOfType(@Nullable Class<T> type) throws BeansException;

}
