package com.github.jingerjesus.pandoracompiler.AST;

import com.github.jingerjesus.pandoracompiler.Tokens.Token;
import com.github.jingerjesus.pandoracompiler.VariableStorer;

public class Var extends Node{

    public Var(Token in) {
        value = in.value;
    }

    @Override
    public Integer getValue() {
        System.out.println("Hi!");
        return VariableStorer.GLOBAL_SCOPE.get(value);
    }

    @Override
    public String getAssembled() {
        return null;
    }
}
