/* ITI 1121/1521. Introduction to Computer Science II
 * Assignment/Devoir 4
 *
 * Marcel Turcotte
 */

public class BinarySearchTree< K extends Comparable<K>, V > implements Associative<K,V> {
  
    // A static nested class used to store the elements of this tree
  
    private static class Node<K,V> {
    
        private K key;
        private V value;
    
        private Node<K,V> left;
        private Node<K,V> right;
    
        private Node( K key, V value ) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }
  
    private Node<K,V> root = null;
    private int size = 0;
  
    public int size() {
        return size;
    }
  
    /** The method fun.apply() is used to associate a value with the
     *  specified key. If the associative structure previously
     *  contained the key, the associated value is passed to the
     *  method apply and the resulting value is used to replace the
     *  old value, otherwise a new association (key,value) is created,
     *  the method apply receives the value zero, the result is associated
     *  with the key.
     *
     * @param key key with which the specified value is to be associated
     * @param fun an implementation of the interface Functor
     * @return the previous value associated with this key, or null if the key was not found
     */
  
    public V update( K key, Functor<V> fun ) {
    
        // pre-condtion:
    
        if ( key == null || fun == null ) {
            throw new IllegalArgumentException( "null" );
        }
    
        // special case:
    
        if ( root == null ) {
            root = new Node<K,V>( key, fun.apply( null ) );
            size++;
            return null;
        }
    
        // general case:
    
        return update( key, fun, root );
    }
  
    // helper method

    private V update( K key, Functor<V> fun, Node<K,V> current ) {
    
        V old;
    
        int test = key.compareTo( current.key );
    
        if ( test == 0 ) { // already exists
            old = current.value;
            current.value = fun.apply( old );
        } else if ( test < 0 ) {
            if ( current.left == null ) {
        	old = null;
        	current.left = new Node<K,V>( key, fun.apply( old ) );
        	size++;
            } else {
        	old = update( key, fun, current.left );
            }
        } else {
            if ( current.right == null ) {
        	old = null;
        	current.right = new Node<K,V>( key, fun.apply( old ) );
        	size++;
            } else {
        	old = update( key, fun, current.right );
            }
        }
        return old;
    }
  
    /**
     * Looks up for key in this BinarySearchTree, returns associated value
     * if key was found and null otherwise.
     *
     * @param key value to look for
     * @return value the value associated with this key
     */
  
    public V get( K key ) {
    
        // pre-condtion:
    
        if ( key == null ) {
            throw new IllegalArgumentException( "null" );
        }
    
        return get( key, root );
    }
  
    private V get( K key, Node<K,V> current ) {
    
        V value;
    
        if ( current == null ) {
      
            value = null;
      
        } else {
      
            int test = key.compareTo( current.key );
      
            if ( test == 0 ) {
        
        	value = current.value;
        
            } else if ( test < 0 ) {
        
        	value = get( key, current.left );
        
            } else {
        
        	value = get( key, current.right );
        
            }
        }
        return value;
    }
  
    /** Returns the list of keys in order, according to the method compareTo of the key
     *  objects.
     *
     *  @return the list of keys in order
     */

    public LinkedList<K> keys() {
        LinkedList<K> keyList = new LinkedList<K>();
        LinkedList<Node<K,V>> nodeList, rightList;
        Node<K,V> curNode, rightNode;
        Iterator<Node<K,V>> iter;

        nodeList = new LinkedList<Node<K,V>>();
        rightList = new LinkedList<Node<K,V>>();
        if (root == null) {
            return keyList;
        }

        nodeList.addLast(root);
        iter = nodeList.iterator();
        while (iter.hasNext()) {
            curNode = iter.next();
            if (curNode.left != null) {
                if (curNode.right != null) {
                    rightList.addLast(curNode.right);
                    iter.add(curNode.right);
                    iter.previous();
                }
                iter.previous();
                iter.add(curNode.left);
                iter.previous();
            } else if (curNode.right != null) {
                    iter.add(curNode.right);
                    iter.previous();
            } else if (!rightList.isEmpty()){
                rightNode = rightList.get(0);
                rightList.remove(rightNode);
                do {
                    curNode = iter.next();
                } while (curNode != rightNode);
                iter.previous();
            }
        }

        iter = nodeList.iterator();
        while (iter.hasNext()) {
            keyList.addLast(iter.next().key);
        }
        return keyList;
    }

    /** Returns the list of value in the order specified by the method compareTo of the key
     *  objects.
     *
     *  @return the list of values
     */

    public LinkedList<V> values() {
        LinkedList<V> valueList = new LinkedList<V>();
        LinkedList<Node<K,V>> nodeList, rightList;
        Node<K,V> curNode, rightNode;
        Iterator<Node<K,V>> iter;

        nodeList = new LinkedList<Node<K,V>>();
        rightList = new LinkedList<Node<K,V>>();
        if (root == null) {
            return valueList;
        }

        nodeList.addLast(root);
        iter = nodeList.iterator();
        while (iter.hasNext()) {
            curNode = iter.next();
            if (curNode.left != null) {
                if (curNode.right != null) {
                    rightList.addLast(curNode.right);
                    iter.add(curNode.right);
                    iter.previous();
                }
                iter.previous();
                iter.add(curNode.left);
                iter.previous();
            } else if (curNode.right != null) {
                    iter.add(curNode.right);
                    iter.previous();
            } else if (!rightList.isEmpty()){
                rightNode = rightList.get(0);
                rightList.remove(rightNode);
                do {
                    curNode = iter.next();
                } while (curNode != rightNode);
                iter.previous();
            }
        }

        iter = nodeList.iterator();
        while (iter.hasNext()) {
            valueList.addLast(iter.next().value);
        }
        return valueList;
    }

    /** Returns the path length of the node containg specified key.  Let
     * the <b>path length</b> of a node be the number of links
     * starting from the root that must be followed to reach that node.
     * The path length of the root is 0. Returns <b>-1</b> if
     * <b>obj</b> is not found in that tree.
     *
     * @param obj the key
     * @return the path length of the specified key
     */
  
    public int getPathLength( K obj ) {
        int len = 0, l;
        Node<K,V> curNode;
        LinkedList<Node<K,V>> curList, nextList;
        if (root == null)
            return -1;
        if (obj == null)
            throw new IllegalArgumentException();
        curList = new LinkedList<Node<K,V>>();
        curList.addLast(root);
        while (true) {
            Iterator<Node<K,V>> iter = curList.iterator();
            nextList = new LinkedList<Node<K,V>>();
            while (iter.hasNext()) {
                curNode = iter.next();
                if (curNode.key.equals(obj))
                    return len;
                if (curNode.left != null)
                    nextList.addLast(curNode.left);
                if (curNode.right != null)
                    nextList.addLast(curNode.right);
            }
            len++;
            curList = nextList;
            if (nextList.size() == 0)
                break;
        }
        return -1;
    }

    /** Returns a String representation of the tree.
     *
     * @return a String representation of the tree.
     */

    public String toString() {
        return toString( root );
    }

    // helper
  
    private String toString( Node<K,V> current ) {
    
        if ( current == null ) {
            return "()";
        }
    
        return "(" + toString( current.left ) + "[" + current.key + "," + current.value + "]" + toString( current.right ) + ")";
    }
  
}
