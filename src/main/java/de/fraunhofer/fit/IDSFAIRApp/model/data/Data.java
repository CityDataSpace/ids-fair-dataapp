package de.fraunhofer.fit.IDSFAIRApp.model.data;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name="Data", description="Example person data for demonstration purposes")
public class Data {

    String first_name;

    String last_name;

    String email;

    public Data(String first_name, String last_name, String email){
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
