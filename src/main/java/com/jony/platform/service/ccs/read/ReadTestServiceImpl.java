package com.jony.platform.service.ccs.read;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.jony.platform.mapper.ccs.read.JudgeMapper;
import com.jony.platform.mapper.ccs.read.UserMapper;
import com.jony.platform.model.entity.User;
import com.jony.platform.utils.HttpClientResult;
import com.jony.platform.utils.HttpClientUtil;
import com.jony.platform.utils.JsonUtil;

@Service
public class ReadTestServiceImpl implements ReadTestService {

	private static final Logger logger = LoggerFactory.getLogger(ReadTestServiceImpl.class);
	
	@Value("${imageUrl1}")
	private String imageUrl1;
	@Autowired
	private JudgeMapper judgeMapper;
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public String test(String paramsReq) {
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
	@Transactional(propagation = Propagation.REQUIRES_NEW) 
	public String selectJudgeContrIsOverdueByContrNbr(String dbKey,String contrNbr) {
		// TODO Auto-generated method stub
		String res = judgeMapper.selectJudgeContrIsOverdueByContrNbr(contrNbr);
		return res;
	}

	@Override
	public List<User> selectUser(String dbKey,User user) {
		// TODO Auto-generated method stub
		return userMapper.selectUser(user);
	}
	

}
