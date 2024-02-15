package com.github.jingerjesus.pandoracompiler.AST;

import com.github.jingerjesus.pandoracompiler.Tokens.Token;
import com.github.jingerjesus.pandoracompiler.Tokens.TokenName;

public class BinOp extends Node {
    // a binary operation has three child nodes:
    // a LeftArg, an Operation, and a RightArg.
    // The nodes are defined left to right.
    // Arguments can be further binary operations, values, or whatever necessary.
    public TokenName opName;
    public String opType;


    public BinOp(Node left, Token op, Node right) {
        setChildNodes(new Node[]{left, right});
        opName = op.name;
        opType = op.value;
        value = left.value + " " + opName + " " + right.value;
    }

    @Override
    public Integer getValue() {
        Integer l, r;
        l = getChildNodes()[0].getValue();
        r = getChildNodes()[1].getValue();
        //
        switch (opType) {
            case "ADD" -> {return l + r;}
            case "SUB" -> {return l - r;}
            case "MULTIPLY" -> {return l * r;}
            case "DIVIDE" -> {return l / r;}
            default -> {throw new RuntimeException("Bad OpType.");}
        }
    }

    @Override
    public String getAssembled() {
        String out = "";

        out += getChildNodes()[0].getAssembled();
        out += "\nLDARN 1";
        out += getChildNodes()[1].getAssembled();
        out += "\nLDBRN 1";

        switch (opType) {
            case "ADD" -> {
                out += "\nADD 0";
            }
            case "SUB" -> {
                out += "\nSUB 0";
            }
        }

        out += "\nSTRCR 1\nUINT 0";

        return out;
    }
}
