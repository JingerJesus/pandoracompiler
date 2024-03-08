package com.github.jingerjesus.pandoracompiler.AST;

import com.github.jingerjesus.pandoracompiler.VariableStorer;

import java.util.Optional;

public class NodeVisitor {
    public static String visit(Node n) {
        if (n.getClass().equals(BinOp.class)) {
            return visitBinOp((BinOp) n);
        } else if (n.getClass().equals(Uint.class)) {
            return visitUint((Uint) n);
        } else if (n.getClass().equals(UnaOp.class)) {
            return visitUnaOp((UnaOp) n);
        } else if (n.getClass().equals(CompoundStatement.class)) {
            return visitCompoundStatement((CompoundStatement) n);
        } else if (n.getClass().equals(NoOp.class)) {
            return "";
        } else if (n.getClass().equals(Var.class)) {
            return visitVar((Var) n);
        } else if (n.getClass().equals(AssignOp.class)) {
            return visitAssign((AssignOp) n);
        } else throw new RuntimeException("Bad Node type.");
    }

    private static String visitVar(Var n) {
        String name = n.value;
        VariableStorer.GLOBAL_SCOPE.put(name, n.getValue());
        return "";
    }

    private static String visitAssign(AssignOp n) {
        //
        return "";
    }

    private static String visitCompoundStatement(CompoundStatement n) {
        String out = "";
        for (Node c : n.getChildNodes()) {
            out += visit(c);
        }
        return out;
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

    private static String visitUnaOp(UnaOp n) {
        return n.getValue().toString();
    }
}
