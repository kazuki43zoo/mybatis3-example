package com.example.domain.repository;

import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.lang.reflect.Method;

public abstract class GenericRepositoryImplProxySupport<R extends GenericRepository<T, ID>, T extends Persistable, ID extends Serializable>
        extends RepositoryImplProxySupport<R> {

    @SuppressWarnings("unchecked")
    private final Class<T> entityClass = (Class<T>) parameterizedTypes[1];

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("save")) {
            T entity = entityClass.cast(args[0]);
            return save(entity);
        } else {
            return super.invoke(proxy, method, args);
        }
    }

    protected T save(T entity) {
        R repository = getRepository();
        if (entity.isNew()) {
            repository.create(entity);
        } else {
            repository.update(entity);
        }
        return entity;
    }

}
