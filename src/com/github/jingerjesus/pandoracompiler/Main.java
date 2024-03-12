package com.github.jingerjesus.pandoracompiler;

import com.github.jingerjesus.pandoracompiler.AST.Node;
import com.github.jingerjesus.pandoracompiler.AST.NodeVisitor;
import com.github.jingerjesus.pandoracompiler.Tokens.Token;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static File inFile;
    public static String preLexer = "";
    public static ArrayList<Token> postLexer;
    public static void main(String[] args) throws IOException {

        Compiler.initHashmaps();

        inFile = new File(args[0]);

        Scanner sc = new Scanner(inFile);

        while (sc.hasNext()) {
            preLexer = preLexer + "\n" + sc.nextLine();
        }

        System.out.println(preLexer + "\n\n");
        postLexer = Lexer.lex(preLexer);

        //debug & validation
        for (Token token : postLexer) {
            System.out.println(token);
        }

        ArrayList<ArrayList<Token>> parsed = Parser.breakIntoLines(postLexer);
        for (ArrayList<Token> line : parsed) {
            for (Token token : line) {
                System.out.print(token.name + " ");
            }
            System.out.println("\n\n");
        }

        Parser.init(postLexer);
        Node tree = Parser.parse();
        //System.out.println(tree);

        System.out.println("\n\n" + NodeVisitor.visit(tree));

        System.out.println("GLOBAL SCOPE: ");
        System.out.println(VariableStorer.getGlobalScope());
        System.out.println("END GLOBAL SCOPE");

        //System.out.println(tree.getValue().intValue());

        System.out.println("\n\n" + Compiler.getAssembledFromAST(tree));

        //Compiler.determineRuntime(Compiler.getAssembledFromAST(tree), 1000);



    }
}