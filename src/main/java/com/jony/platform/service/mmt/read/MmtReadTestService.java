package com.jony.platform.service.mmt.read;

import java.util.List;
import org.springframework.stereotype.Service;
import com.jony.platform.model.entity.User;

@Service
public interface MmtReadTestService {
	List<User> mmtSelectUser(String dbKey,User user);

}
