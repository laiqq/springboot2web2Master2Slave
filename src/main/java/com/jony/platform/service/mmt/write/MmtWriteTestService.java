package com.jony.platform.service.mmt.write;

import org.springframework.stereotype.Service;
import com.jony.platform.model.entity.Order;

@Service
public interface MmtWriteTestService {
	int mmtCreateOrder(String dbKey,Order order);
}
