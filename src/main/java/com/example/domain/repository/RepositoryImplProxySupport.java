package com.example.domain.repository;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.repository.util.ClassUtils;

import java.lang.reflect.*;

public abstract class RepositoryImplProxySupport<R>
        implements BeanPostProcessor, InvocationHandler {

    protected final Type[] parameterizedTypes = ((ParameterizedType) getClass()
            .getGenericSuperclass()).getActualTypeArguments();

    @SuppressWarnings("unchecked")
    protected final Class<R> repositoryInterface = (Class<R>) parameterizedTypes[0];

    private R repository;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (repositoryInterface.isAssignableFrom(bean.getClass())) {
            this.repository = repositoryInterface.cast(bean);
            return Proxy.newProxyInstance(
                    repositoryInterface.getClassLoader(),
                    new Class[]{repositoryInterface},
                    this);
        }
        return bean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(repository, args);
        } catch (Exception e) {
            ClassUtils.unwrapReflectionException(e);
        } catch (Throwable t) {
            return t;
        }
        throw new IllegalStateException("fail.");
    }

    protected R getRepository() {
        return repository;
    }

}
