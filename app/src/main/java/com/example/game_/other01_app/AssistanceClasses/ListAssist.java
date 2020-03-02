package com.example.game_.other01_app.AssistanceClasses;

import java.util.Arrays;
import java.util.List;

public abstract class ListAssist {

    public static List<String> convertStringToListOf(String listString){
        return Arrays.asList(listString.split("\\s*,\\s*"));
    }

    public static String convertListToString(List<String> stringList){
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < stringList.size(); i++){
            listString.append(stringList.get(i));
            if(i != stringList.size()-1){
                listString.append(",");
            }
        }
        return String.valueOf(listString);
    }

    public static List<String> convertInstructionsToListOfStrings(String instructions){
        return Arrays.asList(instructions.split("\\s*\n\\s*"));
    }
}
