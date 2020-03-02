package com.example.game_.other01_app.AssistanceClasses;

public abstract class StringsAssist {

    public static int concatInts(int intOne, int intTwo, int intThree){
        String concatInts = intOne + "" + intTwo + "" + intThree;
        return Integer.valueOf(concatInts);
    }

}
