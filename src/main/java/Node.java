public class Node {
    public NodeType nodeType;
    public int value;

    Node left;
    Node right;

    public Node(NodeType nodeType)
    {
        this.nodeType = nodeType;
        this.value = 0;

        left = null;
        right = null;
    }

    public Node(int value)
    {
        this.nodeType = NodeType.VALUE;
        this.value = value;

        left = null;
        right = null;
    }

    public static int EvaluateExprTree(Node root)
    {
        // do a post-order traversal
        int left = 0;
        if (root.left != null)
        {
            left = Node.EvaluateExprTree(root.left);
        }

        int right = 0;
        if (root.right != null)
        {
            right = Node.EvaluateExprTree(root.right);
        }

        switch (root.nodeType)
        {
            case ADD:
            {
                return left + right;
            }

            case SUB:
            {
                return left - right;
            }

            case MULT:
            {
                return left * right;
            }

            case DIV:
            {
                return left / right;
            }

            default:
            {
                return root.value;
            }
        }

    }
}
