package com.infosysengr.benchmarks;

import org.junit.Test;

public class StopWatchTest {

  @Test
  public void timeOneEvent() {
    StopWatch stopWatch = new StopWatch();

    stopWatch.start();
    stopWatch.stop();
  }
}
