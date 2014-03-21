/* ITI 1121/1521. Introduction to Computer Science II
 * Assignment/Devoir 4
 *
 * Marcel Turcotte
 */

import java.io.FileNotFoundException;
import java.io.IOException;

public class Distance {

    public static final int TUPLE_SIZE = 5;
    private static String type = "AList";

    public static void setImpl( String type ) {

        if ( ! ( type.equals( "AList" ) || type.equals( "BinarySearchTree" ) ) ) {
            throw new IllegalArgumentException( type );
        }

        Distance.type = type;
    }

    private static Associative<String,Integer> getNewAssociative() {

        Associative<String,Integer> assoc;

        if ( type.equals( "AList" ) ) {

            assoc = new AList<String,Integer>();

        } else if ( type.equals( "BinarySearchTree" ) ) {

            assoc = new BinarySearchTree<String,Integer>();

        } else {

            throw new IllegalArgumentException( type );

        }
        
        return assoc;
    }

    // Returns an associative structure such that the keys are
    // k-tuples and the values are the number of occurrences.

    private static Associative<String,Integer> getCounts( String s ) {

        Associative<String,Integer> counts;
        counts = getNewAssociative();

        for ( int i=0; i < s.length() - TUPLE_SIZE + 1; i++ ) {
            counts.update( s.substring( i, i + TUPLE_SIZE ), new Plus( 1 ) );
        }

        return counts;
    }

    public static double compare( String aSeq, String bSeq ) {

        throw new UnsupportedOperationException( "Replace this statement by your answer to the question!" );

    }

}

