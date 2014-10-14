package com.example.infra.mybatis.plugin;

import com.example.domain.model.Versionable;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by shimizukazuki on 2014/10/12.
 */
@Intercepts({@Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class})})
@Component
public class VersionPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object entity = invocation.getArgs()[1];
        Object result;
        if (entity instanceof Versionable) {
            Versionable versionable = (Versionable) entity;
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            if (mappedStatement.getSqlCommandType() == SqlCommandType.INSERT) {
                versionable.setVersion(1);
            }
            result = invocation.proceed();
            if (mappedStatement.getSqlCommandType() == SqlCommandType.UPDATE) {
                versionable.setVersion(versionable.getVersion() + 1);
            }
        } else {
            result = invocation.proceed();
        }
        return result;

    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
