package com.aanchal.coronavirusinvestigator.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryDetail {

	@JsonProperty("pattern")
	private String pattern;
	@JsonProperty("example")
	private String example;


	@JsonProperty("pattern")
	public String getPattern() {
	return pattern;
	}

	@JsonProperty("pattern")
	public void setPattern(String pattern) {
	this.pattern = pattern;
	}

	@JsonProperty("example")
	public String getExample() {
	return example;
	}

	@JsonProperty("example")
	public void setExample(String example) {
	this.example = example;
	}


	}
