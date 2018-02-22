package com.jony.platform.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.springframework.stereotype.Service;

@Service
@WebService
public interface UserService {

	@WebMethod
	String JudgeContrIsOverdueBycontractNo(@WebParam(name = "contrNbr") String contrNbr);

	@WebMethod
	String selectEarlyClearByContrNbr(@WebParam(name = "contrNbr") String contrNbr);
}