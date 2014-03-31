public class A4Q1 {

    public static <E> boolean equals(LinkedList<E> l1, LinkedList<E> l2) {
        E o1, o2;
        Iterator<E> i1, i2;

        //check if either is null. If both are null, returns true. Returns false otherwise
        if (l1 == null || l2 == null)
            return l1 == l2;

        i1 = l1.iterator();
        i2 = l2.iterator();
        while (i1.hasNext() && i2.hasNext()) {
            o1 = i1.next();
            o2 = i2.next();
            if (!o1.equals(o2)) {
                return false;
            }
        }
        if (i1.hasNext() || i2.hasNext()) {
            return false;
        }
        return true;
    }

}
