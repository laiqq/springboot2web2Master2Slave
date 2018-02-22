package com.jony.platform.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.alibaba.druid.pool.DruidDataSource;

@Configuration  
public class DataBaseConfiguration{  
  
  
    @Value("${spring.datasource.type}")  
    private Class<? extends DruidDataSource> dataSourceType;  
    
    /** 
     * 新核心有2个主库就要配置2个 
     * @return 
     */  
    @Bean(name="writeOneDataSource", destroyMethod = "close", initMethod="init")  
    @ConfigurationProperties(prefix = "spring.datasource.write1")  
    public DruidDataSource writeOneDataSource() {  
    	return new DruidDataSource();  
    }  
    
    @Bean(name="writeTwoDataSource", destroyMethod = "close", initMethod="init")  
    @Primary  //必须有一个这个注解，多了就会报错
    @ConfigurationProperties(prefix = "spring.datasource.write2")  
    public DruidDataSource writeTwoDataSource() {  
    	return new DruidDataSource(); 
    }  
    /** 
     * 新核心有2个从库就要配置2个 
     * @return 
     */  
    @Bean(name = "readOneDataSource", destroyMethod = "close", initMethod="init")  
    @ConfigurationProperties(prefix = "spring.datasource.read1")  
    public DruidDataSource readOneDataSource(){  
    	return new DruidDataSource(); 
    }  
  
  
    @Bean(name = "readTwoDataSource", destroyMethod = "close", initMethod="init")  
    @ConfigurationProperties(prefix = "spring.datasource.read2")  
    public DruidDataSource readTwoDataSource() {  
    	return new DruidDataSource(); 
    }  
    
    /** 
     * MMT核心有2个主库就要配置2个 
     * @return 
     */  
    @Bean(name="mmtWriteOneDataSource", destroyMethod = "close", initMethod="init")  
    @ConfigurationProperties(prefix = "spring.datasource.mmt.write1")  
    public DruidDataSource mmtWriteOneDataSource() {  
    	return new DruidDataSource();  
    }  
    
    @Bean(name="mmtWriteTwoDataSource", destroyMethod = "close", initMethod="init")  
    @ConfigurationProperties(prefix = "spring.datasource.mmt.write2")  
    public DruidDataSource mmtWriteTwoDataSource() {  
    	return new DruidDataSource(); 
    }  
    /** 
     * MMT核心有2个从库就要配置2个 
     * @return 
     */  
    @Bean(name = "mmtReadOneDataSource", destroyMethod = "close", initMethod="init")  
    @ConfigurationProperties(prefix = "spring.datasource.mmt.read1")  
    public DruidDataSource mmtReadOneDataSource(){  
    	return new DruidDataSource(); 
    }  
  
  
    @Bean(name = "mmtReadTwoDataSource", destroyMethod = "close", initMethod="init")  
    @ConfigurationProperties(prefix = "spring.datasource.mmt.read2")  
    public DruidDataSource mmtReadTwoDataSource() {  
    	return new DruidDataSource(); 
    }  
  
    @Bean("readDataSources")  
    public List<DruidDataSource> readDataSources(){  
        List<DruidDataSource> dataSources=new ArrayList<DruidDataSource>();  
        dataSources.add(readTwoDataSource());  
        dataSources.add(readOneDataSource());  
        return dataSources;  
    }  
}  