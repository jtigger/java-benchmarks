package com.infosysengr.benchmarks;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class JavaBenchmarksApplication {
    private static Random random = new Random(System.currentTimeMillis());

    public static class NameGenerator implements IntFunction<String> {
        @Override
        public String apply(int value) {
            return RandomStringUtils.randomAscii(value);
        }
    }

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();

        List<String> strings = new LinkedList<>();

        IntStream randomInts = random.ints(10, 120)
            .limit(100_000);

        stopWatch.start();
        randomInts.mapToObj(new NameGenerator()).forEach((name) -> strings.add(name));
        stopWatch.stop();
    }
}

class StopWatch {
    private int maxNumTimes;
    private long[] times;
    private String[] eventNames;
    private int lap;

    public StopWatch() {
        this(100);
    }

    public StopWatch(int maxNumTimes) {
        this.maxNumTimes = maxNumTimes;
        times = new long[maxNumTimes];
        eventNames = new String[maxNumTimes];
        lap = 0;
    }

    public void start() {
        mark("StopWatch starts.");
    }

    public void lap(String eventName) {
        mark(eventName);
    }

    public void stop() {
        mark("StopWatch stops.");
    }

    private void mark(String eventName) {
        times[lap] = System.currentTimeMillis();
        eventNames[lap] = eventName;
        lap++;
    }
}