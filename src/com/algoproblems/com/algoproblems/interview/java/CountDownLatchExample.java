package com.algoproblems.interview.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample {
    private final CountDownLatch countDownLatch;

    public CountDownLatchExample(int n) {
        this.countDownLatch = new CountDownLatch(n);
    }

    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squared = new ArrayList<>();
        CountDownLatchExample task = new CountDownLatchExample(nums.size());

        Arrays.asList(1, 2, 3, 4).forEach(num -> {
            task.executorService.execute(() -> {
                System.out.println("num:: " + num + " " + Thread.currentThread().getName());
                squared.add(num * num);
                task.countDownLatch.countDown();
            });
        });

        try {
            System.out.println("Count before await: " + task.countDownLatch.getCount());
            task.countDownLatch.await();
            System.out.println("Count after await: " + task.countDownLatch.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(squared.stream().reduce(Integer::sum).get());
        System.out.println("Thread Name: " + Thread.currentThread().getName());
        task.executorService.shutdown();
    }
}















