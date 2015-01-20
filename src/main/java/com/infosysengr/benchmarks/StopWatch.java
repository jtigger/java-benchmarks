package com.infosysengr.benchmarks;

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
        mark("START");
    }

    public void lap(String eventName) {
        mark(eventName);
    }

    public void stop() {
        mark("STOP");
    }

    public long timeForEvent(String eventName) {
        int index = 0;
        while(eventNames[index] != eventName && index < lap) index++;
        return index < lap ? times[index] - times[index-1] : -1;
    }

    private void mark(String eventName) {
        times[lap] = System.currentTimeMillis();
        eventNames[lap] = eventName;
        lap++;
    }
}
