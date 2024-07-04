package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class CustomThreadPool {

    public static void main(String[] args) {
        CustomThreadPool threadPool = new CustomThreadPool(5);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                System.out.println("Task " + finalI + " executed by thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        threadPool.shutdown();
        threadPool.awaitTermination();
        System.out.println("All tasks completed.");
    }


    private final int poolSize;
    private final WorkerThread[] workers;
    private final Queue<Runnable> taskQueue;
    private volatile boolean isShutdown = false;

    public CustomThreadPool(int poolSize) {
        this.poolSize = poolSize;
        this.workers = new WorkerThread[poolSize];
        this.taskQueue = new LinkedList<>();

        for (int i = 0; i < poolSize; i++) {
            workers[i] = new WorkerThread();
            workers[i].start();
        }
    }

    public void execute(Runnable task) {
        if (isShutdown) {
            throw new IllegalStateException("ThreadPool is shutdown");
        }
        synchronized (taskQueue) {
            taskQueue.offer(task);
            taskQueue.notifyAll();
        }
    }

    public void shutdown() {
        isShutdown = true;
        synchronized (taskQueue) {
            taskQueue.notifyAll();
        }
    }

    public void awaitTermination() {
        for (WorkerThread worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private class WorkerThread extends Thread {
        @Override
        public void run() {
            Runnable task;
            while (true) {
                synchronized (taskQueue) {
                    while (taskQueue.isEmpty() && !isShutdown) {
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    if (isShutdown && taskQueue.isEmpty()) {
                        return;
                    }
                    task = taskQueue.poll();
                }
                if (task != null) {
                    try {
                        task.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}