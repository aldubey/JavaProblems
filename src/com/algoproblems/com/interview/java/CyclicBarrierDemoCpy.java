package com.interview.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemoCpy {
    private final CyclicBarrier cyclicBarrier;

    public CyclicBarrierDemoCpy(int n, Runnable barrierTask) {
        this.cyclicBarrier = new CyclicBarrier(n, barrierTask);
    }

    ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static Runnable printSum(List<Integer> nums) {
        return () -> {
            System.out.println("Inside barrier task!");
            System.out.println(nums.stream().reduce((a, b) -> a + b).get());
        };
    }


    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squared = new ArrayList<>();

        CyclicBarrierDemoCpy task = new CyclicBarrierDemoCpy(nums.size(), () -> {
            System.out.println("Inside barrier task!");
            System.out.println(squared.stream().reduce((a, b) -> a + b).get());
        });
        nums.forEach(num -> {
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
        task.executorService.shutdown();
    }
}
