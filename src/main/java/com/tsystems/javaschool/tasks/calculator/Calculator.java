package com.tsystems.javaschool.tasks.calculator;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class Calculator {
   
    public String evaluate(String statement) {



        // TODO: Implement the logic here
        FullCalculator calc = new FullCalculator();
        return calc.processInput(statement);




    }

}

