package com.jony.platform.service.ccs.read;

import java.util.List;
import org.springframework.stereotype.Service;
import com.jony.platform.model.entity.User;



@Service
public interface ReadTestService {
	List<User> selectUser(String dbKey,User user);
	String test(String paramsReq);
	String selectJudgeContrIsOverdueByContrNbr(String dbKey,String contrNbr);
}
