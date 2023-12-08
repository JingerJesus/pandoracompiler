package com.github.jingerjesus.pandoracompiler.AST;

import com.github.jingerjesus.pandoracompiler.Token;
import com.github.jingerjesus.pandoracompiler.TokenName;

public class BinOp extends Node {
    // a binary operation has three child nodes:
    // a LeftArg, an Operation, and a RightArg.
    // The nodes are defined left to right.
    // Arguments can be further binary operations, values, or whatever necessary.
    TokenName opName;

    public BinOp(Node left, Token op, Node right) {
        setChildNodes(new Node[]{left, right});
        opName = op.name;
    }
}
