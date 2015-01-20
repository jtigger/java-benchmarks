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
            .limit(1_000_000);

        stopWatch.start();
        randomInts.mapToObj(new NameGenerator()).forEach((name) -> strings.add(name));
        stopWatch.stop();
        System.out.println(stopWatch.timeForEvent("STOP"));
    }
}

