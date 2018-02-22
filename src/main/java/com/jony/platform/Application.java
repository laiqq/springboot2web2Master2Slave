package com.jony.platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import com.jony.platform.config.CxfConfig;



// Spring Boot 应用的标识
@SpringBootApplication
//mapper接口类扫描包配置
//@MapperScan("cn.bqjr.platform.mapper")
public class Application {

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(Application.class,args);
        
    }
 
    //webService配置
	@Bean
	public CxfConfig CxfConfigInit(){
		return new CxfConfig();
	}
	
	// 启动的时候要注意，由于我们在controller中注入了RestTemplate，所以启动的时候需要实例化该类的一个实例 
    @Autowired 
    private RestTemplateBuilder builder; 
   
    // 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例 
    @Bean 
    public RestTemplate restTemplate() { 
        return builder.build(); 
    } 
    
}
