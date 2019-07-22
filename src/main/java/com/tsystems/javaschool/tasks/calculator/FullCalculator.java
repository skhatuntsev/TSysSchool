package com.tsystems.javaschool.tasks.calculator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FullCalculator {

    private TokenStack operatorStack;
    private TokenStack valueStack;
    private boolean error;
    private boolean wasInputDouble = false;

    public FullCalculator() {
        operatorStack = new TokenStack();
        valueStack = new TokenStack();
        error = false;
    }

    private void processOperator(Token t) {
        Token A = null, B = null;
        if (valueStack.isEmpty()) {
            error = true;
            return;
        } else {
            B = valueStack.top();
            valueStack.pop();
        }
        if (valueStack.isEmpty()) {
            error = true;
            return;
        } else {
            A = valueStack.top();
            valueStack.pop();
        }
        Token R = t.operate(A.getValue(), B.getValue());
        valueStack.push(R);
    }

    public String processInput(String input) {

        if (input == null) return null;
        if (input.isEmpty()) return null;
        Tokenizer tokenizer = new Tokenizer();
        ArrayList<String> arrayList = tokenizer.tokenizeSigns(input);

        for (String i : arrayList) {
            Pattern pppp = Pattern.compile("[.]");
            Matcher mmmm = pppp.matcher(i);
            if (mmmm.find()) wasInputDouble=true;
        }

        Token[] tokens = new Token[arrayList.size()];
        for (int n = 0; n < arrayList.size(); n++) {
            tokens[n] = new Token(arrayList.get(n));
        }


        for (int n = 0; n < tokens.length; n++) {
            Token nextToken = tokens[n];
            if (nextToken.getType() == Token.NUMBER) {
                valueStack.push(nextToken);
            } else if (nextToken.getType() == Token.OPERATOR) {
                if (operatorStack.isEmpty() || nextToken.getPrecedence() > operatorStack.top().getPrecedence()) {
                    operatorStack.push(nextToken);
                } else {
                    while (!operatorStack.isEmpty() && nextToken.getPrecedence() <= operatorStack.top().getPrecedence()) {
                        Token toProcess = operatorStack.top();
                        operatorStack.pop();
                        processOperator(toProcess);
                    }
                    operatorStack.push(nextToken);
                }
            } else if (nextToken.getType() == Token.LEFT_PARENTHESIS) {
                operatorStack.push(nextToken);
            } else if (nextToken.getType() == Token.RIGHT_PARENTHESIS) {
                while (!operatorStack.isEmpty() && operatorStack.top().getType() == Token.OPERATOR) {
                    Token toProcess = operatorStack.top();
                    operatorStack.pop();
                    processOperator(toProcess);
                }
                if (!operatorStack.isEmpty() && operatorStack.top().getType() == Token.LEFT_PARENTHESIS) {
                    operatorStack.pop();
                } else {
                    error = true;
                }
            }

        }

        while (!operatorStack.isEmpty() && operatorStack.top().getType() == Token.OPERATOR) {
            Token toProcess = operatorStack.top();
            operatorStack.pop();
            processOperator(toProcess);
        }

        if (error == false) {
            Token result = valueStack.top();
            valueStack.pop();
            if ((int)result.getValue() == Integer.MAX_VALUE) {
                return null;
            }
            if (!operatorStack.isEmpty() || !valueStack.isEmpty()) {
                return null;
            } else {
                return wasInputDouble ? Double.toString(result.getValue()) : Integer.toString((int)result.getValue());


            }
        }
        return null;
    }

}