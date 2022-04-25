package de.fraunhofer.fit.IDSFAIRApp.model.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FAIRHttpResponseModel {



	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	private String data;
	
}
