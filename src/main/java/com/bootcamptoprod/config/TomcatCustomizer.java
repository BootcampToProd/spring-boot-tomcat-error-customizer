package com.bootcamptoprod.config;

import org.apache.catalina.Container;
import org.apache.catalina.core.StandardHost;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class TomcatCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {

        factory.addContextCustomizers((context) -> {
            Container parent = context.getParent();
            if (parent instanceof StandardHost standardHost) {
                standardHost.setErrorReportValveClass(CustomTomcatErrorValve.class.getName());
            }
        });
    }
}