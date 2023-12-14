package com.github.jingerjesus.pandoracompiler.AST;

import com.github.jingerjesus.pandoracompiler.Tokens.Token;
import com.github.jingerjesus.pandoracompiler.Tokens.TokenName;

public class Num extends Node {
    char base;
    String value;
    TokenName type;
    public Num(Token number) {
        base = number.value.charAt(0);
        value = number.value.substring(1);
        this.type = number.name;
    }


}
