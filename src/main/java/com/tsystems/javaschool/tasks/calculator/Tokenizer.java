package com.tsystems.javaschool.tasks.calculator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    public ArrayList<String> tokenizeSigns(String input) {
        ArrayList<String> arrayListOfTokens = new ArrayList<String>();
        Pattern pp = Pattern.compile("\\d*\\.?\\d+|[+-/*()]");
        Matcher mm = pp.matcher(input);

        while (mm.find()) {
            arrayListOfTokens.add(mm.group());
        }
        return arrayListOfTokens;

    }
}