package com.exp;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;

import com.exp.configs.WebClientUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

class WebClientIT extends BaseIT {

    @Autowired
    private WebClientUtil webClientUtil;

    @Timeout(value = 5400, unit = TimeUnit.MILLISECONDS)
    @Test
    void shouldFetchDataInParallelWhenUsingReactiveWebClient() throws Exception {
        var countDownLatch = new CountDownLatch(1);

        Flux.merge(webClientUtil.getData(2), webClientUtil.getData(5), webClientUtil.getData(3))
            .doFinally(signal -> countDownLatch.countDown())
            .parallel()
            .runOn(Schedulers.boundedElastic())
            .sequential()
            .reduce((r1, r2) -> r1 + r2)
            .doOnSuccess(this::printSum)
            .subscribe();

        countDownLatch.await();
    }

}
