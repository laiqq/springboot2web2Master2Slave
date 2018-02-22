package com.jony.platform.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.jony.platform.config.DataSourceContextHolder;
import com.jony.platform.config.DataSourceType;

@Aspect  
@Component  
public class DataSourceAop {  
  
  
    public static final Logger logger = LoggerFactory.getLogger(DataSourceAop.class);  
  
    //通过拦截参数作AOP出来
    /*@Before("execution(* com.jony.platform.service.ccs.read.*.*(..)) ")  
    public void setReadDataSourceType(JoinPoint point) {  
    	logger.info("dataSource == >：come in ccs 从库读的db");   
    	Object[] args = point.getArgs();
  	  	String dbKey = args[0].toString(); 
        if(dbKey.equals("dcn01")){
      	  DataSourceContextHolder.setDataSourceType(DataSourceType.ccsread1.getType());  
      	  logger.info("dataSource == >: ccs从库读的db1 ");
        }else{  	   
      	  DataSourceContextHolder.setDataSourceType(DataSourceType.ccsread2.getType());
      	  logger.info("dataSource == >: ccs从库读的db2 ");
        }       
        logger.info("dataSource == >: get out ccs从库读的db");
        logger.info("dataSource切换到：Read");  
    }
    
    @After("execution(* com.jony.platform.service.ccs.read.*.*(..)) ")
    public void readAfterReturning() throws Throwable {
        DataSourceContextHolder.clearDataSourceType();
        logger.info("=====> clear ccs dataSource aop ");
    }
  
  
    @Before("execution(* com.jony.platform.service.ccs.write.*.*(..)) ")  
    public void setWriteDataSourceType(JoinPoint point) {  
    	logger.info("dataSource == >：come in ccs 主库写的db");   
    	Object[] args = point.getArgs();
  	  	String dbKey = args[0].toString(); 
        if(dbKey.equals("dcn01")){
      	  DataSourceContextHolder.setDataSourceType(DataSourceType.ccswrite1.getType());  
      	  logger.info("dataSource == >: ccs主库写的db1 ");
        }else{  	   
      	  DataSourceContextHolder.setDataSourceType(DataSourceType.ccswrite2.getType());
      	  logger.info("dataSource == >: ccs主库写的db2 ");
        }       
        logger.info("dataSource == >: get out ccs主库写的db");
        logger.info("dataSource切换到：Write");  
    }  
    
    @After("execution(* com.jony.platform.service.ccs.write.*.*(..)) ")
    public void writeAfterReturning() throws Throwable {
        DataSourceContextHolder.clearDataSourceType();
        logger.info("=====> clear ccs dataSource aop ");
    }*/
    
    
    //通过获取当前线程变量作AOP处理
    @Before("execution(* com.jony.platform.service.ccs.read.*.*(..)) ")  
    public void setReadDataSourceType(JoinPoint point) {  
    	logger.info("dataSource == >：come in ccs 从库读的db");   
    	String dbKey = DataSourceContextHolder.getDbkey();
        if(dbKey.equals("dcn01")){
      	  DataSourceContextHolder.setDataSourceType(DataSourceType.ccsread1.getType());  
      	  logger.info("dataSource == >: ccs从库读的db1 ");
        }else{  	   
      	  DataSourceContextHolder.setDataSourceType(DataSourceType.ccsread2.getType());
      	  logger.info("dataSource == >: ccs从库读的db2 ");
        }       
        logger.info("dataSource == >: get out ccs从库读的db");
        logger.info("dataSource切换到：Read");  
    }
    
    @After("execution(* com.jony.platform.service.ccs.read.*.*(..)) ")
    public void readAfterReturning() throws Throwable {
        DataSourceContextHolder.clear();
        logger.info("=====> clear ccs dataSource aop ");
    }
  
  
    @Before("execution(* com.jony.platform.service.ccs.write.*.*(..)) ")  
    public void setWriteDataSourceType(JoinPoint point) {  
    	logger.info("dataSource == >：come in ccs 主库写的db");   
    	String dbKey = DataSourceContextHolder.getDbkey();
        if(dbKey.equals("dcn01")){
      	  DataSourceContextHolder.setDataSourceType(DataSourceType.ccswrite1.getType());  
      	  logger.info("dataSource == >: ccs主库写的db1 ");
        }else{  	   
      	  DataSourceContextHolder.setDataSourceType(DataSourceType.ccswrite2.getType());
      	  logger.info("dataSource == >: ccs主库写的db2 ");
        }       
        logger.info("dataSource == >: get out ccs主库写的db");
        logger.info("dataSource切换到：Write");  
    }  
    
    @After("execution(* com.jony.platform.service.ccs.write.*.*(..)) ")
    public void writeAfterReturning() throws Throwable {
        DataSourceContextHolder.clear();
        logger.info("=====> clear ccs dataSource aop ");
    }
}  