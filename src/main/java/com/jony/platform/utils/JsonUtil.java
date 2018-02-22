/**   
* @Title: JsonUtils.java 
* @Package： com.appvworks.framework.Utils 
* @Description: TODO
* @author：duanwei 
* @date： 2015年7月2日 上午10:48:32  
*/
package com.jony.platform.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class JsonUtil {
  
	private static final ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setSerializationInclusion(Include.ALWAYS);
        /*
         * 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
         */
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

	    /**
	     * 将对象转换为JSON字符串
	     * 
	     * @param obj
	     * @return
	     */
	    public static String toJson(Object obj) {
	        try {
	            return objectMapper.writeValueAsString(obj);
	        } catch (Exception e) {
	            //log.error(e.getMessage(), e);
	            return null;
	        }
	    }

	    /**
	     * 将JSON字符串转换为对象
	     * 
	     * @Title: toObject
	     * @Description: TODO
	     * @param clazz
	     * @param json
	     * @return
	     * @return T
	     * @throws
	     */
	    public static <T> T toObject(Class<T> clazz, String json) {
	        T obj = null;
	        try {
	            obj = (T) objectMapper.readValue(json, clazz);
	        } catch (Exception e) {
	           //log.error(e.getMessage(), e);
	        }
	        return obj;
	    }

	    /***
	     * 将JSON字符串转换为JSONObject对象
	     */
	    @SuppressWarnings("static-access")
	    public static JSONObject toJSONObject(String json) {
	        JSONObject jsonObject = null;
	        try {
	            JSONObject dataJson = new JSONObject();
	            jsonObject = dataJson.parseObject(json);
	        } catch (Exception e) {
	            System.out.println("jsonObject:" + e.getMessage());
	            //log.error(e.getMessage(), e);
	        }
	        return jsonObject;
	    }

	    /**
	     * 将Json字符串转换成List
	     * 
	     * @return
	     */
	    public static <T> List<T> toObjectList(Class<T> clazz, String json) {
	        List<T> jsonList = new ArrayList<T>();
	        try {
	            JSONArray jsonArray = JSONArray.parseArray(json);
	            for (int i = 0; i < jsonArray.size(); i++) {
	                JSONObject jsonObject = jsonArray.getJSONObject(i);
	                jsonList.add((T) JSONObject.toJavaObject(jsonObject, clazz));
	            }
	        } catch (Exception e) { 
	            //log.error("Json string to List a exception:", e);
	        }
	        return jsonList;
	    }
 
}
