package com.github.jingerjesus.pandoracompiler.AST;

public class NoOp extends Node {

    public NoOp() {}

    @Override
    public Integer getValue() {
        return 0;
    }

    @Override
    public String getAssembled() {
        return "";
    }
}
