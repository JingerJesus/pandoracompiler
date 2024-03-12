package com.github.jingerjesus.pandoracompiler;

import com.github.jingerjesus.pandoracompiler.AST.*;
import com.github.jingerjesus.pandoracompiler.Tokens.Token;
import com.github.jingerjesus.pandoracompiler.Tokens.TokenName;

import java.util.ArrayList;

public class Parser {
    private static String out = "";

    private static ArrayList<ArrayList<Token>> lines = new ArrayList<ArrayList<Token>>();
    private static ArrayList<Token> tokens;
    private static Token currentToken;

    public static void init(ArrayList<Token> in) {
        tokens = in;
        currentToken = getNextToken();
    }

    public static ArrayList<ArrayList<Token>> breakIntoLines(ArrayList<Token> tokens) {
        ArrayList<Token> currentLine = new ArrayList<Token>();
        for (Token token : tokens) {
            currentLine.add(token);
            if (token.name == TokenName.STOP || token.name == TokenName.FLAG) {
                lines.add(currentLine);
                currentLine = new ArrayList<Token>();
            }
        }
        return lines;
    }

    private static void error() {
        System.out.println("Parse error. God help you.");
    }

    private static void eat(TokenName type) {
        System.out.println("Attempting to eat token " + type);
        if (currentToken.name == type) {
            currentToken = getNextToken();
        } else {
            System.out.println("Ate incorrect token, tried to eat " + type + ", failed to eat " + currentToken.name);
            error();
        }
    }

    private static Token getNextToken() {
        if (tokens.size() > 0) {
            Token get = tokens.get(0);
            tokens.remove(0);
            return get;
        }
        return new Token(TokenName.STOP, "EOF");
    }

    private static Node factor() {
        Token t = currentToken;
        if (t.name == TokenName.OPERATION && t.value == "ADD") {
            eat(TokenName.OPERATION);
            return new UnaOp(t, factor());
        }
        if (t.name == TokenName.OPERATION && t.value == "SUB") {
            eat(TokenName.OPERATION);
            return new UnaOp(t, factor());
        }
        if (t.name == TokenName.DECVALUE) {
            eat(TokenName.DECVALUE);
            return new Uint(t);
        } else if (t.name == TokenName.OPENBRACKET) {
            eat(TokenName.OPENBRACKET);
            Node n = expr();
                eat (TokenName.CLOSEBRACKET);
            return n;
        } else {
            Node n = variable();
            return n;
        }
    }

    private static Node term() {
        Node n = factor();
        while (currentToken.value == "MULTIPLY" || currentToken.value == "DIVIDE") {
            Token t = currentToken;
            if (t.value == "MULTIPLY" || t.value == "DIVIDE") {
                eat(TokenName.OPERATION);
            }
            n = new BinOp(n, t, factor());
        }
        return n;
    }

    private static Node expr() {
        Node n = term();
        while (currentToken.value == "ADD" || currentToken.value == "SUB") {
            Token t = currentToken;
            if (t.value == "ADD" || t.value == "SUB") {
                eat(TokenName.OPERATION);
            }
            n = new BinOp(n, t, term());
        }
        return n;
    }

    private static Node program() {
        Node n = compoundStatement();
        eat(TokenName.HALT);
        return n;
    }

    private static Node compoundStatement() {
        Node[] ns = statementList();
        Node root = new CompoundStatement();
        root.setChildNodes(ns);
        return root;
    }

    private static Node[] statementList() {
        Node n = statement();
        ArrayList<Node> results = new ArrayList<Node>();
        results.add(n);

        while (currentToken.name.equals(TokenName.STOP)) {
            eat(TokenName.STOP);
            results.add(statement());
        }
        if (currentToken.name.equals(TokenName.VARNAME)) {
            System.out.println("Found VARNAME token as last token in StatementList.");
            error();
        }

        System.out.println("Statement count: " + results.size());
        return results.toArray(new Node[results.size()]);
    }

    private static Node statement() {
        Node n;
        if (currentToken.name.equals(TokenName.PROGRAMSTART)) {
            eat(TokenName.PROGRAMSTART);
            n = compoundStatement();

        } else if (currentToken.name.equals(TokenName.UINT)) {
            n = assignmentStatement();
        } else {
            n = empty();
        }
        return n;
    }

    private static Node assignmentStatement() {
        eat(getVarType(currentToken));
        Node ln = variable();
        Token tk = currentToken;
        eat(TokenName.OPERATION);
        Node rn = expr();
        Node n = new AssignOp(ln, tk, rn);
        return n;
    }

    private static Node variable() {
        Node n = new Var(currentToken);
        eat(TokenName.VARNAME);
        return n;
    }

    private static TokenName getVarType(Token in) {
        switch (in.name) {
            case UINT -> {return TokenName.UINT;}
            default -> {
                System.out.println("calling getVarType failed, var type " + in.name + " not included.");
                error();
                return TokenName.UNKNOWN;
            }
        }
    }

    private static Node empty() {
        return new NoOp();
    }

    public static Node parse() {
        Node n = program();
        if (currentToken.name != TokenName.STOP) {
            System.out.println("No HALT instruction.");
            error();
        }
        return n;
    }


}
