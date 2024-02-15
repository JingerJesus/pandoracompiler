package com.github.jingerjesus.pandoracompiler.AST;

import java.util.Optional;

public class NodeVisitor {
    public static String visit(Node n) {
        if (n.getClass().equals(BinOp.class)) {
            return visitBinOp((BinOp) n);
        } else if (n.getClass().equals(Uint.class)) {
            return visitUint((Uint) n);
        } else throw new RuntimeException("Bad Node type.");
    }



    private static String visitBinOp(BinOp n) {
        Node left = n.getChildNodes()[0];
        Node right = n.getChildNodes()[1];
        String out = "";
        switch (n.opType) {
            case "ADD" -> {
                return "*" + visit(left) + " ADD " + visit(right);
            }
            case "SUB" -> {
                return "*" + visit(left) + " SUB " + visit(right);
            }
            case "MULTIPLY" -> {
                return "*" + visit(left) + " MULT " + visit(right);
            }
            case "DIVIDE" -> {
                return "*" + visit(left) + " DIV " + visit(right);
            }
            default -> {
                throw new RuntimeException("Bad BinOp operation type.");
            }

        }
    }

    private static String visitUint(Uint n) {
        return n.value;
    }
}
