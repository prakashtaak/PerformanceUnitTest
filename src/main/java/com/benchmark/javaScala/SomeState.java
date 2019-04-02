package com.benchmark.javaScala;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.IntStream;

@State(Scope.Benchmark)
public class SomeState{
    StringBuilder sb=new StringBuilder();
    public List<LocalDateTime> ldtList=new ArrayList<>();
    public List<Calendar> clList=new ArrayList<>();
    public List<String> keysConfig=new ArrayList<>();
    public List<String> computedList=new ArrayList<>();
    @Setup
    public void setUpMap(){
        IntStream.range(0, 1000).forEach(x ->{

            keysConfig.add("a"+x);
        });
        IntStream.range(0, 3500).forEach(x ->{
            computedList.add("a"+x);
        });

    }
}
