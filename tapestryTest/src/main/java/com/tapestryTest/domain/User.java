package com.tapestryTest.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tapestryTest.domain.base.AuditableObject;

/**
 * @author hanying.fan
 * @date 2017年7月24日 下午3:16:29
 */
@Entity
@Table(name = "my_user")
public class User extends AuditableObject {
	
	private static final long serialVersionUID = 1L;

	private String code;

	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
