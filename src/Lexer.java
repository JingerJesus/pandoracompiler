import java.util.ArrayList;
import java.util.Scanner;

public class Lexer {
    public static String input;

    public static final String[] reservedWords = new String[] {
            "uint", "int", "udouble", "double", "bool", "char", "+", "-", "*", "/", "end", "subroutine", "return", ";", "mode"
    };

    public static final char[] breakchars = new char[]{
            ' ', '[', ']', ';', '+', '-', '*', '/'
    };
    public static final ArrayList<String> words = new ArrayList<String>();

    public static ArrayList<Token> lex(String in) {
        ArrayList<Token> tokens = new ArrayList<>();

        input = in;
        Scanner sc = new Scanner(in);

        String line;

        while (sc.hasNextLine()) {
            line = sc.nextLine();
            String[] lines = line.split("\n"); //each individual line, separate comments and flags before "real" lexing
            for (int i = 0; i < lines.length; i ++) {
                if (lines[i].length() > 0) {

                    char firstChar = lines[i].toCharArray()[0];

                    while (firstChar == ' ' || firstChar == '\t') { //remove formatting
                        lines[i] = lines[i].substring(1);
                        firstChar = lines[i].toCharArray()[0];
                    }

                    //check for :flags or /comments

                    if (firstChar == ':') {
                        //flag
                        String name = getNextWord(lines[i], 1);
                        tokens.add(new Token(TokenName.FLAG, name));
                        //System.out.println("FLAG: " + name );                                                           //print here

                    } else if (firstChar == '/' && lines[i].toCharArray()[1] == '/') {
                        //return no token
                    } else {
                        //actual words

                        String remainingLine = lines[i];
                        int index = 0;

                        //get a list of words
                        while (remainingLine.length() > 0) {
                            remainingLine = remainingLine.trim();
                            String word = getNextWord(remainingLine, 0);
                            words.add(word);
                            //System.out.println(word);                                                                   //print here
                            index = word.length();
                            remainingLine = remainingLine.substring(index);
                        }
                    }
                } else {} // return no tokens
            }
        }

        for (int i = 0; i < words.size() - 1; i ++) {   // last word should always be a semicolon, guarantees a forward check of one word.
            String word = words.get(i);
            String firstChar = Character.toString(word.toCharArray()[0]);
            String nextWord = words.get(i + 1);
            String nextArg = "";
            TokenName name;
            switch (firstChar) {
                // keeping it in decimal for the sake of it
                    //ignore bases. idc. decimal based.
                case "%":   //I KNOW THEYRE REPETITIVE. SHUT UP.
                    //decimal to hex
                    name = TokenName.VALUE;
                    nextArg = word.substring(1);
                    break;
                case "$":
                    //leave as is
                    name = TokenName.VALUE;
                    nextArg = word.substring(1);
                    break;
                case "#":
                    //binary to hex
                    name = TokenName.VALUE;
                    nextArg = word.substring(1);
                    break;
                default:
                    switch (word) {
                        case "subroutine":
                            name = TokenName.SUBROUTINE;
                            nextArg = nextWord;
                            break;
                        case "uint":
                            name = TokenName.UINT;
                            nextArg = nextWord;
                            break;
                        case "int":
                            name = TokenName.INT;
                            nextArg = nextWord;
                            break;
                        case "udouble":
                            name = TokenName.UDOUBLE;
                            nextArg = nextWord;
                            break;
                        case "double":
                            name = TokenName.DOUBLE;
                            nextArg = nextWord;
                            break;
                        case "bool":
                            name = TokenName.BOOL;
                            nextArg = nextWord;
                            break;
                        case "char":
                            name = TokenName.CHAR;
                            nextArg = nextWord;
                            break;
                        case "[":
                            name = TokenName.OPENBRACKET;
                            break;
                        case "]":
                            name = TokenName.CLOSEBRACKET;
                            break;
                        case ";":
                            name = TokenName.STOP;
                            break;
                        case "end":
                            name = TokenName.END;
                            break;
                        case "return":
                            name = TokenName.RETURN;
                            nextArg = nextWord;
                            break;
                        case "jump":
                            name = TokenName.JUMP;
                            nextArg = nextWord;
                            break;
                        case "halt":
                            name = TokenName.HALT;
                            break;
                        case "=":
                            name = TokenName.OPERATION;
                            nextArg = "SUBTRACT";
                            break;
                        case "+":
                            name = TokenName.OPERATION;
                            nextArg = "ADD";
                            break;
                        case "/":
                            name = TokenName.OPERATION;
                            nextArg = "DIVIDE";
                            break;
                        case "*":
                            name = TokenName.OPERATION;
                            nextArg = "MULTIPLY";
                            break;
                        case "|":
                            name = TokenName.OPERATION;
                            nextArg = "OR";
                            break;
                        case "&:":
                            name = TokenName.OPERATION;
                            nextArg = "AND";
                            break;
                        case "!":
                            name = TokenName.OPERATION;
                            nextArg = "NOT";
                            break;
                        case "==":
                            name = TokenName.OPERATION;
                            nextArg = "EQUALTO";
                            break;
                        case ">":
                            name = TokenName.OPERATION;
                            nextArg = "GREATERTHAN";
                            break;
                        case "<":
                            name = TokenName.OPERATION;
                            nextArg = "LESSTHAN";
                            break;
                        case "jumpif":
                            name = TokenName.CONDJUMP;
                            nextArg = nextWord;
                            break;
                        case "true":
                            name = TokenName.CONDITION;
                            nextArg = "TRUE";
                            break;
                        case "false":
                            name = TokenName.CONDITION;
                            nextArg = "FALSE";
                            break;
                        default:    // assume token is a variable name.
                            name = TokenName.NAME;
                            nextArg = word;
                            break;
                    }
            }
            tokens.add(new Token(name, nextArg));
        }
        return tokens;
    }

    private static String getNextWord(String line, int index) {
        char[] charline = line.substring(index).toCharArray();


        String out = "";

        char firstChar = charline[0];

        for (int i = 0; i < breakchars.length; i ++) {
            if (firstChar == breakchars[i]) {
                out = Character.toString(firstChar);
                return out;
            }
        }

        for (int i = 0; i < charline.length; i ++) {
            for (int j = 0; j < breakchars.length; j ++) {
                if (charline[i] == breakchars[j]) {
                    return out;
                }
            }
            out = out + Character.toString(charline[i]);
        }
        return out;
    }
}