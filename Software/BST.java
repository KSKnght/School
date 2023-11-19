import java.io.PrintWriter;

public class BST {
    protected BSTNode root;
    public BST() {
        root = null;
    }

    public Boolean search(String s){
        return search(root, s);
    }

    public Boolean search(BSTNode p, String el) { // Figure 6.9
        while (p != null)
        if (el.compareToIgnoreCase(p.key.getName()) == 0){
            return true;
        }
        else if (el.compareToIgnoreCase(p.key.getName()) < 0){
            p = p.left;
        }
        else if (el.compareToIgnoreCase(p.key.getName()) > 0) {
            p = p.right;
        }
        return false;
    }

    public Boolean searchVer(String el, String ver) { // Figure 6.9
        BSTNode p = root;
        while (p != null)
        if (el.compareToIgnoreCase(p.key.getName()) == 0){
            if (ver.compareToIgnoreCase(p.key.getVersion()) == 0)
                return true;
            else if (ver.compareToIgnoreCase(p.key.getVersion()) < 0){
                p = p.left;
            }
            else if (ver.compareToIgnoreCase(p.key.getVersion()) > 0) {
                p = p.right;
        }
        }
        else if (el.compareToIgnoreCase(p.key.getName()) < 0){
            p = p.left;
        }
        else if (el.compareToIgnoreCase(p.key.getName()) > 0) {
            p = p.right;
        }
        return false;
    }

    public void selected(String el, String ver, int q) { // Figure 6.9
        BSTNode p = root;
        while (p != null)
        if (el.compareToIgnoreCase(p.key.getName()) == 0){
            if (ver.equalsIgnoreCase(p.key.getVersion())) {
                System.out.printf("%-30s%-10s%-10s%-10s\n", p.key.getName(),p.key.getVersion(),p.key.getPrice(),q);
                break;
            }
            else if (ver.compareToIgnoreCase(p.key.getVersion()) < 0){
                p = p.left;
            }
            else if (ver.compareToIgnoreCase(p.key.getVersion()) > 0) {
                p = p.right;
            }
        }
        else if (el.compareToIgnoreCase(p.key.getName()) < 0){
            p = p.left;
        }
        else if (el.compareToIgnoreCase(p.key.getName()) > 0) {
            p = p.right;
        }
    }


    protected void addExistingSoftware(String elname, String ver, int q) { // Figure 6.9
        BSTNode p = root;
        while (p != null)
        if (elname.compareToIgnoreCase(p.key.getName()) == 0){
            if (ver.equalsIgnoreCase(p.key.getVersion())) {
                int qty = p.key.getQty();
                p.key.setQty(qty + q);
                break;
            }
        }
        else if (elname.compareToIgnoreCase(p.key.getName()) < 0){
            p = p.left;
        }
        else if (elname.compareToIgnoreCase(p.key.getName()) > 0) {
            p = p.right;
        }
    }

    protected void buyExistingSoftware(String el, String ver, int q) { // Figure 6.9
        BSTNode p = root;
        while (p != null)
        if (el.compareToIgnoreCase(p.key.getName()) == 0){
            if (ver.equalsIgnoreCase(p.key.getVersion())){
                int qty = p.key.getQty();
                p.key.setQty(qty - q);
                if (p.key.getQty() <= 0)
                    deleteByMerging(p.key);
                break;
            }
            else if (ver.compareToIgnoreCase(p.key.getVersion()) < 0){
                p = p.left;
            }
            else if (ver.compareToIgnoreCase(p.key.getVersion()) > 0) {
                p = p.right;
            }
        }
        else if (el.compareToIgnoreCase(p.key.getName()) < 0){
            p = p.left;
        }
        else if (el.compareToIgnoreCase(p.key.getName()) > 0) {
            p = p.right;
        }
    }

    public void insert(Software el) { // Figure 6.23
    BSTNode p = root, prev = null;
    while (p != null) { // find a place for inserting new node;
        prev = p;
        if (p.key.getName().compareToIgnoreCase(el.getName()) == 0) {
            if (p.key.getVersion().compareToIgnoreCase(el.getVersion()) < 0)
            p = p.right;
            else if (p.key.getVersion().compareToIgnoreCase(el.getVersion()) > 0)
            p = p.left;
        }
        else if (p.key.getName().compareToIgnoreCase(el.getName()) < 0)
            p = p.right;
        else p = p.left;
    }
    if (root == null) // tree is empty;
        root = new BSTNode(el);
    else if (prev.key.getName().compareToIgnoreCase(el.getName()) == 0){
        if (prev.key.getVersion().compareToIgnoreCase(el.getVersion()) < 0)
        prev.right = new BSTNode(el);
        else if (prev.key.getVersion().compareToIgnoreCase(el.getVersion()) > 0)
            prev.left = new BSTNode(el);
    }
    else if (prev.key.getName().compareToIgnoreCase(el.getName()) < 0)
        prev.right = new BSTNode(el);
    else prev.left = new BSTNode(el);
    }

    public void inorder() {
        inorder(root);
    }

    protected void inorder(BSTNode p) { // Figure 6.11
        if (p != null) {
            inorder(p.left);
            System.out.printf("%-30s%-10s%-10s%-10s\n", p.key.getName(), p.key.getVersion(), p.key.getQty(), p.key.getPrice());
            inorder(p.right);
        }
    }

    public void inorderPrint(PrintWriter pw) {
        inorderPrint(root, pw);
    }

    protected void inorderPrint(BSTNode p, PrintWriter pw) { // Figure 6.11
        if (p != null) {
            inorderPrint(p.left, pw);
            pw.println(p.key.getName());
            pw.println(p.key.getVersion());
            pw.println(p.key.getQty());
            pw.println(p.key.getPrice());
            inorderPrint(p.right, pw);
        }
    }

    public void deleteByMerging(Software el) {
        BSTNode tmp, node, p = root, prev = null;
        while (p != null && p.key != el) { // find the node p
            prev = p; // with element el;
            // if (p.key.getName().compareToIgnoreCase(el.getName()) < 0)
            //     p = p.right;
            // else p = p.left;
            if (p.key.getName().compareToIgnoreCase(el.getName()) == 0) {
            if (p.key.getVersion().compareToIgnoreCase(el.getVersion()) < 0)
                p = p.right;
            else if (p.key.getVersion().compareToIgnoreCase(el.getVersion()) > 0)
                p = p.left;
            }
            else if (p.key.getName().compareToIgnoreCase(el.getName()) < 0)
                p = p.right;
            else p = p.left;
        }
        node = p;
        if (p != null && p.key == el) {
            if (node.right == null) // node has no right child: its left
                node = node.left; // child (if any) is attached to its 
                // parent;
                else if (node.left == null) // node has no left child: its right
                    node = node.right; // child is attached to its parent;
                else { // be ready for merging subtrees;
                    tmp = node.left; // 1. move left
                while (tmp.right != null) // 2. and then right as far as
                    tmp = tmp.right; // possible;
                tmp.right = // 3. establish the link between
                node.right; // the rightmost node of the left
                // subtree and the right subtree;
                node = node.left; // 4.
            }
            if (p == root)
                root = node;
            else if (prev.left == p)
                prev.left = node;
            else prev.right = node; // 5.
        }
        else if (root != null)
            System.out.println("key " + el + " is not in the tree");
        else System.out.println("the tree is empty");
    }
}
