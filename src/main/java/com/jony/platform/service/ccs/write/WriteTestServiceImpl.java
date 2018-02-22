package com.jony.platform.service.ccs.write;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jony.platform.mapper.ccs.write.OrderMapper;
import com.jony.platform.model.entity.Order;

@Service
public class WriteTestServiceImpl implements WriteTestService {

	private static final Logger logger = LoggerFactory.getLogger(WriteTestServiceImpl.class);
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public int createOrder(String dbKey,Order order) {
		logger.info("come in ");
		// TODO Auto-generated method stub
		int count = orderMapper.createOrder(order);
		return count;
	}
	
	
	

}
