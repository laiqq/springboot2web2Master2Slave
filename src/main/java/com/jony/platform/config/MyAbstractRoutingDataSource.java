package com.jony.platform.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import com.jony.platform.config.DataSourceContextHolder;
import com.jony.platform.config.DataSourceType;

public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource {  
  
  
    //private final int dataSourceNumber;  
    //private AtomicInteger count = new AtomicInteger(0);  
  
  
    public MyAbstractRoutingDataSource(int dataSourceNumber) {  
        //this.dataSourceNumber = dataSourceNumber;  
    }  
  
  
    @Override  
    protected Object determineCurrentLookupKey() {  
    	String typeKey = DataSourceContextHolder.getDataSourceType();  
        //CCS核心数据源切换
        if (typeKey.equals(DataSourceType.ccswrite1.getType()))  
            return DataSourceType.ccswrite1.getType();  
        if (typeKey.equals(DataSourceType.ccswrite2.getType()))  
            return DataSourceType.ccswrite2.getType();  
        if (typeKey.equals(DataSourceType.ccsread1.getType()))  
            return DataSourceType.ccsread1.getType();  
        if (typeKey.equals(DataSourceType.ccsread2.getType()))  
            return DataSourceType.ccsread2.getType();  
        //MMT核心数据源切换
        if (typeKey.equals(DataSourceType.mmtwrite1.getType()))  
            return DataSourceType.mmtwrite1.getType();  
        if (typeKey.equals(DataSourceType.mmtwrite2.getType()))  
            return DataSourceType.mmtwrite2.getType();  
        if (typeKey.equals(DataSourceType.mmtread1.getType()))  
            return DataSourceType.mmtread1.getType();  
        if (typeKey.equals(DataSourceType.mmtread2.getType()))  
            return DataSourceType.mmtread2.getType();  
        
        return DataSourceType.ccsread1.getType();  //默认读ccs的1库
        
        
    }  
}  
