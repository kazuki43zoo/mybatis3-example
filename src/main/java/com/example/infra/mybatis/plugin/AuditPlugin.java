package com.example.infra.mybatis.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.joda.time.DateTime;
import org.springframework.data.domain.Auditable;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.terasoluna.gfw.common.date.DateFactory;

import javax.inject.Inject;
import java.util.Properties;

@Intercepts({@Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class})})
@Component
public class AuditPlugin implements Interceptor {

    @Inject
    DateFactory dateFactory;

    @Inject
    AuditorAware<String> auditorAware;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object entity = invocation.getArgs()[1];
        if (entity instanceof Auditable) {
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            @SuppressWarnings("unchecked")
            Auditable<String, ?> auditable = (Auditable<String, ?>) entity;
            DateTime operatedAt = dateFactory.newDateTime();
            String currentAuditor = auditorAware.getCurrentAuditor();
            if (mappedStatement.getSqlCommandType() == SqlCommandType.INSERT) {
                auditable.setCreatedBy(currentAuditor);
                auditable.setCreatedDate(operatedAt);
            }
            auditable.setLastModifiedBy(currentAuditor);
            auditable.setLastModifiedDate(operatedAt);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
