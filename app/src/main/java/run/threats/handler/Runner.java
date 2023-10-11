package run.threats.handler;

import java.util.function.Consumer;

public class Runner {
    private final Thread thread;
    private final String name;
    private final Runnable runnable;
    private final Consumer<Runner> consumer;
        
    private long startTime;
    private long endTime;

    public Runner(String name, Runnable runnable, Consumer<Runner> consumer) {
        this.name = name;
        this.runnable = runnable;
        this.consumer = consumer;
        
        this.thread = new Thread(() -> {
            this.runnable.run();
            this.endTime = System.currentTimeMillis();
            this.consumer.accept(this);
        },name);
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
        this.thread.start();
    }

    public void forceStop() {
        if(this.thread.isAlive()){
            this.thread.interrupt();
        }
        endTime = System.currentTimeMillis();
        this.consumer.accept(this);
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public String getName() {
        return name;
    }

    public long getDuration() {
        return endTime - startTime;
    }

        
}


