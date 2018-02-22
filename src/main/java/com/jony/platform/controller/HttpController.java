package com.jony.platform.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.jony.platform.model.res.BaseResHeader;
import com.jony.platform.utils.HttpClientResult;
import com.jony.platform.utils.HttpClientUtil;


@RestController
@RequestMapping("/api")
public class HttpController {
	
	private static final Logger logger =LoggerFactory.getLogger(HttpController.class);
	
	@Value("${imageUrl1}")
	private String imageUrl1;
	
	@Value("${imageUrl2}")
	private String imageUrl2;
	 

	@RequestMapping(value = "/image/{idNo}",method = RequestMethod.POST)
	public BaseResHeader findImage(@PathVariable String idNo){
		//将json格式转换为对象
		//JSONObject dataJson = new JSONObject();
		logger.info("come in");
		BaseResHeader baseResHeader = new BaseResHeader();
		String jsonStr = "{\"date\":\"2028-02-07\",\"actualPayment\":\"true\",\"feeChange\":\"false\",\"tbjPrePayment\":\"true\",\"customerId\":\"\",\"idNo\":\"520221199401074533\"}";
		HttpClientResult result1 = HttpClientUtil.postJson(imageUrl1, jsonStr);//hexin1
		HttpClientResult result2 = HttpClientUtil.postJson(imageUrl2, jsonStr);//hexin2

		try {	
			baseResHeader.setResultMessage(result1.toString());
			baseResHeader.setResultCode(result2.toString());
			logger.info(result1.toString());
			logger.info(result2.toString());
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		return baseResHeader;
	}
}
