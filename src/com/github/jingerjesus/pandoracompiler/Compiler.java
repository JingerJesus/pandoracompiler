package com.github.jingerjesus.pandoracompiler;

import com.github.jingerjesus.pandoracompiler.AST.Node;

public class Compiler {
    private static String assembled = "UINT 0\nUINT 1";
    private static String binary = "";

    public static String getAssembledFromAST(Node tree) {
        return tree.getAssembled();
    }

    public static String getBinary(String assembled) {
        return "";
    }

}
