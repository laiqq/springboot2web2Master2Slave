package com.jony.platform.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jony.platform.webservice.UserService;


@Configuration
public class CxfConfig {
	 @Autowired
	 private Bus bus;
	 @Autowired
	 private UserService userService;
	 
	//指定目录
	@Bean
	public ServletRegistrationBean cxfServlet(){
		 return new ServletRegistrationBean(new CXFServlet(),"/Service/*");//接口发布在/Service目录下

		}
	
	 @Bean
	 public Endpoint endpoint() {
	        EndpointImpl endpoint = new EndpointImpl(bus,userService);
	        endpoint.publish("/userService");//接口发布在 /NetbarServices 目录下
	        return endpoint;
	    }

	
}