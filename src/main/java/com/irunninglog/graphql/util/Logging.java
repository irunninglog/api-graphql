package com.irunninglog.graphql.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import uk.org.lidalia.sysoutslf4j.context.SysOutOverSLF4J;

@Component
public final class Logging implements InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(Logging.class);

    @Override
    public void afterPropertiesSet() {
        SysOutOverSLF4J.sendSystemOutAndErrToSLF4J();
        System.out.println("logged via system.out");
        System.err.println("logged via system.err");

        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
        java.util.logging.Logger julLogger = java.util.logging.Logger.getLogger(Logging.class.getName());
        julLogger.info("logged via jul");

        org.apache.log4j.Logger log4JLogger = org.apache.log4j.Logger.getLogger(Logging.class);
        log4JLogger.info("logged via log4j");

        Log jclLogger = LogFactory.getLog(Logging.class);
        jclLogger.info("logged via jcl");

        LOG.info("logged via slf4j");
    }

}
