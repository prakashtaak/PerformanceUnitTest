package com.benchmark.javaScala;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {
    @Benchmark
    @Fork(value = 5, warmups = 3)
    @Measurement(iterations = 5)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testMethod(SomeState state) {
        benchMarkLocalDateTime(state);
    }


    public static StringBuilder bechMarkWithListContains(SomeState state) {
        StringBuilder sb = new StringBuilder();
        List<String> data = Arrays.asList("9afceb7c09ee2cf5b76e2e22f2480562874dde6fde5488d0cbd2b1daada76c5a",
                "e5488d0cbd2b1daada76c5a", "", "sadsad asds asd");
        Random rd = new Random();
        int size = data.size();

        for (int i = 0; i < 10; i++) {
            long t1 = System.nanoTime();
            state.computedList.forEach(x -> {
                sb.append("|").append(data.get(rd.nextInt(size - 1)));
            });

            long t2 = System.nanoTime();
            System.out.println("time for iteration " + i + " " + (t2 - t1));
        }
        return sb;
    }


    public StringBuilder bechMarkWithMap(SomeState state) {
        StringBuilder sb = new StringBuilder(500);
        Map<String, String> mapOfKV = new HashMap<>();

        state.computedList.forEach(y -> {

            String val = mapOfKV.get(y);
            sb.append(val);

            sb.append("9afceb7c09ee2cf5b76e2e22f2480562874dde6fde5488d0cbd2b1daada76c5a");
        });
        return sb;


    }

    public static void main(String[] args) throws IOException, RunnerException {


         Main.main(args);
    }

    private static void benchMarkLocalDateTime(SomeState state) {

        state.ldtList = IntStream.of(0, 23079757).mapToObj(x -> {
            LocalDateTime ldt = LocalDateTime.now();

            //System.out.println(ldt);

            //adding 1 year, 1 month, 1 week and 1 day
            LocalDateTime ldt1 = ldt.plusDays(x);
            return ldt1;
            //System.out.println(ldt1);
        }).collect(Collectors.toList());


    }

    private static void benchMarkCalendar(SomeState state) {
        state.clList = IntStream.of(0, 23079757).mapToObj(x -> {
            Calendar cl = Calendar.getInstance();

            cl.add(Calendar.DAY_OF_WEEK, x);
            return cl;
        }).collect(Collectors.toList());
    }

    private static StringBuilder bechMarkWithListContains(List<String> computedList) {

        StringBuilder sb = new StringBuilder();
        List<String> data = Arrays.asList("9afceb7c09ee2cf5b76e2e22f2480562874dde6fde5488d0cbd2b1daada76c5a",
                "e5488d0cbd2b1daada76c5a", "", "sadsad asds asd");
        Random rd = new Random();
        int size = data.size();

        for (int i = 0; i < 50; i++) {
            long t1 = System.currentTimeMillis();
            computedList.forEach(x -> {

                sb.append("|").append(data.get(rd.nextInt(size - 1)));

            });
            long t2 = System.currentTimeMillis();
            System.out.println("time for iteration " + i + " " + (t2 - t1));
        }
        return sb;
    }


}
