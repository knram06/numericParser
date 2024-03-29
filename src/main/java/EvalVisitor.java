public class EvalVisitor extends ExprBaseVisitor<Integer> {
    @Override
    public Integer visitPrintExpr(ExprParser.PrintExprContext ctx)
    {
        Integer value = visit(ctx.expr());
        System.out.println(value);
        return 0;
    }

    @Override
    public Integer visitEofExpr(ExprParser.EofExprContext ctx) {
        Integer value = visit(ctx.expr());
        System.out.println(value);
        return 0;
    }

    @Override
    public Integer visitMulDiv(ExprParser.MulDivContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));

        if (ctx.op.getType() == ExprParser.MUL){
            return left * right;
        }

        // else must be DIV
        return left / right;
    }

    @Override
    public Integer visitAddSub(ExprParser.AddSubContext ctx) {

        // for op tree, you're now at an op node
        // so create a new optree node
        // scan children, if you see another op node, keep creating op node


        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));

        if (ctx.op.getType() == ExprParser.ADD){
            return left + right;
        }
        else if (ctx.op.getType() == ExprParser.MOD) {
            return left % right;
        }

        // else must be SUB
        return left - right;
    }

    @Override
    public Integer visitParens(ExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Integer visitInt(ExprParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }
}
