package com.irunninglog.graphql;

import ch.qos.logback.access.servlet.TeeFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import uk.org.lidalia.sysoutslf4j.context.SysOutOverSLF4J;

import java.util.Collections;

@SpringBootApplication()
public class GraphQLApplication implements InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(GraphQLApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GraphQLApplication.class);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public FilterRegistrationBean requestResponseFilter() {
        final FilterRegistrationBean<TeeFilter> filterRegBean = new FilterRegistrationBean<>();
        TeeFilter filter = new TeeFilter();
        filterRegBean.setFilter(filter);
        filterRegBean.setUrlPatterns(Collections.singletonList("/"));
        filterRegBean.setName("Request Response Filter");
        filterRegBean.setAsyncSupported(Boolean.TRUE);
        return filterRegBean;
    }

    @Override
    public void afterPropertiesSet() {
        SysOutOverSLF4J.sendSystemOutAndErrToSLF4J();
        System.out.println("logged via system.out");
        System.err.println("logged via system.err");

        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
        java.util.logging.Logger julLogger = java.util.logging.Logger.getLogger(GraphQLApplication.class.getName());
        julLogger.info("logged via jul");

        org.apache.log4j.Logger log4JLogger = org.apache.log4j.Logger.getLogger(GraphQLApplication.class);
        log4JLogger.info("logged via log4j");

        Log jclLogger = LogFactory.getLog(GraphQLApplication.class);
        jclLogger.info("logged via jcl");

        LOG.info("logged via slf4j");
    }

}
