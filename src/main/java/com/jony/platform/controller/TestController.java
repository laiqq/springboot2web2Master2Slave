package com.jony.platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jony.platform.config.DataSourceContextHolder;
import com.jony.platform.model.entity.Order;
import com.jony.platform.model.entity.User;
import com.jony.platform.model.res.TestRes;
import com.jony.platform.service.ccs.read.ReadTestService;
import com.jony.platform.service.ccs.write.WriteTestService;
import com.jony.platform.service.mmt.read.MmtReadTestService;
import com.jony.platform.service.mmt.write.MmtWriteTestServiceImpl;



@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private WriteTestService writeTestService;
    @Autowired
    private ReadTestService readTestService;
    //mmt核心 读写测试 start
    @Autowired
    private MmtWriteTestServiceImpl mmtWriteTestService;
    @Autowired
    private MmtReadTestService mmtReadTestService;

    @RequestMapping(value = "/user/select",method = RequestMethod.POST)
    @ResponseBody
    public TestRes user(@RequestBody User user){
    	TestRes res = new TestRes();
    	String dbKey = "dcn01";
    	DataSourceContextHolder.setDbkey(dbKey);//设置获取当前线程变量作为AOP拦截
    	List<User> list = readTestService.selectUser(dbKey,user);
    	res.setList(list);
        return res;
    }
    
    @ResponseBody
    @RequestMapping(value = "/order",method = RequestMethod.POST)
	public TestRes order(@RequestBody Order order){
    	String dbKey = "dcn02";
    	TestRes res = new TestRes();
    	DataSourceContextHolder.setDbkey(dbKey);//设置获取当前线程变量作为AOP拦截
    	int successSize = writeTestService.createOrder(dbKey,order);
    	res.setSuccessSize(successSize);
    	return res;
    }
    
   

    @RequestMapping(value = "/mmtuser/select",method = RequestMethod.POST)
    @ResponseBody
    public TestRes mmtuser(@RequestBody User user){
    	TestRes res = new TestRes();
    	String dbKey = "dcn01";
    	DataSourceContextHolder.setDbkey(dbKey);//设置获取当前线程变量作为AOP拦截
    	List<User> list = mmtReadTestService.mmtSelectUser(dbKey,user);
    	res.setList(list);
        return res;
    }
    
    @ResponseBody
    @RequestMapping(value = "/mmtorder",method = RequestMethod.POST)
	public TestRes mmtorder(@RequestBody Order order){
    	String dbKey = "dcn02";
    	TestRes res = new TestRes();
    	DataSourceContextHolder.setDbkey(dbKey);//设置获取当前线程变量作为AOP拦截
    	int successSize = mmtWriteTestService.mmtCreateOrder(dbKey,order);
    	res.setSuccessSize(successSize);
    	return res;
    }
    
    @RequestMapping(value="/selectUser/{contrNbr}/{dcn}", method = RequestMethod.POST)
    @ResponseBody
    public String selectUser(@PathVariable String contrNbr,@PathVariable String dcn){
    	String dbKey = dcn;    	
    	DataSourceContextHolder.setDbkey(dbKey);//设置获取当前线程变量作为AOP拦截
    	String res = readTestService.selectJudgeContrIsOverdueByContrNbr(dbKey,contrNbr);
    	
        return res+"&dcn="+dcn;
    }

}
