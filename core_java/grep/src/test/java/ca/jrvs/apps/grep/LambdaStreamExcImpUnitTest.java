package ca.jrvs.apps.practice;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class LambdaStreamExcImpUnitTest {
    
    private LambdaStreamExcImp lambdaStream;

    @Before
    public void setUp() throws Exception {
        lambdaStream = new LambdaStreamExcImp();
    }

    @Test
    public void createStrStream() {
        List<String> expected = Arrays.asList("one", "two", "three");
        List<String> actual = lambdaStream.createStrStream("one", "two", "three").collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    @Test
    public void toUpperCase() {
        List<String> expected = Arrays.asList("ONE", "TWO", "THREE");
        List<String> actual = lambdaStream.toUpperCase("oNe", "tWO", "three").collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    @Test
    public void filter() {
        List<String> expected = Arrays.asList("one", "twoone");
        List<String> actual = lambdaStream.filter(Stream.of("one", "twoone", "threetwoone"), "threetwoone").collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    @Test
    public void toList() {
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        int[] temp = {1, 2, 3, 4, 5};
        List<Integer> actual = lambdaStream.toList(Arrays.stream(temp));
        assertEquals(expected, actual);
    }

    @Test
    public void createIntStream() {
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> actual = lambdaStream.createIntStream(1, 5).boxed().collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    @Test
    public void squareRootIntStream() {
        List<Double> expected = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        int[] temp = {1, 4, 9, 16, 25};
        List<Double> actual = lambdaStream.squareRootIntStream(Arrays.stream(temp)).boxed().collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    @Test
    public void getOdd() {
        List<Integer> expected = Arrays.asList(1, 3, 5);
        int[] temp = {1, 2, 3, 4, 5};
        List<Integer> actual = lambdaStream.getOdd(Arrays.stream(temp)).boxed().collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    @Test
    public void printMessage() {
        String[] msg = {"hey", "hee", "hah"};
        lambdaStream.printMessage(msg, lambdaStream.getLambdaPrinter("msg: ", "!!!"));
    }

    @Test
    public void printOdd() {
        IntStream intStream = lambdaStream.createIntStream(1, 5);
        lambdaStream.printOdd(intStream, lambdaStream.getLambdaPrinter("odd number:", "!"));
    }

    @Test
    public void flatNestedInt() {
        List<Integer> expected = Arrays.asList(1, 4, 9, 16, 25);
        List<Integer> tempA = Arrays.asList(1, 2, 3);
        List<Integer> tempB = Arrays.asList(4, 5);
        List<List<Integer>> temp = Arrays.asList(tempA, tempB);
        List<Integer> actual = lambdaStream.flatNestedInt(temp.stream()).collect(Collectors.toList());
        assertEquals(expected, actual);
    }
}
