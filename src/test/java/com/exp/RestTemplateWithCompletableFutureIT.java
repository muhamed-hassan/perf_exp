package com.exp;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;

import com.exp.configs.RestTemplateUtil;

class RestTemplateWithCompletableFutureIT extends BaseIT {

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Timeout(value = 5200, unit = TimeUnit.MILLISECONDS)
    @Test
    void testDataFetchingUsingCompletableFutureAndCustomThreadPool() throws InterruptedException {
        var pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        var f1 = CompletableFuture
            .supplyAsync(() -> restTemplateUtil.getData(2), pool);
        var f2 = CompletableFuture
            .supplyAsync(() -> restTemplateUtil.getData(5), pool);
        var f3 = CompletableFuture
            .supplyAsync(() -> restTemplateUtil.getData(3), pool);

        CompletableFuture.allOf(f1, f2, f3).join();

        printSum(f1.thenApply(r -> r + f2.join()).thenApply(r -> r + f3.join()).join());

        pool.shutdown();
        pool.awaitTermination(6, TimeUnit.SECONDS);
    }

    @Timeout(value = 5200, unit = TimeUnit.MILLISECONDS)
    @Test
    void testDataFetchingUsingCompletableFutureAndCommonForkJoinPool() {
        var f1 = CompletableFuture
            .supplyAsync(() -> restTemplateUtil.getData(2));
        var f2 = CompletableFuture
            .supplyAsync(() -> restTemplateUtil.getData(5));
        var f3 = CompletableFuture
            .supplyAsync(() -> restTemplateUtil.getData(3));

        CompletableFuture.allOf(f1, f2, f3).join();

        printSum(f1.thenApply(r -> r + f2.join()).thenApply(r -> r + f3.join()).join());
    }

}
