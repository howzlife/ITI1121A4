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

    private SinglyLinkedList<E> take(SinglyLinkedList<E> sll, int n, Node<E> f) {
        Node<E> end;

        if (n == 0) {
            return  sll;
        }

        end = f;
        for (int i=n;i>1;i--) {
            end = end.next;
        }
        sll.addFirst(end.value);

        return sll.take(sll, n-1, f);
    }

    public SinglyLinkedList<E> take( int n ) {
        SinglyLinkedList<E> sll;
        Node<E> end;
        
        sll = new SinglyLinkedList<E>();
        if (n == 0) {
            return sll;
        }

        if (n < 0) {
            throw new IllegalArgumentException();
        }

        end = first;
        for (int i=n;i>1;i--) {
            if (end.next == null) {
                throw new IllegalArgumentException();
            }
            end = end.next;
        }
        sll.addFirst(end.value);

        return sll.take(sll, n-1, first);
    }

    //  ----------------------------------------------------------
    //  Other instance methods
    //  ----------------------------------------------------------

    public String toString() {
        StringBuffer answer = new StringBuffer( "[" );
        Node p = first;
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
