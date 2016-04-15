package org.boaboa.divisa.ws.config;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;

import javax.xml.ws.Endpoint;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.boaboa.divisa.ws.webservice.DivisaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.inject.Inject;

/**
 * Created by pperez on 14-04-16.
 */
@Configuration
@PropertySource("classpath:divisas-ws.properties")
@PropertySource(value = "file:${user.home}${file.separator}.boasalas${file.separator}boasalas.properties", ignoreResourceNotFound = true)
public class WebServiceConfiguration {

    @Inject
    private DivisaService divisaService;

    @Bean
    public SpringBus cxf() {
        return new SpringBus();
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(cxf(), divisaService);
        endpoint.setAddress("/divisas");
        endpoint.publish();
        return endpoint;
    }

}
