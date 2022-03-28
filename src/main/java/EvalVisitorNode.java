public class EvalVisitorNode extends ExprBaseVisitor<Node> {
    public Node root;

    @Override
    public Node visitPrintExpr(ExprParser.PrintExprContext ctx)
    {
        Node node = visit(ctx.expr());
        // System.out.println(Node.EvaluateExprTree(node));

        root = node;
        return node;
    }

    @Override
    public Node visitEofExpr(ExprParser.EofExprContext ctx) {
        Node node = visit(ctx.expr());
        // System.out.println(Node.EvaluateExprTree(node));

        root = node;
        return node;
    }

    @Override
    public Node visitMulDiv(ExprParser.MulDivContext ctx) {
        Node left = visit(ctx.expr(0));
        Node right = visit(ctx.expr(1));

        Node ret = new Node(0);
        ret.left = left;
        ret.right = right;

        if (ctx.op.getType() == ExprParser.MUL)
        {
            ret.nodeType = NodeType.MULT;
            ret.value = left.value * right.value;
        }
        else
        {
            ret.nodeType = NodeType.DIV;
            ret.value = left.value / right.value;
        }
        return ret;
    }

    @Override
    public Node visitAddSub(ExprParser.AddSubContext ctx)
    {
        Node left = visit(ctx.expr(0));
        Node right = visit(ctx.expr(1));

        Node ret = new Node(0);
        ret.left = left;
        ret.right = right;

        if (ctx.op.getType() == ExprParser.ADD)
        {
            ret.nodeType = NodeType.ADD;
            ret.value = left.value + right.value;
        }
        // else if (ctx.op.getType() == ExprParser.MOD) {
        //    return left % right;
        // }
        else
        {
            ret.nodeType = NodeType.SUB;
            ret.value = left.value - right.value;
        }

        return ret;
    }

    @Override
    public Node visitParens(ExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Node visitInt(ExprParser.IntContext ctx) {
        int value = Integer.parseInt(ctx.INT().getText());
        return new Node(value);
    }
}
