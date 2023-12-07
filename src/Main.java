import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static File inFile;
    public static String preLexer = "";
    public static ArrayList<Token> postLexer;
    public static void main(String[] args) throws IOException {
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

        ArrayList<ArrayList<Token>> parsed = Parser.parse(postLexer);
        for (ArrayList<Token> line : parsed) {
            for (Token token : line) {
                System.out.print(token.name + " ");
            }
            System.out.println("\n\n");
        }

    }
}