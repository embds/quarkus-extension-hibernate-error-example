package com.embds.quarkus.example.extension.hibernate.error.deployment;

import com.embds.quarkus.example.extension.hibernate.error.runtime.entity.ExampleEntity;
import com.embds.quarkus.example.extension.hibernate.error.runtime.repository.ExampleRepository;

import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.hibernate.orm.deployment.spi.AdditionalJpaModelBuildItem;

class QuarkusExtensionHibernateErrorExampleProcessor {

    private static final String FEATURE = "quarkus-extension-hibernate-error-example";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }
    
    @BuildStep
    AdditionalBeanBuildItem exampleRepositoryBean() {
    	return new AdditionalBeanBuildItem(ExampleRepository.class);
    }
    
    @BuildStep
    AdditionalJpaModelBuildItem exampleEntitybuildItem() {
    	return new AdditionalJpaModelBuildItem(ExampleEntity.class.getName());
    }
}
