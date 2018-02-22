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
public class MmtDataSourceAop {  
  
  
    public static final Logger logger = LoggerFactory.getLogger(MmtDataSourceAop.class);  
  
    //通过拦截变量作AOP处理
    /*@Before("execution(* com.jony.platform.service.mmt.read.*.*(..)) ")  
    public void setReadDataSourceType(JoinPoint point) {  
    	logger.info("dataSource == >：come in mmt 从库读的db");   
    	Object[] args = point.getArgs();
  	  	String dbKey = args[0].toString(); 
        if(dbKey.equals("dcn01")){
      	  DataSourceContextHolder.setDataSourceType(DataSourceType.mmtread1.getType());  
      	  logger.info("dataSource == >: mmt从库读的db1 ");
        }else{  	   
      	  DataSourceContextHolder.setDataSourceType(DataSourceType.mmtread2.getType());
      	  logger.info("dataSource == >: mmt从库读的db2 ");
        }       
        logger.info("dataSource == >: get out mmt从库读的db");
        logger.info("dataSource切换到：Read");  
    }
    
    @After("execution(* com.jony.platform.service.mmt.read.*.*(..)) ")
    public void readAfterReturning() throws Throwable {
        DataSourceContextHolder.clearDataSourceType();
        logger.info("=====> clear mmt dataSource aop ");
    }
  
  
    @Before("execution(* com.jony.platform.service.mmt.write.*.*(..)) ")  
    public void setWriteDataSourceType(JoinPoint point) {  
    	logger.info("dataSource == >：come in mmt 主库写的db");   
    	Object[] args = point.getArgs();
  	  	String dbKey = args[0].toString(); 
        if(dbKey.equals("dcn01")){
      	  DataSourceContextHolder.setDataSourceType(DataSourceType.mmtwrite1.getType());  
      	  logger.info("dataSource == >: mmt主库写的db1 ");
        }else{  	   
      	  DataSourceContextHolder.setDataSourceType(DataSourceType.mmtwrite2.getType());
      	  logger.info("dataSource == >: mmt主库写的db2 ");
        }       
        logger.info("dataSource == >: get out mmt主库写的db");
        logger.info("dataSource切换到：Write");  
    }  
    
    @After("execution(* com.jony.platform.service.mmt.write.*.*(..)) ")
    public void writeAfterReturning() throws Throwable {
        DataSourceContextHolder.clearDataSourceType();
        logger.info("=====> clear mmt dataSource aop ");
    }*/
    
    //通过获取当前线程变量作AOP处理
    @Before("execution(* com.jony.platform.service.mmt.read.*.*(..)) ")  
    public void setReadDataSourceType(JoinPoint point) {  
    	logger.info("dataSource == >：come in mmt 从库读的db");   
    	String dbKey = DataSourceContextHolder.getDbkey();
        if(dbKey.equals("dcn01")){
      	  DataSourceContextHolder.setDataSourceType(DataSourceType.mmtread1.getType());  
      	  logger.info("dataSource == >: mmt从库读的db1 ");
        }else{  	   
      	  DataSourceContextHolder.setDataSourceType(DataSourceType.mmtread2.getType());
      	  logger.info("dataSource == >: mmt从库读的db2 ");
        }       
        logger.info("dataSource == >: get out mmt从库读的db");
        logger.info("dataSource切换到：Read");  
    }
    
    @After("execution(* com.jony.platform.service.mmt.read.*.*(..)) ")
    public void readAfterReturning() throws Throwable {
        DataSourceContextHolder.clear();
        logger.info("=====> clear mmt dataSource aop ");
    }
  
  
    @Before("execution(* com.jony.platform.service.mmt.write.*.*(..)) ")  
    public void setWriteDataSourceType(JoinPoint point) {  
    	logger.info("dataSource == >：come in mmt 主库写的db");   
    	String dbKey = DataSourceContextHolder.getDbkey();
        if(dbKey.equals("dcn01")){
      	  DataSourceContextHolder.setDataSourceType(DataSourceType.mmtwrite1.getType());  
      	  logger.info("dataSource == >: mmt主库写的db1 ");
        }else{  	   
      	  DataSourceContextHolder.setDataSourceType(DataSourceType.mmtwrite2.getType());
      	  logger.info("dataSource == >: mmt主库写的db2 ");
        }       
        logger.info("dataSource == >: get out mmt主库写的db");
        logger.info("dataSource切换到：Write");  
    }  
    
    @After("execution(* com.jony.platform.service.mmt.write.*.*(..)) ")
    public void writeAfterReturning() throws Throwable {
        DataSourceContextHolder.clear();
        logger.info("=====> clear mmt dataSource aop ");
    }
}  