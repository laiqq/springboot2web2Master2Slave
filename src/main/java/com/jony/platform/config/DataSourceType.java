package com.jony.platform.config;

public enum DataSourceType {
	ccswrite1("writeOneDataSource"),  
	 ccswrite2("writeTwoDataSource"), 
	 ccsread1("readOneDataSource"),  
	 ccsread2("readTwoDataSource"),
	 
	 mmtwrite1("mmtWriteOneDataSource"),  
	 mmtwrite2("mmtWriteTwoDataSource"), 
	 mmtread1("mmtReadOneDataSource"),  
	 mmtread2("mmtReadTwoDataSource");
	 private String type;  
 
	 private DataSourceType(String type) {  
	        this.type = type;  

	    } 
	   
	    /** 
	     * @return the code 
	     */  
	 public String getType() {  
	        return type;  
	    }  
  
}  
