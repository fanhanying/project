package com.tapestryTest.domain.base;


/** 
 * @author hanying.fan
 * @date 2017年7月24日 下午2:13:42 
 */
public interface AuditorAware {
	public static final String DEFAULT_AUDITOR = "SYSTEM";
	
	void audit(String auditor);

}
