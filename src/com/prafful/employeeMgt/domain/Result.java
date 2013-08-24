package com.prafful.employeeMgt.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="result")
public class Result {

	private String success;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	
}
