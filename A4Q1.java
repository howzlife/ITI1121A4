public class A4Q1 {


    public static <E> boolean equals(LinkedList<E> l1, LinkedList<E> l2) {
        E o1, o2;
        Iterator<E> i1, i2;

        if (l1 == null || l2 == null)
            return l1 == l2;

        //assign value returned by next(), and compare between the two lists
        i1 = l1.iterator();
        i2 = l2.iterator();
        while (i1.hasNext() && i2.hasNext()) {
            o1 = i1.next();
            o2 = i2.next();
            
            if (!o1.equals(o2)) {
                return false;
            }
        }
        //check if either list still has elements left, while the other doesnt
        if (i1.hasNext() || i2.hasNext()) {
            return false;
        }
        return true;
    }

}
