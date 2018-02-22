package com.jony.platform.service.mmt.read;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jony.platform.mapper.mmt.read.MmtUserMapper;
import com.jony.platform.model.entity.User;


@Service
public class MmtReadTestServiceImpl implements MmtReadTestService {

	private static final Logger logger = LoggerFactory.getLogger(MmtReadTestServiceImpl.class);
	
	@Autowired
	private MmtUserMapper userMapper;


	@Override
	public List<User> mmtSelectUser(String dbKey,User user) {
		// TODO Auto-generated method stub
		logger.info("come in mmt read");
		return userMapper.mmtSelectUser(user);
	}
	

}
