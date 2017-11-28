package myweb

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean
import org.springframework.context.EnvironmentAware
import org.springframework.core.env.Environment
import org.springframework.core.env.PropertiesPropertySource
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.core.env.MapPropertySource


class Application extends GrailsAutoConfiguration implements EnvironmentAware {

    static void main(String[] args) {
        GrailsApp.run(Application, args)
        }

        @Override
        void setEnvironment(Environment environment) {
            // "external.config.location" is defined in build.gradle ... bootRun {jvmArgs=}
            String configPath = System.properties["external.config.location"]
            log.info("External config will be loaded from: ${configPath}")
            Resource resourceConfig = new FileSystemResource(configPath)
            YamlPropertiesFactoryBean ypfb = new YamlPropertiesFactoryBean()
            ypfb.setResources([resourceConfig] as Resource[])
            ypfb.afterPropertiesSet()
            Properties properties = ypfb.getObject()
            environment.propertySources.addFirst(new PropertiesPropertySource("external-config", properties))
        }
}