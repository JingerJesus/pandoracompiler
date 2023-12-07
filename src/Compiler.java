import java.util.ArrayList;

public class Compiler {
    public static String[] compileLine(ArrayList<ArrayList<Token>> program) {
        String out = "";
        String bin = "";





        for (int i = 0; i < program.size() - 1 /*Number of lines, not tokens. The last token should always be STOP, so we can guarantee a forward check of one token.*/; i ++) {
            ArrayList<Token> line = program.get(i);
            // need to look for a few things first.
            // If the line has tokens named SUBROUTINE, FLAG, HALT, or OPERATION, do different things accordingly.
            // Remember to also define variables. That is important.
            // FLAG and HALT should be the easiest. Should keep a list of flags and their respective line numbers.



            if (lineHasToken(line, TokenName.HALT)) {
                out += "HALT\n";
                bin += "111111 0000000000000000\n";
                // :]
            }
            if (lineHasToken(line, TokenName.FLAG)) {

            }
            if (lineHasToken(line, TokenName.SUBROUTINE)) {
                // subroutine declaration.
                // WHAT THE HELL DO I DO
            }
            if (lineHasToken(line, TokenName.UINT)) {
                // initialize a new variable at the current line.
                // Give it a name, then somehow figure out how to attribute the name back to this variable.
            }
            if (lineHasToken(line, TokenName.OPERATION)) { // This is the big one.
                //find first operation token, then check what it is.
                int opLocation = tokenAt(line, TokenName.OPERATION);
                Token opToken = line.get(opLocation);
//                boolean hasTokenBefore = false, hasTokenAfter = false;        // these may be important for error checking.
//                if (opLocation > 0) hasTokenBefore = true;
//                if (opLocation < line.size() - 1) hasTokenAfter = true;

                switch (opToken.value) {
                    case "ASSIGN":
                    case "ADD":
                    default:
                }


            }
        }

        return new String[]{out, bin};
    }

    private static int tokenAt(ArrayList<Token> line, TokenName name) {
        for (int i = 0; i < line.size(); i ++) {
            if (line.get(i).name == name) {
                return i;
            }
        }
        return -1;
    }
    private static boolean lineHasToken(ArrayList<Token> line, TokenName name) {
        for (Token token : line) {
            if (token.name == name) return true;
        }
        return false;
    }
}
