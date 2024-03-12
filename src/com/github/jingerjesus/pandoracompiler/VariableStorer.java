package com.github.jingerjesus.pandoracompiler;

import java.util.HashMap;

public class VariableStorer {
    public static HashMap<String, Integer> GLOBAL_SCOPE = new HashMap<String, Integer>();

    public static String getGlobalScope() {
        String out = "";
        for (String key: GLOBAL_SCOPE.keySet()) {
            out += ("key : " + key + " \\\\ value : " + GLOBAL_SCOPE.get(key) + "\n");
        }
        return out;
    }
}
