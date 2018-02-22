package com.jony.platform.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;

public class HttpClientUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	
	/**
	 * get请求
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static HttpClientResult get(String url, Map<String, Object> params) {
		params = null == params ? new HashMap<String, Object>() : params;
		return invokeGet(url, params, null, null,null,60000, 60000);
	}
	
	/**
	 * get请求
	 * @param url
	 * @param params
	 * @param requestEncode
	 * @param responseEncode
	 * @param connectTimeout
	 * @param soTimeout
	 * @return
	 * @throws Exception
	 */
	public static HttpClientResult get(String url, Map<String, Object> params, String requestEncode, String responseEncode, int connectTimeout, int soTimeout) {
		params = null == params ? new HashMap<String, Object>() : params;
		return invokeGet(url, params, null, requestEncode,responseEncode,connectTimeout, soTimeout);
	}

	/**
	 * get请求
	 * 
	 * @param url
	 * @param params
	 * @param headers
	 * @param connectTimeout
	 * @param soTimeout
	 * @return
	 * @throws Exception 
	 */
	private static HttpClientResult invokeGet(String url, Map<String, Object> params, Map<String, String> headers, String requestEncode,String responseEncode,
			int connectTimeout, int soTimeout) {
		params = null == params ? new HashMap<String, Object>() : params;
		requestEncode = StringUtils.isEmpty(requestEncode) ? Consts.UTF_8.name() : requestEncode;
		responseEncode = StringUtils.isEmpty(responseEncode) ? Consts.UTF_8.name() : responseEncode;
		HttpGet httpGet = null;
		try {
			logger.debug("[HttpClientPool Get] begin get, " + url+", content: "+params.toString());
			url = getGetUrlParams(url, params, requestEncode);
			httpGet = new HttpGet(url);
			RequestConfig config = RequestConfig.custom()
					.setSocketTimeout(soTimeout)
					.setConnectTimeout(connectTimeout)
					.setConnectionRequestTimeout(connectTimeout).build();
			httpGet.setConfig(config);
	
			if (headers != null && !headers.isEmpty()) {
				for (Entry<String, String> entry : headers.entrySet()) {
					httpGet.setHeader(entry.getKey(), entry.getValue());
				}
			}
			CloseableHttpClient httpClient = null;
			CloseableHttpResponse response = null;
			try {
				httpClient = HttpClients.createDefault();
				response = httpClient.execute(httpGet);
				int statusCode = response.getStatusLine().getStatusCode();
				if(statusCode == HttpStatus.SC_OK) {  
					HttpEntity responseEntity = response.getEntity();
					String result = null;
					if (responseEntity != null) {
						result = EntityUtils.toString(responseEntity, responseEncode);
					}
					return new HttpClientResult(String.valueOf(statusCode), "请求成功！", result);
		         } else {
		        	logger.error("[HttpClientUtil GET] 请求失败! statusCode:"+statusCode+" 请求信息:"+url+" 响应信息:"+response.getStatusLine().toString());
		        	return new HttpClientResult(String.valueOf(statusCode), "请求失败！", response.getStatusLine().toString());
		         }
			} catch (SocketTimeoutException e) {
				logger.error("[HttpClientUtil GET] 网络读取异常!"+url, e);
	        	return new HttpClientResult("800","网络读取异常！", "",e);
			} catch (ConnectException e) {
				logger.error("[HttpClientUtil GET] 网络连接异常!"+url, e);
	        	return new HttpClientResult("900","网络连接异常！", "",e);
			} finally {
				closeableHttpResponse(response);
				closeableHttpClient(httpClient);
			}
		} catch (Exception e) {
			abortHttpGet(httpGet);
			logger.error("[HttpClientUtil GET] 异常!"+url, e);
			return new HttpClientResult("500", "请求异常！"+e.getMessage(), "",e);
		} finally {
			logger.debug("[HttpClientPool GET] End get, " + url);
		}

	}
	
	
	/**
	 * 
	 * @param url
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static HttpClientResult postJson(String url, String content) {
		return postJson(url, content, null,null,60000, 60000);
	}
	
	/**
	 * 
	 * @param url
	 * @param content
	 * @param requestEncode
	 * @param responseEncode
	 * @param connectTimeout
	 * @param soTimeout
	 * @return
	 * @throws Exception
	 */
	public static HttpClientResult postJson(String url, String content, String requestEncode,String responseEncode,
			int connectTimeout, int soTimeout) {
		try {
			Map<String,String> headers = new HashMap<String, String>();
			headers.put("Content-type", "application/json");
			HttpEntity requestEntity = getStringEntity(content, requestEncode);
			return invokePost(url, headers, responseEncode, connectTimeout, soTimeout, requestEntity,content);
		}catch (Exception e){
			logger.error("[HttpClientUtil postJson] 异常!"+requestEncode+" , content:"+content, e);
			return new HttpClientResult("900","异常！", "",e);
		}
	}
	
	
	
	
	/**
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static HttpClientResult post(String url, Map<String, Object> params) {
		params = null == params ? new HashMap<String, Object>() : params;
		return post(url, params, null, null, 60000, 60000);
		
	}
	/**
	 * 
	 * @param url
	 * @param params
	 * @param requestEncode
	 * @param responseEncode
	 * @param connectTimeout
	 * @param soTimeout
	 * @return
	 * @throws Exception
	 */
	public static HttpClientResult post(String url, Map<String, Object> params, String requestEncode,String responseEncode,
			int connectTimeout, int soTimeout) {
		try {
			params = null == params ? new HashMap<String, Object>() : params;
			Map<String,String> headers = new HashMap<String, String>();
			headers.put("Content-type", "application/x-www-form-urlencoded");
			HttpEntity requestEntity = getHttpEntity(params, requestEncode);
			return invokePost(url, headers, responseEncode, connectTimeout, soTimeout, requestEntity,params.toString());
		}catch (Exception e){
			logger.error("[HttpClientUtil POST] 异常!"+requestEncode+" , content:"+params.toString(), e);
			return new HttpClientResult("900","异常！", "",e);
		}
	}
	
	
	/**
	 *  执行 post请求
	 * @param url
	 * @param headers
	 * @param responseEncode
	 * @param connectTimeout
	 * @param soTimeout
	 * @param requestEntity
	 * @return
	 */
	private static HttpClientResult invokePost(String url, Map<String, String> headers,String responseEncode,
			int connectTimeout, int soTimeout,HttpEntity requestEntity,String content) {
		responseEncode = StringUtils.isEmpty(responseEncode) ? Consts.UTF_8.name() : responseEncode;
		StringBuilder sb = new StringBuilder().append("url : ").append(url).append(", content : ").append(content);
		logger.debug("[HttpClientPool POST] Begin post, " + sb.toString());
		HttpPost httpPost = null;
		try {
			httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(soTimeout)
					.setConnectTimeout(connectTimeout)
					.setConnectionRequestTimeout(connectTimeout)
					.setExpectContinueEnabled(false).build();
			httpPost.setConfig(requestConfig);
	
			if (headers != null && !headers.isEmpty()) {
				for (Entry<String, String> entry : headers.entrySet()) {
					httpPost.setHeader(entry.getKey(), entry.getValue());
				}
			}
		
			httpPost.setEntity(requestEntity);
			CloseableHttpClient httpClient = null;
			CloseableHttpResponse response = null;
			try {
				httpClient = HttpClients.createDefault();
				response = httpClient.execute(httpPost);
				int statusCode = response.getStatusLine().getStatusCode();
				if(statusCode == HttpStatus.SC_OK) {  
					HttpEntity responseEntity = response.getEntity();
					String result = null;
					if (responseEntity != null) {
						result = EntityUtils.toString(responseEntity, responseEncode);
					}
					return new HttpClientResult(String.valueOf(statusCode), "请求成功！", result);
		         } else {
		        	logger.error("请求失败! statusCode:"+statusCode+" 请求信息:"+sb.toString()+" 响应信息:"+response.getStatusLine().toString());
		        	return new HttpClientResult(String.valueOf(statusCode), "请求失败！", response.getStatusLine().toString());
		         }
			}catch (SocketTimeoutException e) {
				logger.error("[HttpClientUtil POST] 网络读取异常!"+sb.toString(), e);
	        	return new HttpClientResult("800","网络读取异常！", "",e);
			}catch (ConnectException e) {
				logger.error("[HttpClientUtil POST] 网络连接异常!"+sb.toString(), e);
	        	return new HttpClientResult("900","网络连接异常！", "",e);
			} finally {
				closeableHttpResponse(response);
				closeableHttpClient(httpClient);
			}
		} catch (Exception e) {
			abortHttpPost(httpPost);
			logger.error("[HttpClientUtil POST] 异常!"+sb.toString(), e);
			return new HttpClientResult("500", "请求异常！"+e.getMessage(), "",e);
		} finally {
			logger.debug("[HttpClientPool POST] End post, " + sb.toString());
		}
		
	}
	
	/**
	 * 获取GET url参数
	 * @param url
	 * @param params
	 * @param requestEncode
	 * @return
	 */
	public static String getGetUrlParams(String url,Map<String,Object> params,String requestEncode){
		try {
			requestEncode = StringUtils.isEmpty(requestEncode) ? Consts.UTF_8.name() : requestEncode;
			StringBuilder sb = new StringBuilder(url);
			int i = 0;
			if (params != null && !params.isEmpty()) {
				for (Entry<String, Object> entry : params.entrySet()) {
					sb.append((i == 0 && !url.contains("?")) ? "?" : "&");
					sb.append(entry.getKey()).append("=").append(URLEncoder.encode(String.valueOf(entry.getValue()), requestEncode));
					i++;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			throw new  RuntimeException("参数编码异常!",e);
		}
	}
	
	private static HttpEntity getHttpEntity(Map<String, Object> params,String requestEncode) throws UnsupportedEncodingException{
		requestEncode = StringUtils.isEmpty(requestEncode) ? Consts.UTF_8.name() : requestEncode;
		List<NameValuePair> paramPairs = new ArrayList<NameValuePair>();
		if (params != null && !params.isEmpty()) {
			for (Entry<String, Object> entry : params.entrySet()) {
				BasicNameValuePair param = new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
				paramPairs.add(param);
			}
		}
		return new UrlEncodedFormEntity(paramPairs, requestEncode);
	}
	
	private static HttpEntity getStringEntity(String content,String requestEncode) throws UnsupportedEncodingException{
		requestEncode = StringUtils.isEmpty(requestEncode) ? Consts.UTF_8.name() : requestEncode;
		HttpEntity requestEntity = new StringEntity(content, requestEncode);
		return requestEntity;
	}
	
	
	private static void closeableHttpClient(CloseableHttpClient closeableHttpClient){
		try {
			if(null !=closeableHttpClient) {
			  closeableHttpClient.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void closeableHttpResponse(CloseableHttpResponse closeableHttpResponse){
		try {
			if(null !=closeableHttpResponse) {
				closeableHttpResponse.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void abortHttpGet(HttpGet httpGet){
		try {
			if(null !=httpGet) {
				httpGet.abort();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void abortHttpPost(HttpPost httpPost){
		try {
			if(null !=httpPost) {
				httpPost.abort();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*public static void main(String[] args) {
		String jsonStr = "{\"appKey\":\"bqjrokmqazyhnuhb\",\"data\":{\"execDate\":\"2017/07/12\"},\"group\":\"\",\"methodName\":\"noticeFeeDerate\",\"serviceChannel\":\"mmt_01\",\"serviceLine\":\"mmt\",\"serviceName\":\"com.bqjr.file.spi.ReceiveFileFeeDerateService\",\"sig\":\"1qaz0okmfghjk3edc9ijn4esz\",\"time\":"+new Date().getTime()+",\"version\":\"1.0.0\"}";
		HttpClientResult result = HttpClientUtil.postJson("http://10.83.3.75:8080/cts-openapi/api/invoke", jsonStr);
		System.out.println(result);
	}*/

	
}
