package com.jony.platform.config;


public class DataSourceContextHolder {
	//本地线程全局变量
    private static final ThreadLocal<String> dataSourceTypeHolder  = new ThreadLocal<String>();
    private static final ThreadLocal<String> dbKeyHolder  = new ThreadLocal<String>();
    
    public static ThreadLocal<String> getLocalDataSourceType() {
        return dataSourceTypeHolder;
    }
	public static ThreadLocal<String> getLocalDbkeyholder() {
		return dbKeyHolder;
	}
    
    /**
     * 使用setDataSourceType设置当前的
     * @param dataSourceType
     */
    public static void setDataSourceType(String dataSourceType) {
    	dataSourceTypeHolder.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return dataSourceTypeHolder.get();
    }


    /**
     * 使用dbKey设置当前的
     * @param dbKey
     */
    public static void setDbkey(String dbKey) {
    	dbKeyHolder.set(dbKey);
    }

    public static String getDbkey() {
        return dbKeyHolder.get();
    }

    public static void clear() {
    	dataSourceTypeHolder.remove();
    	dbKeyHolder.remove();
    }
}
