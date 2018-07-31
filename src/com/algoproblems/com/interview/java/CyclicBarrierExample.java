package com.interview.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {
    private final CyclicBarrier cyclicBarrier;

    public CyclicBarrierExample(int n, Runnable barrierTask) {
        this.cyclicBarrier = new CyclicBarrier(n, barrierTask);
    }

    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squared = new ArrayList<>();
        CyclicBarrierExample task = new CyclicBarrierExample(nums.size(), () -> {
            System.out.println("Inside barrier task!");
            System.out.println(squared.stream().reduce((a, b) -> a + b).get());
        });
        Arrays.asList(1, 2, 3, 4).forEach(num -> {
            task.executorService.execute(() -> {
                System.out.println("num:: " + num + " " + Thread.currentThread().getName());
                try {
                    squared.add(num * num);
                    task.cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        });
        System.out.println("Thread Name: "+Thread.currentThread().getName());
        task.executorService.shutdown();
    }
}