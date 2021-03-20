package com.exp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class BaseIT {

    private long startedOn;

    @BeforeEach
    void startTime() {
        startedOn = System.currentTimeMillis();
    }

    @AfterEach
    void reportExecutionTime() {
        System.out.println("Execution Time: " + ((System.currentTimeMillis() - startedOn) / 1000.0) + " secs");
    }

    protected void printSum(int finalResult) {
        System.out.println(">> SUM: " + finalResult);
    }

}
