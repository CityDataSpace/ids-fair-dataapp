package de.fraunhofer.fit.IDSFAIRApp.model.data;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;

import de.fraunhofer.fit.IDSFAIRApp.annotations.FAIRvalidatorInputEndpoint;

public final class FAIRdataStore {

    public static FAIRvalidatorInputModel exampleData = new FAIRvalidatorInputModel();


    public static FAIRvalidatorInputModel getExampleData(){
        return exampleData;
    }



    public static void setExampleData(FAIRvalidatorInputModel data){
        exampleData=data;
    }
}

