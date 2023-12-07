import java.util.ArrayList;

public class Parser {
    private static String out = "";

    private static ArrayList<ArrayList<Token>> lines = new ArrayList<ArrayList<Token>>();

    public static ArrayList<ArrayList<Token>> parse(ArrayList<Token> tokens) {
        ArrayList<Token> currentLine = new ArrayList<Token>();
        for (Token token : tokens) {
            currentLine.add(token);
            if (token.name == TokenName.STOP || token.name == TokenName.FLAG || token.name == TokenName.HALT) {
                lines.add(currentLine);
                currentLine = new ArrayList<Token>();
            }
        }
        return lines;
    }

}
