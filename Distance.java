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
        Associative<String,Integer> aAsoc, bAsoc;
        LinkedList<String> aKeys, bKeys;
        LinkedList<Integer> aVals, bVals;
        Iterator<String> aKeyIter, bKeyIter;
        Iterator<Integer> aValIter, bValIter;
        String aKey, bKey;
        double result;
        int alen, blen;

        alen = aSeq.length();
        blen = bSeq.length();

        aAsoc = getCounts(aSeq);
        aKeys = aAsoc.keys();
        aVals = aAsoc.values();
        aKeyIter = aKeys.iterator();
        aValIter = aVals.iterator();

        bAsoc = getCounts(bSeq);
        bKeys = bAsoc.keys();
        bVals = bAsoc.values();
        bKeyIter = bKeys.iterator();
        bValIter = bVals.iterator();

        result = 0.0;
        while (aKeyIter.hasNext() && bKeyIter.hasNext()) {
            aKey = aKeyIter.next();
            bKey = bKeyIter.next();
            while (!aKey.equals(bKey)) {
                while (aKey.compareTo(bKey) < 0) {
                    result += Math.pow(((double)aValIter.next())/((double)(alen - aKey.length() + 1)), 2);
                    if (!aKeyIter.hasNext())
                        break;
                    aKey = aKeyIter.next();
                }
                while (bKey.compareTo(aKey) < 0) {
                    result += Math.pow(((double)bValIter.next())/((double)(blen - bKey.length() + 1)), 2);
                    if (!bKeyIter.hasNext())
                        break;
                    bKey = bKeyIter.next();
                }
            }
            result += Math.pow((((double)aValIter.next())/((double)(alen - aKey.length() + 1)))
                               - (((double)bValIter.next())/((double)(blen - bKey.length() + 1))), 2);
        }
        while (aKeyIter.hasNext()) {
            aKey = aKeyIter.next();
            result += Math.pow(((double)aValIter.next())/((double)(alen - aKey.length() + 1)), 2);
        }
        while (bKeyIter.hasNext()) {
            bKey = bKeyIter.next();
            result += Math.pow(((double)bValIter.next())/((double)(blen - bKey.length() + 1)), 2);
        }
        return result;
    }

}

