package com.exp;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;

import com.exp.configs.RestTemplateUtil;

class RestTemplateIT extends BaseIT {

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Timeout(value = 10300, unit = TimeUnit.MILLISECONDS)
    @Test
    void shouldFetchDataSequentially() {
        var r1 = restTemplateUtil.getData(2);
        var r2 = restTemplateUtil.getData(5);
        var r3 = restTemplateUtil.getData(3);

        printSum(r1 + r2 + r3);
    }

}
