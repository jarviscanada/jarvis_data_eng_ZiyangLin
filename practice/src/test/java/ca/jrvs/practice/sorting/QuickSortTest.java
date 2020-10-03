package ca.jrvs.practice.sorting;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class QuickSortTest {

    @Test
    public void quickSort() {
        int[] actual = {6, 4, 5, 8, 3, 7, 1, 9};
        QuickSort.quickSort(actual, 0, actual.length - 1);
        System.out.println(Arrays.toString(actual));
    }
}