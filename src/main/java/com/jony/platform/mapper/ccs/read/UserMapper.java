package com.jony.platform.mapper.ccs.read;

import java.util.List;
import com.jony.platform.model.entity.User;


public interface UserMapper {

	public List<User> selectUser(User user);

}
