package com.jony.platform.config;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.alibaba.druid.pool.DruidDataSource;

@Configuration  
@ConditionalOnClass({EnableTransactionManagement.class})  
@Import({ DataBaseConfiguration.class})  
@MapperScan(basePackages={"com.jony.platform.mapper.ccs.read",
		"com.jony.platform.mapper.ccs.write",
		"com.jony.platform.mapper.mmt.read",
		"com.jony.platform.mapper.mmt.write"})  
public class MybatisConfiguration {  
  
  
    @Value("${spring.datasource.type}")  
    private Class<? extends DruidDataSource> dataSourceType;  
  
  
    @Value("${spring.datasource.readSize}")  
    private String dataSourceSize;  
  
    //新核心主从数据源配置
    @Resource(name = "readTwoDataSource")  
    private DruidDataSource readTwoDataSource;  
    
    @Resource(name = "readOneDataSource")  
    private DruidDataSource readOneDataSource;  
    
    @Resource(name = "writeOneDataSource")  
    private DruidDataSource writeOneDataSource;  
    
    @Resource(name = "writeTwoDataSource")  
    private DruidDataSource writeTwoDataSource;  
  
    //MMT核心主从数据源配置
    @Resource(name = "mmtReadTwoDataSource")  
    private DruidDataSource mmtReadTwoDataSource;  
    
    @Resource(name = "mmtReadOneDataSource")  
    private DruidDataSource mmtReadOneDataSource;  
    
    @Resource(name = "mmtWriteOneDataSource")  
    private DruidDataSource mmtWriteOneDataSource;  
    
    @Resource(name = "mmtWriteTwoDataSource")  
    private DruidDataSource mmtWriteTwoDataSource;  
  
    @Bean  
    @ConditionalOnMissingBean  
    public SqlSessionFactory sqlSessionFactory() throws Exception {  
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();  
        sqlSessionFactoryBean.setDataSource(roundRobinDataSouceProxy());  
        sqlSessionFactoryBean.setTypeAliasesPackage("com.jony.platform.model.entity");  
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()  
                .getResources("classpath:mapper/*/*/*.xml"));  
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);  
        return sqlSessionFactoryBean.getObject();  
    }  
    /**  
     * 有多少个数据源就要配置多少个bean  
     * @return  
     */  
    @Bean  
    public AbstractRoutingDataSource roundRobinDataSouceProxy() {  
        int size = Integer.parseInt(dataSourceSize);  
        MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource(size);  
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();  
        //2个写
        targetDataSources.put("writeOneDataSource", writeOneDataSource);  
        targetDataSources.put("writeTwoDataSource", writeTwoDataSource);  
        //2个读
        targetDataSources.put("readOneDataSource", readOneDataSource);  
        targetDataSources.put("readTwoDataSource", readTwoDataSource);  
        
        //MMT 2个写
        targetDataSources.put("mmtWriteOneDataSource", mmtWriteOneDataSource);  
        targetDataSources.put("mmtWriteTwoDataSource", mmtWriteTwoDataSource);  
        //MMT 2个读
        targetDataSources.put("mmtReadOneDataSource", mmtReadOneDataSource);  
        targetDataSources.put("mmtReadTwoDataSource", mmtReadTwoDataSource);  
        proxy.setDefaultTargetDataSource(readOneDataSource);  //默认读1
        proxy.setTargetDataSources(targetDataSources);  
        return proxy;  
    }  
}  