package me.fulln.study.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CompletableFutureBuilder {

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(3);

        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return ThreadLocalRandom.current().nextInt(10);

        }, service).whenComplete((v, e) -> {
            if (e == null){
                System.out.println("执行成功！v="+v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println("c");
            return null;
        });
//        integerCompletableFuture.join();
        service.shutdown();
    }
}
