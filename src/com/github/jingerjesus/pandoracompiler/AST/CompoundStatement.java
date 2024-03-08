package com.github.jingerjesus.pandoracompiler.AST;

public class CompoundStatement extends Node {
    @Override
    public Integer getValue() {
        Integer val = 0;
        val = getChildNodes()[0].getValue();
        return val;
    }

    @Override
    public String getAssembled() {
        return null;
    }


}
