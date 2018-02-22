package com.jony.platform.service.ccs.write;

import org.springframework.stereotype.Service;
import com.jony.platform.model.entity.Order;

@Service
public interface WriteTestService {
	int createOrder(String dbKey,Order order);
}
