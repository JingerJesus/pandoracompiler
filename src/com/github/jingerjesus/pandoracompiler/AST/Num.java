package com.github.jingerjesus.pandoracompiler.AST;

import com.github.jingerjesus.pandoracompiler.Token;
import com.github.jingerjesus.pandoracompiler.TokenName;

public class Num extends Node {
    char base;
    String value;
    TokenName type;
    public Num(Token number, Token type) {
        base = number.value.charAt(0);
        value = number.value.substring(1);
        this.type = type.name;
    }

}
