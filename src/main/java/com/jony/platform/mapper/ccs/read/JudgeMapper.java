package com.jony.platform.mapper.ccs.read;

import org.apache.ibatis.annotations.Param;

public interface JudgeMapper {

	String selectEarlyClearByContrNbr(@Param("contrNbr") String contrNbr,@Param("loanFeeDefId") String loanFeeDefId);

	String selectJudgeContrIsOverdueByContrNbr(@Param("contrNbr") String contrNbr);
	
	String selectLoanFeeDefIdByContrNbr(@Param("contrNbr") String contrNbr);

}
