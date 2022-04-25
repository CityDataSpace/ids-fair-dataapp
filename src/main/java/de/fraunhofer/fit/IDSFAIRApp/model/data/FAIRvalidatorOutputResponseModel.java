package de.fraunhofer.fit.IDSFAIRApp.model.data;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name="OutputData", description="Example FAIRvalidator  response data")
public class FAIRvalidatorOutputResponseModel {

    //private int validationScore;
    private float validationScore;
    private String validationScoreDesc;
    private List<String> message;

    public float getValidationScore() {
        return validationScore;
    }

    public void setValidationScore(float validationScore) {
        this.validationScore = validationScore;
    }

    public String getValidationScoreDesc() {
        return validationScoreDesc;
    }

    public void setValidationScoreDesc(String validationScoreDesc) {
        this.validationScoreDesc = validationScoreDesc;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

  
}
