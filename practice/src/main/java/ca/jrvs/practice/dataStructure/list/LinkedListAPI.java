package ca.jrvs.practice.dataStructure.list;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LinkedListAPI {

    public static void main(String[] args) {
        // instantiation, similar to ArrayList
        List<String> animals = new LinkedList<>();

        // add
        animals.add("Lion");
        animals.add("Tiger");
        animals.add(2, "Cat");

        // size, not length
        int s = animals.size();

        // get element
        String firstElement = animals.get(0);

        // search, O(n)
        Boolean hasCat = animals.contains("Cat");

        // index
        int catIndex = animals.indexOf("Cat");

        // remove
        boolean isCatRemoved = animals.remove("Cat"); // by object
        String removedElement = animals.remove(1); // remove by index

        // sort, pass comparator using lambda
        animals.sort(String::compareToIgnoreCase);

        // to array
        System.out.println(Arrays.toString(animals.toArray()));
    }
}
