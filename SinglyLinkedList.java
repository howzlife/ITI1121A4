/* ITI 1121/1521. Introduction to Computer Science II
 * Assignment/Devoir 4
 *
 * Marcel Turcotte
 */

import java.util.NoSuchElementException;

public class SinglyLinkedList<E> {

    private static class Node<E> {
        private E value;
        private Node<E> next;
        private Node( E value, Node<E> next ) {
            this.value = value;
            this.next  = next;
        }
    }

    // Instance variable
    private Node<E> first;

    //  ----------------------------------------------------------
    //  SinglyLinkedList methods
    //  ----------------------------------------------------------

    public void addFirst( E item ) {
        first = new Node<E>( item, first );
    }

    public boolean isEmpty() { 
        return first == null; 
    }
    private SinglyLinkedList<E> take(int n, Node<E> current) {
        SinglyLinkedList<E> sll;

        if (n == 1) {
            sll = new SinglyLinkedList<E>();
            sll.addFirst(current.value);
            return sll;
        }

        if (current.next != null) {
            sll = take(n - 1, current.next);
            sll.addFirst(current.value);
            return sll;
        } else throw new IllegalArgumentException();
    }

    public SinglyLinkedList<E> take( int n ) {
        if (n == 0) {
            return new SinglyLinkedList<E>();
        } else if (n < 0) {
            throw new IllegalArgumentException();
        }

        return this.take(n, first);
    }

    //  ----------------------------------------------------------
    //  Other instance methods
    //  ----------------------------------------------------------

    public String toString() {
        StringBuffer answer = new StringBuffer( "[" );
        Node<E> p = first;
        while ( p != null ) {
            if ( p != first ) {
                answer.append( "," );
            }
            answer.append( p.value );
            p = p.next;
        }
        answer.append( "]" );
        return answer.toString();
    }

}
