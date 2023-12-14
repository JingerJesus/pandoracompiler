package com.github.jingerjesus.pandoracompiler;

import com.github.jingerjesus.pandoracompiler.AST.BinOp;
import com.github.jingerjesus.pandoracompiler.AST.Node;
import com.github.jingerjesus.pandoracompiler.AST.Num;
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
        } else error();
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
        if (t.name == TokenName.DECVALUE) {
            eat(TokenName.DECVALUE);
            return new Num(t);
        } else if (t.name == TokenName.OPENBRACKET) {
            eat(TokenName.OPENBRACKET);
            Node n = expr();
                eat (TokenName.CLOSEBRACKET);
            return n;
        }
        error();
        return null;
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
    public static Node parseLine() {
        return expr();
    }


}
