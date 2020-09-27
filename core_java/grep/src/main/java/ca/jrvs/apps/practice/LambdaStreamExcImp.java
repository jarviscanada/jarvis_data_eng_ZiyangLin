package ca.jrvs.apps.practice;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExcImp implements LambdaStreamExc {

    /**
     * Create a String stream from array
     *
     * note: arbitrary number of value will be stored in an array
     *
     * @param strings a series of string input
     * @return a stream of input strings
     */
    @Override
    public Stream<String> createStrStream(String... strings) {
        return Arrays.stream(strings);
    }

    /**
     * Convert all strings to uppercase
     * please use createStrStream
     *
     * @param strings a series of string input
     * @return converted stream of strings
     */
    @Override
    public Stream<String> toUpperCase(String... strings) {
        return createStrStream(strings).map(s -> s.toUpperCase());
    }

    /**
     * filter strings that contains the pattern
     * e.g.
     * filter(stringStream, "a") will return another stream which no element contains a
     *
     *
     * @param stringStream a stream of strings
     * @param pattern the targeted regex pattern
     * @return the filtered stream of strings
     */
    @Override
    public Stream<String> filter(Stream<String> stringStream, String pattern) {
        return stringStream.filter(s -> !s.matches(pattern));
    }

    /**
     * Create an intStream from a arr[]
     * @param arr array of ints
     * @return an intStream from arr[]
     */
    @Override
    public IntStream createIntStream(int[] arr) {
        return Arrays.stream(arr);
    }

    /**
     * Convert a stream to list
     *
     * @param stream input stream
     * @param <E> generic
     * @return list converted from the input stream
     */
    @Override
    public <E> List<E> toList(Stream<E> stream) {
        return stream.collect(Collectors.toList());
    }

    /**
     * Convert a intStream to list
     * @param intStream input IntStream object
     * @return list converted from the input intstream
     */
    @Override
    public List<Integer> toList(IntStream intStream) {
        return intStream.boxed().collect(Collectors.toList());
    }

    /**
     * Create a IntStream range from start to end inclusive
     * @param start the starting integer
     * @param end the ending integer
     * @return intStream from start to end integer
     */
    @Override
    public IntStream createIntStream(int start, int end) {
        return IntStream.rangeClosed(start, end);
    }

    /**
     * Convert a intStream to a doubleStream
     * and compute square root of each element
     * @param intStream input IntStream
     * @return a doubleStream that contains square root of each element in intStream
     */
    @Override
    public DoubleStream squareRootIntStream(IntStream intStream) {
        return intStream.mapToDouble(Math::sqrt); // the same as intStream.mapToDouble(s -> Math.sqrt(s));
    }

    /**
     * filter all even number and return odd numbers from a intStream
     * @param intStream
     * @return
     */
    @Override
    public IntStream getOdd(IntStream intStream) {
        return intStream.filter(s -> !(s % 2 == 0));
    }

    /**
     * Return a lambda function that print a message with a prefix and suffix
     * This lambda can be useful to format logs
     *
     * You will learn:
     *   - functional interface http://bit.ly/2pTXRwM & http://bit.ly/33onFig
     *   - lambda syntax
     *
     * e.g.
     * LambdaStreamExc lse = new LambdaStreamImp();
     * Consumer<String> printer = lse.getLambdaPrinter("start>", "<end");
     * printer.accept("Message body");
     *
     * sout:
     * start>Message body<end
     *
     * @param prefix prefix str
     * @param suffix suffix str
     * @return a lambda printer for prefix + string + suffix
     */
    @Override
    public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
        Consumer<String> lambdaPrinter = s -> System.out.println(prefix + s + suffix);
        return lambdaPrinter;
    }

    /**
     * Print each message with a given printer
     * Please use `getLambdaPrinter` method
     *
     * e.g.
     * String[] messages = {"a","b", "c"};
     * lse.printMessages(messages, lse.getLambdaPrinter("msg:", "!") );
     *
     * sout:
     * msg:a!
     * msg:b!
     * msg:c!
     *
     * @param messages an array of message strings
     * @param printer lambda printer
     */
    @Override
    public void printMessage(String[] messages, Consumer<String> printer) {
        createStrStream(messages).forEach(printer);
    }

    /**
     * Print all odd number from a intStream.
     * Please use `createIntStream` and `getLambdaPrinter` methods
     *
     * e.g.
     * lse.printOdd(lse.createIntStream(0, 5), lse.getLambdaPrinter("odd number:", "!"));
     *
     * sout:
     * odd number:1!
     * odd number:3!
     * odd number:5!
     *
     * @param intStream
     * @param printer
     */
    @Override
    public void printOdd(IntStream intStream, Consumer<String> printer) {
        // the same as getOdd(intStream).mapToObj(s -> Integer.toString(s)).forEach(printer);
        getOdd(intStream).mapToObj(Integer::toString).forEach(printer);
    }

    /**
     * Square each number from the input.
     * Please write two solutions and compare difference
     *   - using flatMap
     *
     * @param ints a stream of list of integers to be squared
     * @return stream of squared integers
     */
    @Override
    public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
        return ints.flatMap(Collection::stream).map(x -> x * x);
    }
}