package com.jony.platform.service.mmt.write;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jony.platform.mapper.mmt.write.MmtOrderMapper;
import com.jony.platform.model.entity.Order;

@Service
public class MmtWriteTestServiceImpl implements MmtWriteTestService {

	private static final Logger logger = LoggerFactory.getLogger(MmtWriteTestServiceImpl.class);
	
	@Autowired
	private MmtOrderMapper mmtOrderMapper;
	
	@Override
	public int mmtCreateOrder(String dbKey,Order order) {
		logger.info("come in ");
		// TODO Auto-generated method stub
		int count = mmtOrderMapper.mmtCreateOrder(order);
		return count;
	}
	
	
	

}
