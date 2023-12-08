package com.github.jingerjesus.pandoracompiler;

public class Token {
    public TokenName name;
    public String value;

    public Token(TokenName n, String v) {
        name = n;
        value = v;
    }
    public String toString() {
        return name + " : " + value;
    }
}