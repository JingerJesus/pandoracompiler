package com.github.jingerjesus.pandoracompiler.AST;

import com.github.jingerjesus.pandoracompiler.Tokens.Token;
import com.github.jingerjesus.pandoracompiler.Tokens.TokenName;

public class Uint extends Node {
    char base;

    TokenName type;
    public Uint(Token number) {
        //base = number.value.charAt(0);
        value =  "" + number.value;
        System.out.println(value);
        this.type = number.name;
    }

    @Override
    public Integer getValue() {
        return Integer.valueOf(value);
    }
    public String getAssembled() {
        return "\nUINT " + value;
    }
}
