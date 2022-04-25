package de.fraunhofer.fit.IDSFAIRApp.model.data;

import java.util.ArrayList;
import java.util.Arrays;

public final class ExampleData {

    public static ArrayList<Data> exampleData = new ArrayList<>(
            Arrays.asList(
                    new Data("Dominic","Reuter","dominic.reuter@fit.fraunhofer.de"),
                    new Data("Artur","Khromov","artur.khromov@fit.fraunhofer.de"),
                    new Data("Christoph","Quix","christoph.quix@fit.fraunhofer.de")
            ));

    public static ArrayList<Data> getExampleData(){
        return exampleData;
    }

    public static Data getExampleData(int index){
        return exampleData.get(index);
    }

    public static void setExampleData(Data data){
        exampleData.add(data);
    }
}

