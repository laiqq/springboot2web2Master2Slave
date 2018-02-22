package com.jony.platform.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.jony.platform.model.entity.User;
import com.jony.platform.model.res.TestRes;
import com.jony.platform.service.ccs.read.ReadTestService;



/**
 * Created by jony
 */
@Controller
public class TemplateController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${restTemplateUrl}")
	private String restTemplateUrl;
	@Autowired
	private ReadTestService testService;
	
	private static final Logger logger =LoggerFactory.getLogger(HttpController.class);
	
	@ModelAttribute 
    @ResponseBody
    @RequestMapping(value = "/consumer",method = RequestMethod.POST)
	public TestRes findImage(@RequestBody User user,HttpServletRequest request){
    	logger.info("come in");
    	logger.info(request.toString());
    	TestRes res = new TestRes();
    	//TestRes res = restTemplate.postForObject(restTemplateUrl,request,TestRes.class);  
    	
    	@SuppressWarnings("unchecked")
		List<User> list = restTemplate.postForObject(restTemplateUrl,user,List.class);
    	res.setList(list);
    	
		return res;
     
    }
	
    @ResponseBody
    @RequestMapping(value = "/test/{contrNbr}",method = RequestMethod.POST)
	public String findImage(@PathVariable String contrNbr){
    	logger.info("come in");
    	logger.info(contrNbr);
    	String dbKey = "dcn01";
    	String res = testService.selectJudgeContrIsOverdueByContrNbr(dbKey,contrNbr);   	
		return res;
     
    }
}
