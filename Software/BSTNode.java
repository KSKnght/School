public class BSTNode {
    protected Software key;
    protected BSTNode left, right;
    public BSTNode() {
        left = right = null;
    }
    public BSTNode(Software el) {
        this(el,null,null);
    }
    public BSTNode(Software el, BSTNode lt, BSTNode rt) {
        key = el; left = lt; right = rt;
    }
}
