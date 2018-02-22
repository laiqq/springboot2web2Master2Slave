package com.jony.platform.webservice;

import java.util.HashMap;
import java.util.Map;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.jony.platform.utils.HttpClientResult;
import com.jony.platform.utils.HttpClientUtil;
import com.jony.platform.utils.JsonUtil;

@WebService
@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Value("${imageUrl1}")
	private String imageUrl1;
	
	@Override
	public String JudgeContrIsOverdueBycontractNo(@WebParam(name = "contrNbr") String contrNbr) {
		logger.info("come in ");
		Map<String,String> reqs = new HashMap<String,String>();
		String jsonStr = "{\"date\":\"2028-02-07\",\"actualPayment\":\"true\",\"feeChange\":\"false\",\"tbjPrePayment\":\"true\",\"customerId\":\"\",\"idNo\":\"520221199401074533\"}";
		HttpClientResult result = HttpClientUtil.postJson(imageUrl1, jsonStr);
		try {		
			logger.info(result.toString());
			reqs.put("result", "success");
			reqs.put("msg", " 请求已经接收成功!!");
			reqs.put("dcn", result.toString());
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}				
		return JsonUtil.toJson(reqs);
	}

	@Override
	public String selectEarlyClearByContrNbr(@WebParam(name = "contrNbr") String contrNbr) {
		logger.info("come in ");
		Map<String,String> reqs = new HashMap<String,String>();
		String jsonStr = "{\"date\":\"2028-02-07\",\"actualPayment\":\"true\",\"feeChange\":\"false\",\"tbjPrePayment\":\"true\",\"customerId\":\"\",\"idNo\":\"520221199401074533\"}";
		HttpClientResult result = HttpClientUtil.postJson(imageUrl1, jsonStr);
		try {		
			logger.info(result.toString());
			reqs.put("result", "success");
			reqs.put("msg", " 请求已经接收成功!!");
			reqs.put("dcn", result.toString());
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}				
		return JsonUtil.toJson(reqs);
	}

}