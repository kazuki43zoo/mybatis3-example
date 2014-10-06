package com.example.test.executetype.repository;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;

/**
 * Created by shimizukazuki on 2014/10/09.
 */
public class BachRepositoryBeanNameGenerator implements BeanNameGenerator {
    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        String defaultBeanName = Introspector.decapitalize(ClassUtils.getShortName(definition
                .getBeanClassName()));
        String repositoryBeanName = defaultBeanName.replaceAll("Repository", "BatchRepository");
        return repositoryBeanName;
    }
}
