package com.github.jingerjesus.pandoracompiler.AST;

import com.github.jingerjesus.pandoracompiler.Tokens.Token;
import com.github.jingerjesus.pandoracompiler.Tokens.TokenName;

public class AssignOp extends Node{

    public TokenName opType;
    public String op;

    public AssignOp(Node left, Token oper, Node right) {
        setChildNodes(new Node[]{left, right});
        opType = oper.name;
        op = oper.value;
    }

    @Override
    public Integer getValue() {

        return getChildNodes()[1].getValue();
    }

    @Override
    public String getAssembled() {
        return null;
    }
}
