package com.goldin.config;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.TimeZone;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.goldin")
public class WebConfig implements WebApplicationInitializer {
    static {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Kyiv"));
    }
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(WebConfig.class);

        ServletRegistration.Dynamic dispatcher
                = servletContext.
                addServlet("dispatcher",
                        new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

//        FilterRegistration.Dynamic filter = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
//        filter.addMappingForUrlPatterns(null, false, "/*");

    }

}
