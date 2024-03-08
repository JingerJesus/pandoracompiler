package com.github.jingerjesus.pandoracompiler;

import com.github.jingerjesus.pandoracompiler.AST.Node;

import java.util.HashMap;
import java.util.Scanner;

public class Compiler {
    private static String assembled = "UINT 0\nUINT 1";
    private static String binary = "";

    private static HashMap<String, Integer> executionTime;

    public static String getAssembledFromAST(Node tree) {
        return tree.getAssembled();
    }

    public static String getBinary(String assembled) {
        return "";
    }

    public static void initHashmaps() {
        executionTime = new HashMap<String, Integer>();
        executionTime.put("NOP", 3);
        executionTime.put("LDA", 5);
        executionTime.put("LDB", 5);
        executionTime.put("LDC", 5);
        executionTime.put("LDAA", 4);
        executionTime.put("LDBA", 4);
        executionTime.put("LDCA", 4);
        executionTime.put("SWPAB", 6);
        executionTime.put("SWPAC", 6);
        executionTime.put("SWPBC", 6);
        executionTime.put("STORA", 5);
        executionTime.put("STORB", 5);
        executionTime.put("STORC", 5);
        executionTime.put("ADD", 4);
        executionTime.put("ADDN", 5);
        executionTime.put("SUB", 4);
        executionTime.put("SUBN", 5);
        executionTime.put("RDPTX", 5);
        executionTime.put("RDPTY", 5);
        executionTime.put("SDPTX", 5);
        executionTime.put("SDPTY", 5);
        executionTime.put("KEYIN", 5);
        executionTime.put("UINT", 3);
        executionTime.put("SIGND", 3);
        executionTime.put("UNDBL", 3);
        executionTime.put("SNDBL", 3);
        executionTime.put("BOOL", 3);
        executionTime.put("PTR", 3);
        executionTime.put("UNSPC", 3);
        executionTime.put("LDPTR", 7);
        executionTime.put("JMP", 4);
        executionTime.put("JMPZ", 4);
        executionTime.put("JMPNZ", 4);
        executionTime.put("JMPC", 4);
        executionTime.put("JMPNC", 4);
        executionTime.put("JMPSR", 6);
        executionTime.put("ENDSR", 5);
        executionTime.put("PUSH", 5);
        executionTime.put("POP", 6);
        executionTime.put("STRPR", 8);
        executionTime.put("STRNR", 8);
        executionTime.put("TYPIN", 3);
        executionTime.put("LDARP", 8);
        executionTime.put("LDARN", 8);
        executionTime.put("LDBRP", 8);
        executionTime.put("LDBRN", 8);
        executionTime.put("HALT", 1);

    }

    public static void determineRuntime(String assembly, int clockSpeedHertz) {
        //Currently misbehaving. DNI.
        int clockCycles = 0;
        double cycleTime = 1.0/clockSpeedHertz;

        Scanner sc = new Scanner(assembly);
        System.out.println(assembly);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println(line + "**");
            line = line.split(" ")[0];
            clockCycles += executionTime.get(line);
        }

        System.out.println(clockCycles);

    }
}
