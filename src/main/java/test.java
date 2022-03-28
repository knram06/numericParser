import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class test {
    public static void main( String[] args) {
        // ExprLexer lexer = new ExprLexer(CharStreams.fromString("((5*3) + 4-(9%5))/6"));

        ExprLexer lexer = new ExprLexer(CharStreams.fromString("(5+(3*2))/2"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);
        ParseTree tree = parser.prog();

        EvalVisitorNode eval = new EvalVisitorNode();
        eval.visit(tree);

        System.out.println("Evaluated expression value: " + Node.EvaluateExprTree(eval.root));
    }
}
