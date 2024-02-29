package com.github.jingerjesus.pandoracompiler.AST;

import com.github.jingerjesus.pandoracompiler.Tokens.Token;

public class UnaOp extends Node{

    public String opType;

    public UnaOp(Token op, Node value) {
        setChildNodes(new Node[] {value});
        opType = op.value;
        this.value = String.valueOf(value.getValue());
    }

    @Override
    public Integer getValue() {
        if (opType == "SUB") {
            return (-Integer.valueOf(value));
        } else {
            return Integer.valueOf(value);
        }
    }

    @Override
    public String getAssembled() {
        return null;
    }
}
