package lesson02;

import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class Utils {
    static <T> void removeDuplicates(List<T> list) {
        ListIterator<T> iter = list.listIterator();
        Set<T> tempSet = new HashSet<>();
        while (iter.hasNext()) {
            T obj = iter.next();
            if (tempSet.contains(obj)) {
                iter.remove();
            }
            else {
                tempSet.add(obj);
            }
        }
    }
}
