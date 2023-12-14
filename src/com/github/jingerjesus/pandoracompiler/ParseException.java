package com.github.jingerjesus.pandoracompiler;

public class ParseException extends Exception {
    public ParseException(String statement) {
        super(statement);
    }
}
