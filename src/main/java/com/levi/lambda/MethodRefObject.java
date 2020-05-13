package com.levi.lambda;

public class MethodRefObject {
    public void printUpperCaseString(String str){
        System.out.println(str.toUpperCase());
    }

    public static void printChars(String str){
        for (char c : str.toCharArray()){
            System.out.println(c);
        }
    }
}
