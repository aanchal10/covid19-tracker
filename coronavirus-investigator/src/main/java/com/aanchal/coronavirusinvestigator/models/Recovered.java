package com.aanchal.coronavirusinvestigator.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Recovered {

	@JsonProperty("value")
	private Integer value;
	@JsonProperty("detail")
	private String detail;
	
	@JsonProperty("value")
	public Integer getValue() {
	return value;
	}

	@JsonProperty("value")
	public void setValue(Integer value) {
	this.value = value;
	}

	@JsonProperty("detail")
	public String getDetail() {
	return detail;
	}

	@JsonProperty("detail")
	public void setDetail(String detail) {
	this.detail = detail;
	}

	}
