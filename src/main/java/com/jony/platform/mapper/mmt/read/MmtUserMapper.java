package com.jony.platform.mapper.mmt.read;

import java.util.List;
import com.jony.platform.model.entity.User;


public interface MmtUserMapper {

	public List<User> mmtSelectUser(User user);

}
