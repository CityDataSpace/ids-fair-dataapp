package de.fraunhofer.fit.IDSFAIRApp.model.data;

public class FAIRresponseModel {
	
	private boolean isValid;
	private String message;
	
	public boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
