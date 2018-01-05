package binary_tree;

/**
 * A node in a binary tree with a value and left and right children.
 */
class BinaryNode {

    /** The value in this node. */
    Object value;

    /** The root of the left subtree. */
    BinaryNode left;

    /** The root of the right subtree. */
    BinaryNode right;

    /**
     * A new node with value v and no left or right children.
     *
     * @param v
     */
    BinaryNode(Object v) {
        this.value = v;
    }
}

/**
 * A binary tree of nodes.
 */
public class BinaryTree {

    /** The root of the tree. */
    private BinaryNode root;

    /**
     * Add a node for value under the node containing parentValue. Create a left
     * child if createLeftChild is true; otherwise create a right child. If a
     * node already exists at that location, replace it.
     *
     * If parentValue is null, then make the root of the tree a new node with no
     * children.
     *
     * Precondition: parentValue is null, or exists as a value somewhere in this
     * tree.
     *
     * @param value
     *            the value to add
     * @param parentValue
     *            where to add the new node
     * @param createLeftChild
     *            whether to create a left child or a right child
     */
    public void addValue(Object value, Object parentValue, boolean createLeftChild) {

        if (parentValue == null) {
            this.root = new BinaryNode(value);
        } else {

            // The current location in this tree.
            BinaryNode parent = findParent(root, parentValue);

            if (createLeftChild) {
                parent.left = new BinaryNode(value);
            } else {
                parent.right = new BinaryNode(value);
            }
        }
    }

    /**
     * Return the node containing parentValue, or null if the value is not in
     * the subtree rooted at curr. Note that curr may be null.
     *
     * @param curr
     *            the root of the subtree being searched.
     * @param parentValue
     *            the value being searched for.
     * @return the node containing parentValue, or null if the value is not in
     *         the subtree rooted at curr.
     */
    private BinaryNode findParent(BinaryNode curr, Object parentValue) {
    
    // if current node doesn't exist(null):
    if(curr == null){
    	// return null.
    	return curr;
    	}
    // if current node exist:
    else{
    	// if current node's value is containing the parentValue:
    	if(curr.value == parentValue){
    		// return this current node.
    		return curr;
    		}
    	// if current node's value isn't containing the parentValue:
    	else{
    		// using recursion search the whole branch of the left-subtree.
			BinaryNode inLeftpath = findParent(curr.left, parentValue);
			// using recursion search the whole branch of the right-subtree.
			BinaryNode inRightpath = findParent(curr.right, parentValue);
			/* if the search result in left-subtree is not empty, 
			 * i.e. there is a node in left-sub tree containing parentValue. */
			if (inLeftpath != null){
				// return the search result, which is the node as desire.
				return inLeftpath;
				}
			/* if the search result in right-subtree is not empty, 
			 * i.e. there is a node in right-sub tree containing parentValue. */
			if (inRightpath !=null){
				// return the search result, which is the node as desire.
				return inRightpath;
				}
			}
    	}
    // if there is no node containing in both left-subtree and right-subtree, return null.
    return null;
    }

    /**
     * Return a String representation of this tree of this form:
     *
     * "(rootvalue (left subtree) (right subtree))"
     *
     * or return "()" if the tree is empty. Empty subtrees should also be
     * represented as "()" — note that this means that all leaves will be
     * represented as "(value () ())".
     *
     * @return a String representation of this tree
     */
    public String toString() {
        if (this.root == null) {
            return "()";
        } else {
            return toString(this.root);
        }
    }

    /**
     * Return a String representation of the subtree rooted at curr of this
     * form:
     *
     * "(curr-value (left subtree) (right subtree))"
     *
     * or return "()" if the tree is empty. Empty subtrees should also be
     * represented as "()" — note that this means that all leaves will be
     * represented as "(curr-value () ())".
     *
     * @return a String representation of this tree
     */
    private String toString(BinaryNode curr) {
    	// if tree is empty:
    	if (curr == null){
    		// return "()".
    		return "(" + ")";
    	// if tree is not empty:
    	}else{
    		// if current node is a leaf(no child):
    		if(curr.left == null && curr.right == null){
    			// return current node's value, in form "(curr-value () ())".
    			return "(" + curr.value + " " + "(" + ")"+ " " + "(" + ")" + ")";
    		// if current node is not a lead(has child(ren)):
    		}else{
    			/* return the String representation of the subtree rooted at curr of the form of 
    			 * "(curr-value (left subtree) (right subtree))" */
    			return "(" + curr.value + " " + toString(curr.left) + " " + toString(curr.right) + ")";
    							}
    			}
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        System.out.println(tree);
        tree.addValue("A", null, false);
        System.out.println(tree);
        tree.addValue(null, "A", false);
        System.out.println(tree);
        tree.addValue("C", null, false);
        System.out.println(tree);
        tree.addValue("D",null, false);
        System.out.println(tree);
    }
}
