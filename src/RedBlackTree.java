public class RedBlackTree<K, V> {
    RBTNode<Integer, String> root;
    int size;

    public void add(Integer key, String value) {
        RBTNode<Integer, String> node = new RBTNode<Integer, String>(key, value);
        if(root == null) {
            root = node;
            root.black = true;
            size++;
            return;
        }
        add(root, node);
        size++;
    }

    private void add(RBTNode<Integer, String> parent, RBTNode<Integer, String> newNode) {
        if(newNode.key.compareTo(parent.key) > 0) {
            if(parent.right == null) {
                parent.right = newNode;
                newNode.parent = parent;
                newNode.isLeftChild = false;
                size++;
                return;
            }
            add(parent.right, newNode);
        } 
        if(parent.left == null) {
            parent.left = newNode;
            newNode.parent = parent;
            newNode.isLeftChild = true;
            return;
        }
        add(parent.left, newNode);

        checkColor(newNode);

    }

    public void checkColor(RBTNode<Integer, String> node) {
        if(node == root) return;
        if(!node.black && !node.parent.black) {
            correctTree(node);
        }
        checkColor(node.parent);
    }

    public void correctTree(RBTNode<Integer, String> node) {
        if(node.parent.isLeftChild) { // if parent is a left child -> aunt is a right child
            //then aunt = node.parent.parent.right
            if(node.parent.parent.right == null || node.parent.parent.black) { // if aunt is black or aunt is null = black since null nodes are black
                rotate(node);                                                  // aunt is black -> rotate
            }
            if(node.parent.parent.right != null) { // if aunt is red -> color flip
                node.parent.parent.right.black = true;  // set aunt to black
                node.parent.parent.black = false;       // set grandparent to red                    red  <- grandparent
                node.parent.black = true;               // set parent to black       parent -> black     black  <- aunt
            } 
        }
        /* if parent is not a left child */
        // that means aunt = node.parent.panret.left;
        if(node.parent.parent.left == null || node.parent.parent.black) { // if aunt is black or aunt is null = black since null nodes are black
            rotate(node);                                                  // aunt is black -> rotate
        }
        if(node.parent.parent.left != null) { // if aunt is red -> color flip
            node.parent.parent.left.black = true;  // set aunt to black
            node.parent.parent.black = false;       // set grandparent to red                    red  <- grandparent
            node.parent.black = true;               // set parent to black       parent -> black     black  <- aunt
        } 
    }

   public void rotate(RBTNode<Integer, String> node) {
       if(node.isLeftChild) {
           if(node.parent.isLeftChild) {
               rightRotate(node.parent.parent);
               node.black = false;
               node.parent.black = true;
               if(node.parent.right != null){
                   node.parent.right.black = false;
                   
            } 

           }
       }
   } 

}
